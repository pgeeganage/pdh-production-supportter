package com.wiley.pdh.pdhproductionsupporter.service.impl;

import com.wiley.pdh.pdhproductionsupporter.entity.Identifier;
import com.wiley.pdh.pdhproductionsupporter.entity.IdentifierXref;
import com.wiley.pdh.pdhproductionsupporter.entity.SurvivorShipLog;
import com.wiley.pdh.pdhproductionsupporter.exception.SysRootException;
import com.wiley.pdh.pdhproductionsupporter.repository.SurvivorShipLogRepository;
import com.wiley.pdh.pdhproductionsupporter.repository.identifire.IdentifierRepository;
import com.wiley.pdh.pdhproductionsupporter.repository.identifire.IdentifierXrefRepository;
import com.wiley.pdh.pdhproductionsupporter.service.IdentifierDeleteService;
import com.wiley.pdh.pdhproductionsupporter.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class IdentifierDeleteServiceImpl implements IdentifierDeleteService {

    private final IdentifierRepository identifierRepository;
    private final SurvivorShipLogRepository survivorShipLogRepository;
    private final IdentifierXrefRepository identifierXrefRepository;


    @Override
    public String softDeleteIdentifierByDhProId(BigInteger dhProdId, String identCd, String ticketId) {

        Utility.validateParams(dhProdId, identCd, ticketId);
        return softDeleteIdentifier(dhProdId, identCd, ticketId);
    }

    private String softDeleteIdentifier(BigInteger dhProdId, String identCd, String ticketId) {
        Optional<Identifier> identifierByDhProdIdAndIdentCd = identifierRepository.findByDhProdIdAndIdentCdLike(dhProdId, identCd);
        Optional<IdentifierXref> identifierXrefByDhProdIdAndIdentCd = identifierXrefRepository.findByDhProdIdAndIdentCdLike(dhProdId, identCd);

        if (identifierByDhProdIdAndIdentCd.isPresent()) {
            if (identifierXrefByDhProdIdAndIdentCd.isPresent()) {
                IdentifierXref identifierXref = identifierXrefByDhProdIdAndIdentCd.get();
                identifierXref.setDeletedInd(Utility.FLAG_YES);
                identifierXref.setDeletedDate(getCurrentDbTimestamp());
                identifierXref.setDeletedBy(ticketId);
                IdentifierXref updatedIdentifierXref = identifierXrefRepository.save(identifierXref);

                SurvivorShipLog lastSurvivorShip = survivorShipLogRepository.findTopByStartTimeDesc();
                survivorShipLogRepository.executeSurvivorShip(updatedIdentifierXref.getRowIdSystem());

                return String.format(Utility.INFO_IDENTIFIER_SUCCESSFULLY_MARKED_AS_DELETED, dhProdId, identCd,
                        survivorShipLogRepository.findTopByRowIdSystemAndLogIdGreaterThanOrderByStartTimeDesc(
                                updatedIdentifierXref.getRowIdSystem(), lastSurvivorShip.getLogId()).toString());
            } else {
                throw new SysRootException(String.format(Utility.EX_IDENTIFIER_DOES_NOT_EXISTING_IN_C_IDENTIFIER_XREF, dhProdId, identCd));
            }
        } else {
            throw new SysRootException(String.format(Utility.EX_IDENTIFIER_DOES_NOT_EXISTING_IN_C_IDENTIFIER, dhProdId, identCd));
        }
    }

    private Timestamp getCurrentDbTimestamp() {
        return survivorShipLogRepository.getCurrentDatabaseTimestamp();
    }
}
