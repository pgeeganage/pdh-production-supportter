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
    @Column(name = "DH_ID_XREF")
    private BigInteger dhIdXref;
    @Column(name = "PKEY_SRC_OBJECT")
    private String pkeySrcObject;
    @Column(name = "C_DH_ID")
    private BigInteger dhId;
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
    private BigInteger promoteInd;
    @Column(name = "NULL_INDICATOR_BITMAP")
    private String nullIndicatorBitMap;
    @Column(name = "CREATOR")
    private String creator;
    @Column(name = "CREATE_DATE")
    private java.sql.Date createDate;
    @Column(name = "UPDATED_BY")
    private String updatedBy;
    @Column(name = "LAST_UPDATE_DATE")
    private java.sql.Date lastUpdatedDate;
    @Column(name = "DELETED_IND")
    private String deletedInd;
    @Column(name = "DELETED_BY")
    private String deletedBy;
    @Column(name = "DELETED_DATE")
    private java.sql.Date deletedDate;
    @Column(name = "DH_TYPE_ID")
    private BigInteger dhTypeId;
    @Column(name = "DH_SUBTYPE_ID")
    private BigInteger dhSubTypeId;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "START_DATE")
    private java.sql.Date startDate;
    @Column(name = "END_DATE")
    private java.sql.Date endDate;
    @Column(name = "DH_SRC_LANGUAGE_ID")
    private BigInteger dhSrcLanguageId;
    @Column(name = "DESCR")
    private String descr;
    @Column(name = "SHORT_DESCR")
    private String shortDescr;
    @Column(name = "DH_PUB_ID")
    private BigInteger dhPubId;
    @Column(name = "EDITION_NUM")
    private String editionNum;
    @Column(name = "PUB_DATE")
    private java.sql.Date pubDate;
    @Column(name = "OUT_OF_PRINT_DATE")
    private java.sql.Date outOfPrintDate;
    @Column(name = "SUBSCRIPTION_TYPE")
    private String subscriptionType;
    @Column(name = "DH_OWNER_REG_ID")
    private BigInteger dhOwnerRegId;
    @Column(name = "PRIMARY_IND")
    private String primaryInd;
    @Column(name = "EXCEPTION_FLAG")
    private String executionFlag;
}
