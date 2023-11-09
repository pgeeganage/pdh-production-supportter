package com.wiley.pdh.pdhproductionsupportter.service.impl;

import com.wiley.pdh.pdhproductionsupportter.entity.Product;
import com.wiley.pdh.pdhproductionsupportter.entity.ProductXref;
import com.wiley.pdh.pdhproductionsupportter.entity.SurvivorShipLog;
import com.wiley.pdh.pdhproductionsupportter.repository.SurvivorShipLogRepository;
import com.wiley.pdh.pdhproductionsupportter.repository.product.ProductRepository;
import com.wiley.pdh.pdhproductionsupportter.repository.product.ProductXrefRepository;
import com.wiley.pdh.pdhproductionsupportter.service.ArticleDeleteService;
import com.wiley.pdh.pdhproductionsupportter.util.Utility;
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
        validateProductDeleteParams(dhId, ticketId);
        return softDeleteProduct(dhId, ticketId);
    }

    @Override
    public String softDeleteBulkProducts(List<BigInteger> dhIds, String ticketId) {
        List<String> survivorShipLog = new ArrayList<>();
        for (BigInteger dhId : dhIds) {
            validateProductDeleteParams(dhId, ticketId);
            survivorShipLog.add(softDeleteProduct(dhId, ticketId));
        }
        return Arrays.toString(survivorShipLog.toArray());
    }

    private static void validateProductDeleteParams(BigInteger dhId, String ticketId) {
        if (null == dhId) {
            throw new IllegalArgumentException("'DH_ID' is required!");
        }
        if (null == ticketId || ticketId.isBlank()) {
            throw new IllegalArgumentException("'Ticket ID' is required!");
        }
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

//    public String softDeleteProduct(BigInteger dhId, String ticketId) {
//        Optional<Product> cProductById = productRepository.findById(dhId);
//        if (cProductById.isPresent()) {
//            Product product = cProductById.get();
//            if (Utility.ALIAS_YES.equals(product.getDeletedInd())) {
//                throw new SysRootException(String.format(Utility.EX_PRODUCT_ALREADY_MARKED_AS_DELETED, dhId));
//            }
//            List<ProductXref> productXrefs = productXrefRepository.findByDhId(product.getDhId());
//            if (null != productXrefs && !productXrefs.isEmpty()) {
//                productRepository.markDeleteProductProcedure(product.getDhId(), ticketId);
//                ProductXref updatedProductXref = productXrefRepository.findByDhIdAndDeletedIndAndDeletedBy(
//                        product.getDhId(), Utility.ALIAS_YES, ticketId);
//
//                SurvivorShipLog latestLog = survivorShipLogRepository.findTopByStartTimeDesc();
//                productRepository.executeSurvivorShip(updatedProductXref.getRowIdSystem().trim());
//                return survivorShipLogRepository.findTopByRowIdSystemAndLogIdGreaterThanOrderByStartTimeDesc(
//                        updatedProductXref.getRowIdSystem(), latestLog.getLogId()).toString();
//            } else {
//                throw new SysRootException(String.format(Utility.EX_PRODUCT_DOES_NOT_EXISTING_IN_C_PRODUCT_XREF, dhId));
//            }
//        }
//        throw new SysRootException(String.format(Utility.EX_PRODUCT_DOES_NOT_EXISTING_IN_C_PRODUCT, dhId));
//    }
}
