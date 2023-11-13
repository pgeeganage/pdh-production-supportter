package com.wiley.pdh.pdhproductionsupporter.service;

import com.wiley.pdh.pdhproductionsupporter.entity.SurvivorShipLog;

import java.math.BigInteger;

public interface SurvivorShipLogService {

    SurvivorShipLog getSurvivorShipLog(BigInteger logId);
}
