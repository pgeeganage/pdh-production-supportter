package com.wiley.pdh.pdhproductionsupporter.repository.identifire;

import com.wiley.pdh.pdhproductionsupporter.entity.IdentifierXref;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface IdentifierXrefRepository extends JpaRepository<IdentifierXref, BigInteger> {

    Optional<IdentifierXref> findByDhProdIdAndIdentCdLike(BigInteger dhProdId, String identCd);
}
