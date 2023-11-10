package com.wiley.pdh.pdhproductionsupportter.repository.productdates;

import com.wiley.pdh.pdhproductionsupportter.entity.ProductDates;
import com.wiley.pdh.pdhproductionsupportter.entity.ProductDatesXref;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface ProductDatesXrefRepository extends JpaRepository<ProductDatesXref, BigInteger> {

    Optional<ProductDatesXref> findByDhProdRegId(BigInteger prodRegionId);
}
