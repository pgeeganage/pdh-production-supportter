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
@Table(name = "C_PRODUCT_DATES_XREF")
public class ProductDatesXref {

    @Id
    @Column(name = "DH_ID_XREF")
    private BigInteger dhIdXref;
    @Column(name = "PKEY_SRC_OBJECT")
    private String pkeySrcObject;
    @Column(name = "C_DH_ID")
    private int cDhId;
    @Column(name = "ROWID_SYSTEM")
    private String rowIdSystem;
    @Column(name = "SRC_LUD")
    private java.sql.Date srcLud;
    @Column(name = "PUT_UPDATE_MERGE_IND")
    private int putUpdateMergeInd;
    @Column(name = "INTERACTION_ID")
    private int interactionId;
    @Column(name = "HUB_STATE_IND")
    private int hubStateInd;
    @Column(name = "PROMOTE_IND")
    private int promoteInd;
    @Column(name = "NULL_INDICATOR_BITMAP")
    private String nullIndicatorBitMap;
    @Column(name = "CREATOR")
    private String creator;
    @Column(name = "CREATE_DATE")
    private java.sql.Date createdDate;
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
    @Column(name = "DH_PRODREG_ID")
    private int dhProdRegId;
    @Column(name = "DH_DATE_TYPE_ID")
    private int dhDateTypeId;
    @Column(name = "DATE_VALUE")
    private java.sql.Date dateValue;
    @Column(name = "START_DATE")
    private java.sql.Date startDate;
    @Column(name = "END_DATE")
    private java.sql.Date endDate;
    @Column(name = "PRIMARY_IND")
    private String primaryInd;
    @Column(name = "DH_PRODTYPE_ID")
    private int dhProdTypeId;
    @Column(name = "PRODREG_SRC_KEY")
    private String prodRegSrcKey;
    @Column(name = "EXCEPTION_FLAG")
    private String exceptionFlag;
}
