package com.wiley.pdh.pdhproductionsupporter.repository.product;

import com.wiley.pdh.pdhproductionsupporter.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ProductRepository extends JpaRepository<Product, BigInteger> {

    @Procedure(name = "Product.markDeleteProductProcedure")
    void markDeleteProductProcedure(@Param("p_dh_prod_id") BigInteger dhProdId,
                                    @Param("p_ticket_name") String ticketId);
}
