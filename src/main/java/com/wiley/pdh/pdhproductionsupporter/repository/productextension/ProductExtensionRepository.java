package com.wiley.pdh.pdhproductionsupporter.repository.productextension;

import com.wiley.pdh.pdhproductionsupporter.entity.ProductExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface ProductExtensionRepository extends JpaRepository<ProductExtension, BigInteger> {

    Optional<ProductExtension> findByProdRegIdAndExtId(BigInteger prodRegId, BigInteger extId);
}
