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
@Table(name = "C_IDENTIFIER_XREF")
public class IdentifierXref {

    @Id
    @Column(name = "DH_ID_XREF")
    private BigInteger dhIdXref;
    @Column(name = "PKEY_SRC_OBJECT")
    private String pKeySrcObject;
    @Column(name = "C_DH_ID")
    private BigInteger cDhId;
    @Column(name = "ROWID_SYSTEM")
    private String rowIdSystem;
    @Column(name = "SRC_LUD")
    private java.sql.Date srcLud;
    @Column(name = "PUT_UPDATE_MERGE_IND")
    private BigInteger putUpdateMergeInd;
    @Column(name = "INTERACTION_ID")
    private BigInteger interactionId;
    @Column(name = "HUB_STATE_IND")
    private BigInteger hubStateInd;
    @Column(name = "PROMOTE_IND")
    private int promoteInd;
    @Column(name = "NULL_INDICATOR_BITMAP")
    private String nullIndicatorBitMap;
    @Column(name = "CREATOR")
    private String creator;
    @Column(name = "CREATE_DATE")
    private java.sql.Date createDate;
    @Column(name = "UPDATED_BY")
    private String updatedBy;
    @Column(name = "LAST_UPDATE_DATE")
    private java.sql.Date lastUpdateDate;
    @Column(name = "DELETED_IND")
    private String deletedInd;
    @Column(name = "DELETED_BY")
    private String deletedBy;
    @Column(name = "DELETED_DATE")
    private java.sql.Date deletedDate;
    @Column(name = "DH_TYPE_ID")
    private BigInteger dhTypeId;
    @Column(name = "DH_PROD_ID")
    private BigInteger dhProdId;
    @Column(name = "IDENT_CD")
    private String identCd;
    @Column(name = "IDENT_SUBTYPE_CD")
    private String identSubtypeCd;
    @Column(name = "START_DATE")
    private java.sql.Date startDate;
    @Column(name = "END_DATE")
    private java.sql.Date endDate;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "PRIMARY_IND")
    private String primaryInd;
    @Column(name = "DH_PRODTYPE_ID")
    private BigInteger dhProdTypeId;
    @Column(name = "PROD_SRC_KEY")
    private String prodSrcKey;
    @Column(name = "EXCEPTION_FLAG")
    private String exceptionFlag;
}
