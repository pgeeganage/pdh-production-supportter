package com.wiley.pdh.pdhproductionsupporter.service.impl;

import com.wiley.pdh.pdhproductionsupporter.entity.*;
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
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductDatesServiceImpl implements ProductDatesService {

    private final ProductRepository productRepository;
    private final ProductRegionRepository productRegionRepository;
    private final ProductDatesRepository productDatesRepository;
    private final ProductDatesXrefRepository productDatesXrefRepository;
    private final SurvivorShipLogRepository survivorShipLogRepository;

    @Override
    public String removeWithdrawnDate(BigInteger dhId, String ticketId) {

        Optional<Product> product = productRepository.findById(dhId);
        if (product.isPresent()) {
            Optional<ProductRegion> region = productRegionRepository.findByDhProdId(dhId);
            if (region.isPresent()) {
                ProductRegion productRegion = region.get();
                Optional<ProductDates> productDates = productDatesRepository.findByDhProdRegId(productRegion.getDhId());
                if (productDates.isPresent()) {
                    Optional<ProductDatesXref> productDatesXref = productDatesXrefRepository.findByDhProdRegId(productRegion.getDhId());
                    if (productDatesXref.isPresent()) {
                        ProductDatesXref datesXref = productDatesXref.get();
                        datesXref.setDeletedInd(Utility.FLAG_YES);
                        datesXref.setDeletedBy(ticketId);
                        datesXref.setDeletedDate(getCurrentDbTimestamp());
                        datesXref.setPromoteInd(Utility.PROMOTE_IND_1);

                        ProductDatesXref updatedDatesXref = productDatesXrefRepository.save(datesXref);
                        SurvivorShipLog lastSurvivorShip = survivorShipLogRepository.findTopByStartTimeDesc();
                        survivorShipLogRepository.executeSurvivorShip(updatedDatesXref.getRowIdSystem());
                        return String.format(Utility.INFO_WITHDRAWN_DATE_SUCCESSFULLY_MARKED_AS_DELETED, dhId,
                                survivorShipLogRepository.findTopByRowIdSystemAndLogIdGreaterThanOrderByStartTimeDesc(
                                        updatedDatesXref.getRowIdSystem(), lastSurvivorShip.getLogId()));
                    }
                    return String.format(Utility.EX_PRODUCT_DATES_XREF_DOES_NOT_EXISTING_IN_C_PRODUCT_DATES_XREF, productRegion.getDhId());
                }
                return String.format(Utility.EX_PRODUCT_DATES_DOES_NOT_EXISTING_IN_C_PRODUCT_DATES, productRegion.getDhId());
            }
            return String.format(Utility.EX_PRODUCT_REGION_DOES_NOT_EXISTING_IN_C_PRODUCT_REGION, dhId);
        }
        return String.format(Utility.EX_PRODUCT_DOES_NOT_EXISTING_IN_C_PRODUCT, dhId);
    }

    private Timestamp getCurrentDbTimestamp() {
        return survivorShipLogRepository.getCurrentDatabaseTimestamp();
    }
}