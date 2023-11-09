package com.wiley.pdh.pdhproductionsupportter.repository.identifire;

import com.wiley.pdh.pdhproductionsupportter.entity.Identifier;
import com.wiley.pdh.pdhproductionsupportter.entity.IdentifierXref;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface IdentifierXrefRepository extends JpaRepository<IdentifierXref, BigInteger> {

    Optional<IdentifierXref> findByDhProdIdAndIdentCdLike(BigInteger dhProdId, String identCd);
}
