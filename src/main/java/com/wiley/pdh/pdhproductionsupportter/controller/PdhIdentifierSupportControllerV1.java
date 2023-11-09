package com.wiley.pdh.pdhproductionsupportter.controller;


import com.wiley.pdh.pdhproductionsupportter.service.IdentifierDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RequiredArgsConstructor
@RestController("pdhIdentifierSupportControllerV1")
@RequestMapping("/api/v1/identifier")
public class PdhIdentifierSupportControllerV1 {

    private final IdentifierDeleteService identifierDeleteService;

    // Delete duplicate record in PDH
    @PatchMapping("/delete/{dhProdId}")
    public ResponseEntity<String> softDeleteIdentifierByDhProdId(@PathVariable("dhProdId") BigInteger dhProdId,
                                                                 @RequestParam("identCd") String identCd,
                                                                 @RequestParam("ticketId") String ticketId) {
        return ResponseEntity.ok(identifierDeleteService.softDeleteIdentifierByDhProId(dhProdId, identCd, ticketId));
    }
}
