package com.wiley.pdh.pdhproductionsupporter.service;

import java.math.BigInteger;
import java.util.List;

public interface ArticleDeleteService {

    String softDeleteProductById(BigInteger dhId, String ticketId);

    String softDeleteBulkProducts(List<BigInteger> dhIds, String ticketId);
}
