package com.wiley.pdh.pdhproductionsupporter.repository.identifire;

import com.wiley.pdh.pdhproductionsupporter.entity.Identifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface IdentifierRepository extends JpaRepository<Identifier, BigInteger> {

    Optional<Identifier> findByDhProdIdAndIdentCdLike(BigInteger dhProdId, String identCd);
}
