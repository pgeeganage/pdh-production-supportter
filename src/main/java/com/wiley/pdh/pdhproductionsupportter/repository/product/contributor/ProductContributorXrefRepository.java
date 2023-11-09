package com.wiley.pdh.pdhproductionsupportter.repository.product.contributor;

import com.wiley.pdh.pdhproductionsupportter.entity.ProductContributor;
import com.wiley.pdh.pdhproductionsupportter.entity.ProductContributorXref;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface ProductContributorXrefRepository extends JpaRepository<ProductContributorXref, BigInteger> {

    Optional<ProductContributorXref> findByCDhId(BigInteger dhId);
}
