package com.wiley.pdh.pdhproductionsupportter.service.impl;

import com.wiley.pdh.pdhproductionsupportter.entity.ProductContributor;
import com.wiley.pdh.pdhproductionsupportter.entity.ProductContributorXref;
import com.wiley.pdh.pdhproductionsupportter.entity.ProductRegion;
import com.wiley.pdh.pdhproductionsupportter.entity.SurvivorShipLog;
import com.wiley.pdh.pdhproductionsupportter.exception.SysRootException;
import com.wiley.pdh.pdhproductionsupportter.repository.SurvivorShipLogRepository;
import com.wiley.pdh.pdhproductionsupportter.repository.product.contributor.ProductContributorRepository;
import com.wiley.pdh.pdhproductionsupportter.repository.product.contributor.ProductContributorXrefRepository;
import com.wiley.pdh.pdhproductionsupportter.repository.product.region.ProductRegionRepository;
import com.wiley.pdh.pdhproductionsupportter.service.JournalVendorBookService;
import com.wiley.pdh.pdhproductionsupportter.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class JournalVendorBookServiceImpl implements JournalVendorBookService {

    private final ProductRegionRepository productRegionRepository;
    private final ProductContributorRepository productContribRepository;
    private final ProductContributorXrefRepository productContribXrefRepository;
    private final SurvivorShipLogRepository survivorShipLogRepository;

    @Override
    public String markVendorBookAsDeletedInResourcesForJournals(BigInteger dhProdId, BigInteger dhId, String ticketId) {
        Optional<ProductRegion> productRegion = productRegionRepository.findById(dhProdId);
        if (productRegion.isPresent()) {
            ProductRegion region = productRegion.get();
            Optional<ProductContributor> contributor = productContribRepository.findByDhIdAndDhProdRegId(dhId, region.getDhId());
            if (contributor.isPresent()) {
                Optional<ProductContributorXref> contributorXref = productContribXrefRepository.findByCDhId(dhId);
                if (contributorXref.isPresent()) {
                    ProductContributorXref productContributorXref = contributorXref.get();
                    productContributorXref.setPromoteInd(Utility.PROMOTE_IND_1);
                    productContributorXref.setDeletedIndex(Utility.FLAG_YES);
                    productContributorXref.setDeletedBy(ticketId);
                    productContributorXref.setDeletedDate(Date.valueOf(LocalDate.now()));

                    ProductContributorXref updatedContrib = productContribXrefRepository.save(productContributorXref);
                    SurvivorShipLog lastSurvivorShip = survivorShipLogRepository.findTopByStartTimeDesc();
                    survivorShipLogRepository.executeSurvivorShip(updatedContrib.getRowIdSystem());
                    return String.format(Utility.INFO_VENDOR_BOOK_DETAILS_SUCCESSFULLY_MARKED_AS_DELETED, dhProdId, dhId,
                            survivorShipLogRepository.findTopByRowIdSystemAndLogIdGreaterThanOrderByStartTimeDesc(
                                    updatedContrib.getRowIdSystem(), lastSurvivorShip.getLogId()).toString());
                }
                throw new SysRootException(String.format(Utility.EX_PRODUCT_CONTRIBUTOR_DOES_NOT_EXISTING_IN_C_PRODUCT_CONTRIBUTOR_XREF, dhId));
            }
            throw new SysRootException(String.format(Utility.EX_PRODUCT_CONTRIBUTOR_DOES_NOT_EXISTING_IN_C_PRODUCT_CONTRIBUTOR, dhId, region.getDhId()));
        }
        throw new SysRootException(String.format(Utility.EX_PRODUCT_REGION_DOES_NOT_EXISTING_IN_C_PRODUCT_REGION, dhProdId));
    }
}
