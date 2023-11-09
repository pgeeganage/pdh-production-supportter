package com.wiley.pdh.pdhproductionsupportter.controller;

import com.wiley.pdh.pdhproductionsupportter.service.JournalVendorBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;


@RequiredArgsConstructor
@RestController("pdhJournalSupportControllerV1")
@RequestMapping("/api/v1/journal")
public class PdhJournalSupportControllerV1 {

    private final JournalVendorBookService journalVendorBookService;

    //  Could you please update the vendor book in resources for journals
    @PatchMapping("/update-vendor-book/{dhProdId}")
    public ResponseEntity<String> updateVendorBookInJournals(@PathVariable("dhProdId") BigInteger dhProdId,
                                                             @RequestParam("dhId")     BigInteger dhId,
                                                             @RequestParam("ticketId") String ticketId) {
        return ResponseEntity.ok(journalVendorBookService.markVendorBookAsDeletedInResourcesForJournals(dhProdId, dhId, ticketId));
    }
}
