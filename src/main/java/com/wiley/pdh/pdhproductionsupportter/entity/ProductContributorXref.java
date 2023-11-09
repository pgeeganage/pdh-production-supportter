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
@Table(name = "C_PRODUCT_CONTRIBUTOR_XREF")
public class ProductContributorXref {

    @Id
    @Column(name = "DH_ID_XREF")
    private BigInteger dhIdXref;
    private String PKEY_SRC_OBJECT;
    @Column(name = "C_DH_ID")
    private BigInteger cDhId;
    @Column(name = "ROWID_SYSTEM")
    private String rowIdSystem;
    private java.sql.Date SRC_LUD;
    private int PUT_UPDATE_MERGE_IND;
    private int INTERACTION_ID;
    private int HUB_STATE_IND;
    @Column(name = "PROMOTE_IND")
    private int promoteInd;
    private String NULL_INDICATOR_BITMAP;
    private String CREATOR;
    private java.sql.Date CREATE_DATE;
    private String UPDATED_BY;
    private java.sql.Date LAST_UPDATE_DATE;
    @Column(name = "DELETED_IND")
    private String deletedIndex;
    @Column(name = "DELETED_BY")
    private String deletedBy;
    @Column(name = "DELETED_DATE")
    private java.sql.Date deletedDate;
    private int DH_PRODREG_ID;
    private int DH_CONTRIB_ID;
    private int DH_ROLETYPE_ID;
    private java.sql.Date START_DATE;
    private java.sql.Date END_DATE;
    private String PRIMARY_IND;
    private int DH_PRODTYPE_ID;
    private String PRODREG_SRC_KEY;
    private String EXCEPTION_FLAG;
    private String CONTRIB_SRC_KEY;
    private int ORDER_SEQ_NUM;
}
