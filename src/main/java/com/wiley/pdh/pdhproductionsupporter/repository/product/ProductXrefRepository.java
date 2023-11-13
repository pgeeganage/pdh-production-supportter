package com.wiley.pdh.pdhproductionsupporter.repository.product;

import com.wiley.pdh.pdhproductionsupporter.entity.ProductXref;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface ProductXrefRepository extends JpaRepository<ProductXref, BigInteger> {

    List<ProductXref> findByDhId(BigInteger cDhId);
    ProductXref findByDhIdAndDeletedIndAndDeletedBy(BigInteger cDhId, String deleteIndex, String deletedBy);
}
