package com.wiley.pdh.pdhproductionsupporter.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "C_DH_REFERENCE_DATA")
public class DhReferenceData {

    @Id
    @Column(name = "DH_ID")
    private BigInteger dhId;
    @Column(name = "REF_TYPE")
    private String refType;
    @Column(name = "REF_CD")
    private String refCd;
    @Column(name = "REF_DESCR")
    private String refDescr;

}
