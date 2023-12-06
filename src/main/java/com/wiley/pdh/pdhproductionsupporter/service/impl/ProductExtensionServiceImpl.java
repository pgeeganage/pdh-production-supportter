package com.wiley.pdh.pdhproductionsupporter.service.impl;

import com.wiley.pdh.pdhproductionsupporter.entity.*;
import com.wiley.pdh.pdhproductionsupporter.repository.ReferenceDataRepository;
import com.wiley.pdh.pdhproductionsupporter.repository.SurvivorShipLogRepository;
import com.wiley.pdh.pdhproductionsupporter.repository.product.ProductRepository;
import com.wiley.pdh.pdhproductionsupporter.repository.product.region.ProductRegionRepository;
import com.wiley.pdh.pdhproductionsupporter.repository.productextension.ProductExtensionRepository;
import com.wiley.pdh.pdhproductionsupporter.repository.productextension.ProductExtensionXrefRepository;
import com.wiley.pdh.pdhproductionsupporter.service.ProductExtensionService;
import com.wiley.pdh.pdhproductionsupporter.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductExtensionServiceImpl implements ProductExtensionService {

    private final ProductRepository productRepository;
    private final ProductRegionRepository productRegionRepository;
    private final ProductExtensionRepository productExtensionRepository;
    private final ProductExtensionXrefRepository productExtensionXrefRepository;
    private final ReferenceDataRepository referenceDataRepository;
    private final SurvivorShipLogRepository survivorShipLogRepository;

    @Override
    public String updateArticleTags(BigInteger dhId, String dhExtCd, String extValue, String ticketId) {
        validateParams(dhId, dhExtCd, extValue, ticketId);
        Optional<Product> product = productRepository.findById(dhId);
        if (product.isPresent()) {
            Optional<ProductRegion> region = productRegionRepository.findByDhProdId(dhId);
            if (region.isPresent()) {
                ProductRegion productRegion = region.get();
                DhReferenceData referenceData = referenceDataRepository.findByRefCd(dhExtCd);
                Optional<ProductExtension> extension = productExtensionRepository.findByProdRegIdAndExtId(productRegion.getDhId(), referenceData.getDhId());
                if (extension.isPresent()) {
                    Optional<ProductExtensionXref> extensionXref = productExtensionXrefRepository.findByDhProdRegIdAndDhExtId(productRegion.getDhId(), referenceData.getDhId());
                    if (extensionXref.isPresent()) {
                        ProductExtensionXref productExtensionXref = extensionXref.get();
                        productExtensionXref.setExtValue(extValue);
                        productExtensionXref.setPromoteInd(Utility.PROMOTE_IND_1);
                        productExtensionXref.setLastUpdateDate(getCurrentDbTimestamp());
                        productExtensionXref.setUpdatedBy(ticketId);

                        ProductExtensionXref updatedExtXref = productExtensionXrefRepository.save(productExtensionXref);
                        SurvivorShipLog lastSurvivorShip = survivorShipLogRepository.findTopByStartTimeDesc();
                        survivorShipLogRepository.executeSurvivorShip(updatedExtXref.getRowIdSystem().trim());
                        SurvivorShipLog log = survivorShipLogRepository.findTopByRowIdSystemAndLogIdGreaterThanOrderByStartTimeDesc(
                                updatedExtXref.getRowIdSystem(), lastSurvivorShip.getLogId());

                        return String.format(Utility.INFO_ARTICLE_TAGS_SUCCESSFULLY_UPDATED, dhId, log);
                    }
                    return String.format(Utility.EX_PRODUCT_EXTENSION_XREF_DOES_NOT_EXISTING_IN_C_PRODUCT_EXTENSION_XREF, dhId, referenceData.getDhId(), dhExtCd);
                }
                return String.format(Utility.EX_PRODUCT_EXTENSION_DOES_NOT_EXISTING_IN_C_PRODUCT_EXTENSION, dhId, referenceData.getDhId(), dhExtCd);
            }
            return String.format(Utility.EX_PRODUCT_REGION_DOES_NOT_EXISTING_IN_C_PRODUCT_REGION, dhId);
        }
        return String.format(Utility.EX_PRODUCT_DOES_NOT_EXISTING_IN_C_PRODUCT, dhId);
    }

    private void validateParams(BigInteger dhId, String dhExtCd, String extValue, String ticketId) {
        Utility.validateParams(dhId, ticketId);
        if (null == dhExtCd || dhExtCd.isBlank()) {
            throw new IllegalArgumentException("'DH_EXT_CD' is required!");
        }
        if (null == extValue || ticketId.isBlank()) {
            throw new IllegalArgumentException("'EXT_VALUE' is required!");
        }
    }

    private Timestamp getCurrentDbTimestamp() {
        return survivorShipLogRepository.getCurrentDatabaseTimestamp();
    }
}
