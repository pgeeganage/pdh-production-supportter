package com.wiley.pdh.pdhproductionsupportter.controller;

import com.wiley.pdh.pdhproductionsupportter.service.ArticleDeleteService;
import com.wiley.pdh.pdhproductionsupportter.service.SurvivorShipLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RequiredArgsConstructor
@RestController("pdhArticleSupportControllerV1")
@RequestMapping("/api/v1/article")
public class PdhArticleSupportControllerV1 {

    private final ArticleDeleteService articleDeleteService;
    private final SurvivorShipLogService survivorShipLogService;


    // Article deletion in PDH (Single Article Deletion)
    @PatchMapping("/delete/{dhId}")
    public ResponseEntity<String> softDeleteProductById(@PathVariable("dhId") BigInteger dhId,
                                                        @RequestParam("ticketId") String ticketId) {
        return ResponseEntity.ok(articleDeleteService.softDeleteProductById(dhId, ticketId));
    }

    // Article deletion in PDH (Bulk Article Deletion)
    @PatchMapping("/delete/bulk")
    public ResponseEntity<String> softDeleteBulkProducts(@RequestBody List<BigInteger> dhIDs,
                                                         @RequestParam("ticketId") String ticketId) {
        return ResponseEntity.ok(articleDeleteService.softDeleteBulkProducts(dhIDs, ticketId));
    }

    // Add articles to eCORE/JANIS (VCH)
    @PatchMapping("/re-save/{dhId}")
    public ResponseEntity<String> addArticleToEcoreJanis(@PathVariable("dhId") BigInteger dhId,
                                                         @RequestParam("ticketId") String ticketId) {
        return ResponseEntity.ok(survivorShipLogService.addArticleToEcoreJanis(dhId, ticketId));
    }
}
