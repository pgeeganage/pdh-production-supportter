package com.wiley.pdh.pdhproductionsupporter.repository;

import com.wiley.pdh.pdhproductionsupporter.entity.SurvivorShipLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.Timestamp;

@Repository
public interface SurvivorShipLogRepository extends JpaRepository<SurvivorShipLog, BigInteger> {

    SurvivorShipLog findTopByRowIdSystemAndLogIdGreaterThanOrderByStartTimeDesc(String rowIdSystem, BigInteger logId);

    @Query("SELECT SL FROM SurvivorShipLog AS SL ORDER BY SL.startTime DESC")
    SurvivorShipLog findTopByStartTimeDesc();

    @Procedure(name = "Product.executeSurvivorShip")
    void executeSurvivorShip(@Param("in_src_sys") String sourceSystem);


    @Query(value = "SELECT max(sysdate) FROM SURVIVORSHIP_LOG", nativeQuery = true)
    Timestamp getCurrentDatabaseTimestamp();
}

