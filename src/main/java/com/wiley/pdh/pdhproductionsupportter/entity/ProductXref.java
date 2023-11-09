package com.wiley.pdh.pdhproductionsupportter.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "C_PRODUCT_XREF")
public class ProductXref {

    @Id
    private BigInteger DH_ID_XREF;
    private String PKEY_SRC_OBJECT;
    @Column(name = "C_DH_ID")
    private BigInteger dhId;
    @Column(name = "ROWID_SYSTEM")
    private String rowIdSystem;
    private java.sql.Date SRC_LUD;
    private BigInteger PUT_UPDATE_MERGE_IND;
    private BigInteger INTERACTION_ID;
    private BigInteger HUB_STATE_IND;
    private BigInteger PROMOTE_IND;
    private String NULL_INDICATOR_BITMAP;
    private String CREATOR;
    private java.sql.Date CREATE_DATE;
    private String UPDATED_BY;
    private java.sql.Date LAST_UPDATE_DATE;
    @Column(name = "DELETED_IND")
    private String deletedInd;
    @Column(name = "DELETED_BY")
    private String deletedBy;
    private java.sql.Date DELETED_DATE;
    @Column(name = "DH_TYPE_ID")
    private BigInteger dhTypeId;
    private BigInteger DH_SUBTYPE_ID;
    private String STATUS;
    private java.sql.Date START_DATE;
    private java.sql.Date END_DATE;
    private BigInteger DH_SRC_LANGUAGE_ID;
    private String DESCR;
    private String SHORT_DESCR;
    private BigInteger DH_PUB_ID;
    private String EDITION_NUM;
    private java.sql.Date PUB_DATE;
    private java.sql.Date OUT_OF_PRINT_DATE;
    private String SUBSCRIPTION_TYPE;
    private BigInteger DH_OWNER_REG_ID;
    private String PRIMARY_IND;
    private String EXCEPTION_FLAG;
}
