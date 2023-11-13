package com.wiley.pdh.pdhproductionsupporter.controller;

import com.wiley.pdh.pdhproductionsupporter.entity.SurvivorShipLog;
import com.wiley.pdh.pdhproductionsupporter.service.SurvivorShipLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RequiredArgsConstructor
@RestController("pdhSurvivorShipLogSupportControllerV1")
@RequestMapping("/api/v1/survivor-ship-log")
public class SurvivorShipSupportControllerV1 {

    private final SurvivorShipLogService survivorShipLogService;

    @GetMapping("/{logId}")
    public ResponseEntity<SurvivorShipLog> getSurvivorShipLog(@PathVariable("logId") BigInteger logId) {
        return ResponseEntity.ok(survivorShipLogService.getSurvivorShipLog(logId));
    }
}
