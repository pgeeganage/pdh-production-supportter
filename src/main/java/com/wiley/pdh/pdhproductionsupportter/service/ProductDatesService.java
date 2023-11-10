package com.wiley.pdh.pdhproductionsupportter.service;

import java.math.BigInteger;

public interface ProductDatesService {

    String removeWithdrawnDate(BigInteger dhId, String ticketId);
}
