package com.wiley.pdh.pdhproductionsupportter.service.impl;


import com.wiley.pdh.pdhproductionsupportter.entity.Product;
import com.wiley.pdh.pdhproductionsupportter.entity.ProductXref;
import com.wiley.pdh.pdhproductionsupportter.entity.SurvivorShipLog;
import com.wiley.pdh.pdhproductionsupportter.repository.SurvivorShipLogRepository;
import com.wiley.pdh.pdhproductionsupportter.repository.product.ProductRepository;
import com.wiley.pdh.pdhproductionsupportter.repository.product.ProductXrefRepository;
import com.wiley.pdh.pdhproductionsupportter.service.EcoreJanisService;
import com.wiley.pdh.pdhproductionsupportter.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EcoreJanisServiceImpl implements EcoreJanisService {

    private final SurvivorShipLogRepository survivorShipLogRepository;
    private final ProductRepository productRepository;
    private final ProductXrefRepository productXrefRepository;


    @Override
    public String addArticleToEcoreJanis(BigInteger dhId, String ticketId) {
        Utility.validateParams(dhId, ticketId);
        SurvivorShipLog survivorShipLog = new SurvivorShipLog();

        List<ProductXref> productXrefs = productXrefRepository.findByDhId(dhId);
        if (null != productXrefs && !productXrefs.isEmpty()) {
            ProductXref productXref = productXrefs.get(0);
            survivorShipLog.setRowIdSystem(productXref.getRowIdSystem());
            survivorShipLog.setDhProdTypeId(productXref.getDhTypeId());
        } else {
            survivorShipLog.setRowIdSystem(Utility.ROW_ID_SYS_WPP);
            survivorShipLog.setDhProdTypeId(Utility.DH_PROD_TYPE_ID_4);
        }

        survivorShipLog.setStartTime(Date.valueOf(String.valueOf(LocalDateTime.now())));
        survivorShipLog.setPublishedEsb(Utility.FLAG_NO);
        survivorShipLog.setPublishedSolr(Utility.FLAG_NO);
        SurvivorShipLog savedSurvivorShipLog = survivorShipLogRepository.save(survivorShipLog);

        Optional<Product> productByDhId = productRepository.findById(dhId);
        if (productByDhId.isPresent()) {
            Product product = productByDhId.get();
            product.setSourceLastUpdated(ticketId);
            productRepository.save(product);
        }

        savedSurvivorShipLog.setEndTime(Date.valueOf(String.valueOf(LocalDateTime.now())));
        return String.format(Utility.INFO_ARTICLE_SUCCESSFULLY_ADDED_TO_ECORE_JANIS, dhId,
                survivorShipLogRepository.save(savedSurvivorShipLog));
    }
}
