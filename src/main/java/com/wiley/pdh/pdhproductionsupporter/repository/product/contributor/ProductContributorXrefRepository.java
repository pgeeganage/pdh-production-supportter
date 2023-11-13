package com.wiley.pdh.pdhproductionsupporter.repository.product.contributor;

import com.wiley.pdh.pdhproductionsupporter.entity.ProductContributorXref;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface ProductContributorXrefRepository extends JpaRepository<ProductContributorXref, BigInteger> {

    Optional<ProductContributorXref> findByDhId(BigInteger dhId);
}
