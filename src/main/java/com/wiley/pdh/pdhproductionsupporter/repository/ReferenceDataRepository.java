package com.wiley.pdh.pdhproductionsupporter.repository;

import com.wiley.pdh.pdhproductionsupporter.entity.DhReferenceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ReferenceDataRepository extends JpaRepository<DhReferenceData, BigInteger> {

    DhReferenceData findByRefCd(String refCd);
}