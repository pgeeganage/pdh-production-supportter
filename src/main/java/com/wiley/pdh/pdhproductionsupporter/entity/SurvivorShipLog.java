package com.wiley.pdh.pdhproductionsupporter.entity;

import javax.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.sql.Timestamp;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedStoredProcedureQuery(name = "SurvivorShipLog.executeSurvivorShip",
        procedureName = "merge_util.process_source", parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "in_src_sys", type = String.class)})
@Entity
@Table(name = "SURVIVORSHIP_LOG")
@SequenceGenerator(name = "survivorship_log_seq_generator", sequenceName = "SURVIVORSHIP_LOG_SEQ", allocationSize = 1)
public class SurvivorShipLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "survivorship_log_seq_generator")
    @Column(name = "LOG_ID")
    private BigInteger logId;
    @Column(name = "ROWID_SYSTEM")
    private String rowIdSystem;
    @Column(name = "START_TIME")
    private Timestamp startTime;
    @Column(name = "END_TIME")
    private Timestamp endTime;
    @Column(name = "PUBLISHED_ESB")
    private String publishedEsb;
    @Column(name = "PUBLISHED_SOLR")
    private String publishedSolr;
    @Column(name = "DH_PRODTYPE_ID")
    private BigInteger dhProdTypeId;
}
