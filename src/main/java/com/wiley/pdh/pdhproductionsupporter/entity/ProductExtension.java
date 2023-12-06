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
@Table(name = "C_PRODUCT_EXTENSION")
public class ProductExtension {

    @Id
    @Column(name = "DH_ID")
    private BigInteger dhId;
    @Column(name = "DH_PRODREG_ID", nullable = false)
    private BigInteger prodRegId;
    @Column(name = "DH_EXT_ID")
    private BigInteger extId;
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
    @Column(name = "DELETED_IND", length = 1)
    private String deletedInd;
    @Column(name = "DH_PRODTYPE_ID", nullable = false)
    private Long prodTypeId;
    @Column(name = "SOURCE_LAST_UPDATED", length = 14)
    private String sourceLastUpdated;
    @ManyToOne
    @JoinColumn(name = "DH_PRODREG_ID", referencedColumnName = "DH_ID", insertable = false, updatable = false)
    private ProductRegion productRegion;
    @ManyToOne
    @JoinColumn(name = "DH_EXT_ID", referencedColumnName = "DH_ID", insertable = false, updatable = false)
    private DhReferenceData dhReferenceData;
}
