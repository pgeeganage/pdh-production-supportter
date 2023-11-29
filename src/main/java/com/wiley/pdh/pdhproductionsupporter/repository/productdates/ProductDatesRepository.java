package com.wiley.pdh.pdhproductionsupporter.repository.productdates;

import com.wiley.pdh.pdhproductionsupporter.entity.ProductDates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDatesRepository extends JpaRepository<ProductDates, BigInteger> {

    Optional<List<ProductDates>> findByDhProdRegIdAndDhDateTypeId(BigInteger prodRegionId, BigInteger dhDateTypeId);
}
