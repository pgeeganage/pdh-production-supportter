package com.wiley.pdh.pdhproductionsupportter.repository.product;

import com.wiley.pdh.pdhproductionsupportter.entity.ProductXref;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface ProductXrefRepository extends JpaRepository<ProductXref, BigInteger> {

    List<ProductXref> findByDhId(BigInteger cDhId);
    ProductXref findByDhIdAndDeletedIndAndDeletedBy(BigInteger cDhId, String deleteIndex, String deletedBy);
}
