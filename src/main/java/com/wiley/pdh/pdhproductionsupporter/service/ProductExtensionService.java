package com.wiley.pdh.pdhproductionsupporter.service;

import java.math.BigInteger;

public interface ProductExtensionService {
    String updateArticleTags(BigInteger dhId, String dhExtCd, String extValue, String ticketId);
}
