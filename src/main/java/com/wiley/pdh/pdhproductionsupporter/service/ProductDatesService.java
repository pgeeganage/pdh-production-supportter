package com.wiley.pdh.pdhproductionsupporter.service;

import java.math.BigInteger;

public interface ProductDatesService {

    String removeWithdrawnDate(BigInteger dhId, String ticketId);
}
