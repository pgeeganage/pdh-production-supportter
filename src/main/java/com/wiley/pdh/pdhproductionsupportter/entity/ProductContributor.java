package com.wiley.pdh.pdhproductionsupportter.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "C_PRODUCT_CONTRIBUTOR")
public class ProductContributor {

    @Id
    @Column(name = "DH_ID")
    private BigInteger dhId;
    @Column(name = "DH_PRODREG_ID")
    private BigInteger dhProdRegId;
    @Column(name = "DH_CONTRIB_ID")
    private BigInteger dhContribId;
    @Column(name = "DH_ROLETYPE_ID")
    private BigInteger dhRoleTypeId;
    @Column(name = "START_DATE")
    private java.sql.Date startDate;
    @Column(name = "END_DATE")
    private java.sql.Date endDate;
    @Column(name = "PRIMARY_IND")
    private String primaryInd;
    @Column(name = "DELETED_IND")
    private String deletedInd;
    @Column(name = "DH_PRODTYPE_ID")
    private BigInteger dhProdTypeId;
    @Column(name = "ORDER_SEQ_NUM")
    private int orderSeqNum;
    @Column(name = "SOURCE_LAST_UPDATED")
    private String sourceLastUpdated;
}
