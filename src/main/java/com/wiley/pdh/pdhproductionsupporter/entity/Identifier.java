package com.wiley.pdh.pdhproductionsupporter.entity;


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
@Table(name = "C_IDENTIFIER")
public class Identifier {

    @Id
    @Column(name = "DH_ID")
    private BigInteger dhId;
    @Column(name = "DH_TYPE_ID")
    private BigInteger dhTypeId;
    @Column(name = "DH_PROD_ID")
    private BigInteger dhProdId;
    @Column(name = "IDENT_CD")
    private String identCd;
    @Column(name = "IDENT_SUBTYPE_CD")
    private String identSubTypeCd;
    @Column(name = "START_DATE")
    private java.sql.Date startDate;
    @Column(name = "END_DATE")
    private java.sql.Date endDate;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "PRIMARY_IND")
    private String primaryInd;
    @Column(name = "DELETED_IND")
    private String deletedInd;
    @Column(name = "DH_PRODTYPE_ID")
    private BigInteger dhProdTypeId;
    @Column(name = "SOURCE_LAST_UPDATED")
    private String sourceLastUpdated;
}
