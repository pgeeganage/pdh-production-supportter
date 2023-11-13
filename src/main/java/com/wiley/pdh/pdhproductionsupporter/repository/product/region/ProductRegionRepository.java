package com.wiley.pdh.pdhproductionsupporter.repository.product.region;

import com.wiley.pdh.pdhproductionsupporter.entity.ProductRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface ProductRegionRepository extends JpaRepository<ProductRegion, BigInteger> {

    Optional<ProductRegion> findByDhProdId(BigInteger dhProdId);
}
