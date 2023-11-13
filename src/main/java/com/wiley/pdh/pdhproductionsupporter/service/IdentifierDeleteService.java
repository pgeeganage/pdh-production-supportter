package com.wiley.pdh.pdhproductionsupporter.service;

import java.math.BigInteger;

public interface IdentifierDeleteService {

    String softDeleteIdentifierByDhProId(BigInteger dhProdId, String identCd, String ticketId);
}
