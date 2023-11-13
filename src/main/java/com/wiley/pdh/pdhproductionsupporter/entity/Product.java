package com.wiley.pdh.pdhproductionsupporter.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedStoredProcedureQuery(name = "Product.markDeleteProductProcedure",
        procedureName = "pdh_utils_pkg.mark_delete_product", parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_dh_prod_id", type = BigInteger.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_ticket_name", type = String.class)})

@NamedStoredProcedureQuery(name = "Product.executeSurvivorShip",
        procedureName = "merge_util.process_source", parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "in_src_sys", type = String.class)})
@Entity
@Table(name = "C_PRODUCT")
public class Product {
    @Id
    @Column(name = "DH_ID")
    private BigInteger dhId;
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
    private BigDecimal dhPubId;
    @Column(name = "EDITION_NUM")
    private String editionNum;
    @Column(name = "PUB_DATE")
    private java.sql.Date pubDate;
    @Column(name = "OUT_OF_PRINT_DATE")
    private java.sql.Date outOfPrintDate;
    @Column(name = "SUBSCRIPTION_TYPE")
    private String subscriptionDate;
    @Column(name = "DH_OWNER_REG_ID")
    private BigDecimal dhOwnerRegId;
    @Column(name = "PRIMARY_IND")
    private String primaryInd;
    @Column(name = "DELETED_IND")
    private String deletedInd;
    @Column(name = "SOURCE_LAST_UPDATED")
    private String sourceLastUpdated;
}
