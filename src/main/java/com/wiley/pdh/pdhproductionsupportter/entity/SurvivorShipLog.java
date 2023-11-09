package com.wiley.pdh.pdhproductionsupportter.entity;

import javax.persistence.*;
import lombok.*;

import java.math.BigInteger;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SURVIVORSHIP_LOG")
public class SurvivorShipLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOG_ID")
    private BigInteger logId;
    @Column(name = "ROWID_SYSTEM")
    private String rowIdSystem;
    @Column(name = "START_TIME")
    private java.sql.Date startTime;
    @Column(name = "END_TIME")
    private java.sql.Date endTime;
    @Column(name = "PUBLISHED_ESB")
    private String publishedEsb;
    @Column(name = "PUBLISHED_SOLR")
    private String publishedSolr;
    @Column(name = "DH_PRODTYPE_ID")
    private BigInteger dhProdTypeId;
}
