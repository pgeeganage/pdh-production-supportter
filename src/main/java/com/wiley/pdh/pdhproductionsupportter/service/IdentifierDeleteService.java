package com.wiley.pdh.pdhproductionsupportter.service;

import java.math.BigInteger;

public interface IdentifierDeleteService {

    String softDeleteIdentifierByDhProId(BigInteger dhProdId, String identCd, String ticketId);
}
