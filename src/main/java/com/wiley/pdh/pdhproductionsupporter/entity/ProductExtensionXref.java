package com.wiley.pdh.pdhproductionsupporter.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "C_PRODUCT_EXTENSION_XREF", schema = "PROD_PDH")
public class ProductExtensionXref {

    @Id
    @Column(name = "DH_ID_XREF", precision = 38)
    private BigInteger dhIdXref;
    @Column(name = "PKEY_SRC_OBJECT")
    private String pkeySrcObject;
    @Column(name = "C_DH_ID")
    private BigInteger cDhId;
    @Column(name = "ROWID_SYSTEM", length = 14, nullable = false)
    private String rowIdSystem;
    @Column(name = "SRC_LUD")
    @Temporal(TemporalType.DATE)
    private Date srcLud;
    @Column(name = "PUT_UPDATE_MERGE_IND", precision = 38)
    private Long putUpdateMergeInd;
    @Column(name = "INTERACTION_ID", precision = 38)
    private Long interactionId;
    @Column(name = "HUB_STATE_IND", precision = 38, nullable = false)
    private Long hubStateInd;
    @Column(name = "PROMOTE_IND", precision = 38)
    private int promoteInd;
    @Column(name = "NULL_INDICATOR_BITMAP", length = 1000)
    private String nullIndicatorBitmap;
    @Column(name = "CREATOR", length = 50)
    private String creator;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @Column(name = "UPDATED_BY", length = 50)
    private String updatedBy;
    @Column(name = "LAST_UPDATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateDate;
    @Column(name = "DELETED_IND", length = 1)
    private String deletedInd;
    @Column(name = "DELETED_BY", length = 50)
    private String deletedBy;
    @Column(name = "DELETED_DATE")
    @Temporal(TemporalType.DATE)
    private Date deletedDate;
    @Column(name = "DH_PRODREG_ID")
    private BigInteger dhProdRegId;
    @Column(name = "DH_EXT_ID")
    private BigInteger dhExtId;
    @Column(name = "EXT_VALUE", length = 4000)
    private String extValue;
    @Column(name = "EXT_DATE")
    @Temporal(TemporalType.DATE)
    private Date extDate;
    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "END_DATE")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(name = "PRIMARY_IND", length = 1)
    private String primaryInd;
    @Column(name = "EXT_CODE", length = 20)
    private String extCode;
    @Column(name = "DH_PRODTYPE_ID", nullable = false)
    private BigInteger dhProdTypeId;
    @Column(name = "PRODREG_SRC_KEY")
    private String prodRegSrcKey;
    @Column(name = "EXCEPTION_FLAG", length = 1, columnDefinition = "VARCHAR2(1) DEFAULT 'X'")
    private String exceptionFlag;

}
