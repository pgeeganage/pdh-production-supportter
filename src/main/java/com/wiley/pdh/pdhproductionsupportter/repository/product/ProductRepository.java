package com.wiley.pdh.pdhproductionsupportter.repository.product;

import com.wiley.pdh.pdhproductionsupportter.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.ParameterMode;
import java.math.BigInteger;

@Repository
public interface ProductRepository extends JpaRepository<Product, BigInteger> {

//    @Procedure(name = "pdh_utils_pkg.mark_delete_product")
//    Object markDeleteProductProcedure(@Param("p_dh_prod_id") BigInteger dhProdId,
//                                      @Param("p_ticket_name") String ticketId);

//    @Modifying
//    @Query(value = "BEGIN pdh_utils_pkg.mark_delete_product(:p_dh_prod_id, trim(:p_ticket_name)); END;", nativeQuery = true)
//    void markDeleteProductProcedure(@Param("p_dh_prod_id") BigInteger dhProdId,
//                                    @Param("p_ticket_name") String ticketId);

//    @Query(value = "{ CALL pdh_utils_pkg.mark_delete_product(:p_dh_prod_id, trim(:p_ticket_name)) }", nativeQuery = true)

    @Procedure(name = "Product.markDeleteProductProcedure")
    void markDeleteProductProcedure(@Param("p_dh_prod_id") BigInteger dhProdId,
                                    @Param("p_ticket_name") String ticketId);

//    @Modifying
//    @Query(value = "BEGIN merge_util.process_source(trim(:in_src_sys)); END;", nativeQuery = true)
//    void executeSurvivorShip(@Param("in_src_sys") String sourceSystem);
//    @Query(value = "{ CALL merge_util.process_source(trim(:in_src_sys)) }", nativeQuery = true)
}
