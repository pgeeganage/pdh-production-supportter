package com.wiley.pdh.pdhproductionsupporter.service.impl;


import com.wiley.pdh.pdhproductionsupporter.entity.Product;
import com.wiley.pdh.pdhproductionsupporter.entity.ProductXref;
import com.wiley.pdh.pdhproductionsupporter.entity.SurvivorShipLog;
import com.wiley.pdh.pdhproductionsupporter.exception.SysRootException;
import com.wiley.pdh.pdhproductionsupporter.repository.SurvivorShipLogRepository;
import com.wiley.pdh.pdhproductionsupporter.repository.product.ProductRepository;
import com.wiley.pdh.pdhproductionsupporter.repository.product.ProductXrefRepository;
import com.wiley.pdh.pdhproductionsupporter.service.EcoreJanisService;
import com.wiley.pdh.pdhproductionsupporter.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EcoreJanisServiceImpl implements EcoreJanisService {

    private final SurvivorShipLogRepository survivorShipLogRepository;
    private final ProductRepository productRepository;


    @Override
    public String addArticleToEcoreJanis(BigInteger dhId, String ticketId) {
        Utility.validateParams(dhId, ticketId);
        SurvivorShipLog survivorShipLog = new SurvivorShipLog();
        Optional<Product> product = productRepository.findById(dhId);
        if (product.isPresent()) {
            Product cProduct = product.get();
            if (cProduct.getSourceLastUpdated().isBlank()) {
                survivorShipLog.setRowIdSystem(Utility.ROW_ID_SYS_WPP);
            } else {
                survivorShipLog.setRowIdSystem(cProduct.getSourceLastUpdated().trim());
            }

            if (null == cProduct.getDhTypeId()) {
                survivorShipLog.setDhProdTypeId(Utility.DH_PROD_TYPE_ID_4);
            } else {
                survivorShipLog.setDhProdTypeId(cProduct.getDhTypeId());
            }

            survivorShipLog.setStartTime(getCurrentDbTimestamp());
            survivorShipLog.setPublishedEsb(Utility.FLAG_NO);
            survivorShipLog.setPublishedSolr(Utility.FLAG_NO);
            SurvivorShipLog savedSurvivorShipLog = survivorShipLogRepository.save(survivorShipLog);

            cProduct.setSourceLastUpdated(ticketId);
            productRepository.save(cProduct);

            savedSurvivorShipLog.setEndTime(getCurrentDbTimestamp());
            SurvivorShipLog reUpdatedSurvivorshipLog = survivorShipLogRepository.save(savedSurvivorShipLog);
            return String.format(Utility.INFO_ARTICLE_SUCCESSFULLY_ADDED_TO_ECORE_JANIS, dhId,
                    reUpdatedSurvivorshipLog);
        } else {
            throw new SysRootException(String.format(Utility.EX_PRODUCT_DOES_NOT_EXISTING_IN_C_PRODUCT, dhId));
        }
    }

    private Timestamp getCurrentDbTimestamp() {
        return survivorShipLogRepository.getCurrentDatabaseTimestamp();
    }
}
