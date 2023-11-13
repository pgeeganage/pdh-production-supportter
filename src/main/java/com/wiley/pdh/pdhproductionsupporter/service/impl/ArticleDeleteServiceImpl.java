package com.wiley.pdh.pdhproductionsupporter.service.impl;

import com.wiley.pdh.pdhproductionsupporter.entity.Product;
import com.wiley.pdh.pdhproductionsupporter.entity.ProductXref;
import com.wiley.pdh.pdhproductionsupporter.entity.SurvivorShipLog;
import com.wiley.pdh.pdhproductionsupporter.repository.SurvivorShipLogRepository;
import com.wiley.pdh.pdhproductionsupporter.repository.product.ProductRepository;
import com.wiley.pdh.pdhproductionsupporter.repository.product.ProductXrefRepository;
import com.wiley.pdh.pdhproductionsupporter.service.ArticleDeleteService;
import com.wiley.pdh.pdhproductionsupporter.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ArticleDeleteServiceImpl implements ArticleDeleteService {

    private final ProductRepository productRepository;
    private final ProductXrefRepository productXrefRepository;
    private final SurvivorShipLogRepository survivorShipLogRepository;

    @Override
    public String softDeleteProductById(BigInteger dhId, String ticketId) {
        Utility.validateParams(dhId, ticketId);
        return softDeleteProduct(dhId, ticketId);
    }

    @Override
    public String softDeleteBulkProducts(List<BigInteger> dhIds, String ticketId) {
        List<String> survivorShipLog = new ArrayList<>();
        for (BigInteger dhId : dhIds) {
            Utility.validateParams(dhId, ticketId);
            survivorShipLog.add(softDeleteProduct(dhId, ticketId));
        }
        return Arrays.toString(survivorShipLog.toArray());
    }

    public String softDeleteProduct(BigInteger dhId, String ticketId) {
        Optional<Product> cProductById = productRepository.findById(dhId);
        if (cProductById.isPresent()) {
            Product product = cProductById.get();
            if (Utility.FLAG_YES.equals(product.getDeletedInd())) {
                return String.format(Utility.EX_PRODUCT_ALREADY_MARKED_AS_DELETED, dhId);
            }
            List<ProductXref> productXrefs = productXrefRepository.findByDhId(product.getDhId());
            if (null != productXrefs && !productXrefs.isEmpty()) {
                productRepository.markDeleteProductProcedure(product.getDhId(), ticketId);
                ProductXref updatedProductXref = productXrefRepository.findByDhIdAndDeletedIndAndDeletedBy(
                        product.getDhId(), Utility.FLAG_YES, ticketId);

                SurvivorShipLog latestLog = survivorShipLogRepository.findTopByStartTimeDesc();
                survivorShipLogRepository.executeSurvivorShip(updatedProductXref.getRowIdSystem().trim());
                return String.format(Utility.INFO_PRODUCT_SUCCESSFULLY_MARKED_AS_DELETED,
                        dhId, survivorShipLogRepository.findTopByRowIdSystemAndLogIdGreaterThanOrderByStartTimeDesc(
                                updatedProductXref.getRowIdSystem(), latestLog.getLogId()).toString());
            } else {
                return String.format(Utility.EX_PRODUCT_DOES_NOT_EXISTING_IN_C_PRODUCT_XREF, dhId);
            }
        }
        return String.format(Utility.EX_PRODUCT_DOES_NOT_EXISTING_IN_C_PRODUCT, dhId);
    }
}
