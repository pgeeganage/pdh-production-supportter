package com.wiley.pdh.pdhproductionsupportter.service;

import java.math.BigInteger;

public interface JournalVendorBookService {

    String markVendorBookAsDeletedInResourcesForJournals(BigInteger dhProdId, BigInteger dhId, String ticketId);
}
