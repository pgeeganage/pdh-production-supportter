package com.wiley.pdh.pdhproductionsupporter.repository.productdates;

import com.wiley.pdh.pdhproductionsupporter.entity.ProductDatesXref;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface ProductDatesXrefRepository extends JpaRepository<ProductDatesXref, BigInteger> {

    Optional<ProductDatesXref> findByDhProdRegIdAndDhDateTypeId(BigInteger prodRegionId, BigInteger dhDateTypeId);
}
