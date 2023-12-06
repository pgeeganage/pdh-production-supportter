package com.wiley.pdh.pdhproductionsupporter.repository.productextension;

import com.wiley.pdh.pdhproductionsupporter.entity.ProductExtension;
import com.wiley.pdh.pdhproductionsupporter.entity.ProductExtensionXref;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface ProductExtensionXrefRepository extends JpaRepository<ProductExtensionXref, BigInteger> {

    Optional<ProductExtensionXref> findByDhProdRegIdAndDhExtId(BigInteger prodRegId, BigInteger extId);
}
