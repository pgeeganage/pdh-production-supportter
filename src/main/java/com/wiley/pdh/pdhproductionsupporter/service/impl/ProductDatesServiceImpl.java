package com.wiley.pdh.pdhproductionsupporter.service.impl;

import com.wiley.pdh.pdhproductionsupporter.entity.*;
import com.wiley.pdh.pdhproductionsupporter.repository.ReferenceDataRepository;
import com.wiley.pdh.pdhproductionsupporter.repository.SurvivorShipLogRepository;
import com.wiley.pdh.pdhproductionsupporter.repository.product.ProductRepository;
import com.wiley.pdh.pdhproductionsupporter.repository.product.region.ProductRegionRepository;
import com.wiley.pdh.pdhproductionsupporter.repository.productdates.ProductDatesRepository;
import com.wiley.pdh.pdhproductionsupporter.repository.productdates.ProductDatesXrefRepository;
import com.wiley.pdh.pdhproductionsupporter.service.ProductDatesService;
import com.wiley.pdh.pdhproductionsupporter.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductDatesServiceImpl implements ProductDatesService {

    private final ProductRepository productRepository;
    private final ProductRegionRepository productRegionRepository;
    private final ProductDatesRepository productDatesRepository;
    private final ProductDatesXrefRepository productDatesXrefRepository;
    private final SurvivorShipLogRepository survivorShipLogRepository;
    private final ReferenceDataRepository referenceDataRepository;

    @Override
    public String removeWithdrawnDate(BigInteger dhId, String ticketId) {
        Utility.validateParams(dhId, ticketId);
        Optional<Product> product = productRepository.findById(dhId);
        if (product.isPresent()) {
            Optional<ProductRegion> region = productRegionRepository.findByDhProdId(dhId);
            if (region.isPresent()) {
                ProductRegion productRegion = region.get();

                DhReferenceData refData = referenceDataRepository.findByRefCd(Utility.ART_WITHDRAWN_DATE);

                Optional<List<ProductDates>> productDates = productDatesRepository.findByDhProdRegIdAndDhDateTypeId(productRegion.getDhId(), refData.getDhId());
                if (productDates.isPresent()) {
                    Optional<ProductDatesXref> productDatesXref = productDatesXrefRepository.findByDhProdRegIdAndDhDateTypeId(productRegion.getDhId(), refData.getDhId());
                    if (productDatesXref.isPresent()) {
                        ProductDatesXref datesXref = productDatesXref.get();
                        datesXref.setDeletedInd(Utility.FLAG_YES);
                        datesXref.setDeletedBy(ticketId);
                        datesXref.setDeletedDate(getCurrentDbTimestamp());
                        datesXref.setPromoteInd(Utility.PROMOTE_IND_1);

                        ProductDatesXref updatedDatesXref = productDatesXrefRepository.save(datesXref);
                        SurvivorShipLog lastSurvivorShip = survivorShipLogRepository.findTopByStartTimeDesc();
                        survivorShipLogRepository.executeSurvivorShip(updatedDatesXref.getRowIdSystem().trim());
                        SurvivorShipLog log = survivorShipLogRepository.findTopByRowIdSystemAndLogIdGreaterThanOrderByStartTimeDesc(
                                updatedDatesXref.getRowIdSystem(), lastSurvivorShip.getLogId());
                        return String.format(Utility.INFO_WITHDRAWN_DATE_SUCCESSFULLY_MARKED_AS_DELETED, dhId, log);
                    }
                    return String.format(Utility.EX_PRODUCT_DATES_XREF_DOES_NOT_EXISTING_IN_C_PRODUCT_DATES_XREF, productRegion.getDhId(), refData.getDhId(), Utility.ART_WITHDRAWN_DATE);
                }
                return String.format(Utility.EX_PRODUCT_DATES_DOES_NOT_EXISTING_IN_C_PRODUCT_DATES, productRegion.getDhId(), refData.getDhId(), Utility.ART_WITHDRAWN_DATE);
            }
            return String.format(Utility.EX_PRODUCT_REGION_DOES_NOT_EXISTING_IN_C_PRODUCT_REGION, dhId);
        }
        return String.format(Utility.EX_PRODUCT_DOES_NOT_EXISTING_IN_C_PRODUCT, dhId);
    }

    private Timestamp getCurrentDbTimestamp() {
        return survivorShipLogRepository.getCurrentDatabaseTimestamp();
    }
}
