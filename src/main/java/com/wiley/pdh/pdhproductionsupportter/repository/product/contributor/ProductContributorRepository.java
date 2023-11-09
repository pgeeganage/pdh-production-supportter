package com.wiley.pdh.pdhproductionsupportter.repository.product.contributor;

import com.wiley.pdh.pdhproductionsupportter.entity.ProductContributor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface ProductContributorRepository extends JpaRepository<ProductContributor, BigInteger> {

    Optional<ProductContributor> findByDhIdAndDhProdRegId(BigInteger dhId, BigInteger regionId);
}
