package com.wiley.pdh.pdhproductionsupportter.service;

import java.math.BigInteger;
import java.util.List;

public interface ArticleDeleteService {

    String softDeleteProductById(BigInteger dhId, String ticketId);

    String softDeleteBulkProducts(List<BigInteger> dhIds, String ticketId);
}
