package com.wiley.pdh.pdhproductionsupportter.service.impl;

import com.wiley.pdh.pdhproductionsupportter.entity.Identifier;
import com.wiley.pdh.pdhproductionsupportter.entity.IdentifierXref;
import com.wiley.pdh.pdhproductionsupportter.entity.SurvivorShipLog;
import com.wiley.pdh.pdhproductionsupportter.exception.SysRootException;
import com.wiley.pdh.pdhproductionsupportter.repository.SurvivorShipLogRepository;
import com.wiley.pdh.pdhproductionsupportter.repository.identifire.IdentifierRepository;
import com.wiley.pdh.pdhproductionsupportter.repository.identifire.IdentifierXrefRepository;
import com.wiley.pdh.pdhproductionsupportter.repository.product.ProductRepository;
import com.wiley.pdh.pdhproductionsupportter.service.IdentifierDeleteService;
import com.wiley.pdh.pdhproductionsupportter.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class IdentifierDeleteServiceImpl implements IdentifierDeleteService {

    private final IdentifierRepository identifierRepository;
    private final ProductRepository productRepository;
    private final SurvivorShipLogRepository survivorShipLogRepository;
    private final IdentifierXrefRepository identifierXrefRepository;


    @Override
    public String softDeleteIdentifierByDhProId(BigInteger dhProdId, String identCd, String ticketId) {

        validateIdentifierDeleteParams(dhProdId, identCd, ticketId);
        return softDeleteIdentifier(dhProdId, identCd, ticketId);
    }

    private static void validateIdentifierDeleteParams(BigInteger dhProdId, String identCd, String ticketId) {
        if (null == dhProdId) {
            throw new IllegalArgumentException("'DH_ID' is required!");
        }
        if (null == identCd || identCd.isBlank()) {
            throw new IllegalArgumentException("'IDENT_CD' is required!");
        }
        if (null == ticketId || ticketId.isBlank()) {
            throw new IllegalArgumentException("'Ticket ID' is required!");
        }
    }

    private String softDeleteIdentifier(BigInteger dhProdId, String identCd, String ticketId) {
        Optional<Identifier> identifierByDhProdIdAndIdentCd = identifierRepository.findByDhProdIdAndIdentCdLike(dhProdId, identCd);
        Optional<IdentifierXref> identifierXrefByDhProdIdAndIdentCd = identifierXrefRepository.findByDhProdIdAndIdentCdLike(dhProdId, identCd);

        if (identifierByDhProdIdAndIdentCd.isPresent()) {
            if (identifierXrefByDhProdIdAndIdentCd.isPresent()) {
                IdentifierXref identifierXref = identifierXrefByDhProdIdAndIdentCd.get();
                identifierXref.setDeletedInd(Utility.FLAG_YES);
                identifierXref.setDeletedDate(Date.valueOf(LocalDate.now()));
                identifierXref.setDeletedBy(ticketId);
                IdentifierXref updatedIdentifierXref = identifierXrefRepository.save(identifierXref);

                SurvivorShipLog lastSurvivorShip = survivorShipLogRepository.findTopByStartTimeDesc();
                survivorShipLogRepository.executeSurvivorShip(updatedIdentifierXref.getRowIdSystem());

                return String.format(Utility.INFO_IDENTIFIER_SUCCESSFULLY_MARKED_AS_DELETED, dhProdId, identCd,
                        survivorShipLogRepository.findTopByRowIdSystemAndLogIdGreaterThanOrderByStartTimeDesc(
                                updatedIdentifierXref.getRowIdSystem(), lastSurvivorShip.getLogId()).toString());
            } else {
                throw new SysRootException(String.format("Identifier was not found in 'C_IDENTIFIER_XREF' table by DH_PROD_ID: {%d} and IDENT_CD: {%s}", dhProdId, identCd));
            }
        } else {
            throw new SysRootException(String.format("Identifier was not found in 'C_IDENTIFIER' table by DH_PROD_ID: {%d} and IDENT_CD: {%s}", dhProdId, identCd));
        }
    }
}
