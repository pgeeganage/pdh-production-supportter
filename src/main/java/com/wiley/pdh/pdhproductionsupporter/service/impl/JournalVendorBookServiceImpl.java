package com.wiley.pdh.pdhproductionsupporter.service.impl;

import com.wiley.pdh.pdhproductionsupporter.entity.ProductContributor;
import com.wiley.pdh.pdhproductionsupporter.entity.ProductContributorXref;
import com.wiley.pdh.pdhproductionsupporter.entity.ProductRegion;
import com.wiley.pdh.pdhproductionsupporter.entity.SurvivorShipLog;
import com.wiley.pdh.pdhproductionsupporter.exception.SysRootException;
import com.wiley.pdh.pdhproductionsupporter.repository.SurvivorShipLogRepository;
import com.wiley.pdh.pdhproductionsupporter.repository.product.contributor.ProductContributorRepository;
import com.wiley.pdh.pdhproductionsupporter.repository.product.contributor.ProductContributorXrefRepository;
import com.wiley.pdh.pdhproductionsupporter.repository.product.region.ProductRegionRepository;
import com.wiley.pdh.pdhproductionsupporter.service.JournalVendorBookService;
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
                Optional<ProductContributorXref> contributorXref = productContribXrefRepository.findByDhId(dhId);
                if (contributorXref.isPresent()) {
                    ProductContributorXref productContributorXref = contributorXref.get();
                    productContributorXref.setPromoteInd(Utility.PROMOTE_IND_1);
                    productContributorXref.setDeletedIndex(Utility.FLAG_YES);
                    productContributorXref.setDeletedBy(ticketId);
                    productContributorXref.setDeletedDate(getCurrentDbTimestamp());

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

    private Timestamp getCurrentDbTimestamp() {
        return survivorShipLogRepository.getCurrentDatabaseTimestamp();
    }
}
