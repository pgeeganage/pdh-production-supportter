package com.wiley.pdh.pdhproductionsupporter.service.impl;

import com.wiley.pdh.pdhproductionsupporter.entity.SurvivorShipLog;
import com.wiley.pdh.pdhproductionsupporter.exception.SysRootException;
import com.wiley.pdh.pdhproductionsupporter.repository.SurvivorShipLogRepository;
import com.wiley.pdh.pdhproductionsupporter.service.SurvivorShipLogService;
import com.wiley.pdh.pdhproductionsupporter.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SurvivorShipLogServiceImpl implements SurvivorShipLogService {

    private final SurvivorShipLogRepository survivorShipLogRepository;


    @Override
    public SurvivorShipLog getSurvivorShipLog(BigInteger logId) {
        Optional<SurvivorShipLog> survivorShipLog = survivorShipLogRepository.findById(logId);
        if (survivorShipLog.isPresent()) {
            return survivorShipLog.get();
        } else {
            throw new SysRootException(String.format(Utility.EX_SURVIVOR_SHIP_LOG_NOT_FOUND, logId));
        }
    }
}
