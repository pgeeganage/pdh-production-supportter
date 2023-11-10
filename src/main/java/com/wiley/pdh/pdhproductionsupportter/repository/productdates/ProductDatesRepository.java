package com.wiley.pdh.pdhproductionsupportter.repository.productdates;

import com.wiley.pdh.pdhproductionsupportter.entity.ProductDates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface ProductDatesRepository extends JpaRepository<ProductDates, BigInteger> {

    Optional<ProductDates> findByDhProdRegId(BigInteger prodRegionId);
}
