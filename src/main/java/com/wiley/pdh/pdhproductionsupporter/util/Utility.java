package com.wiley.pdh.pdhproductionsupporter.util;

import lombok.experimental.UtilityClass;

import java.math.BigInteger;

@UtilityClass
public class Utility {

    /**
     * Constants
     */
    public static final int PROMOTE_IND_1 = 1;
    public static final int PROMOTE_IND_0 = 0;
    public static final String FLAG_YES = "Y";
    public static final String FLAG_NO = "N";
    public static final String ROW_ID_SYS_WPP = "WPP";
    public static final BigInteger DH_PROD_TYPE_ID_4 = BigInteger.valueOf(4);
    public static final String ART_WITHDRAWN_DATE = "ART_WITHDRAWN_DATE";

    /**
     * Info Messages
     */
    public static final String INFO_PRODUCT_SUCCESSFULLY_MARKED_AS_DELETED = "Article soft deletion successful for DH_ID: {%d} %n SurvivorShipLog: {%s}";
    public static final String INFO_IDENTIFIER_SUCCESSFULLY_MARKED_AS_DELETED = "Identifier soft deletion successful for DH_PROD_ID: {%d} and IDENT_CD: {%s} %n SurvivorShipLog: {%s}";
    public static final String INFO_ARTICLE_SUCCESSFULLY_ADDED_TO_ECORE_JANIS = "Article with 'DH_ID': {%d} was successfully added to the Ecore/Janis. %n SurvivorShipLog: {%s}";
    public static final String INFO_VENDOR_BOOK_DETAILS_SUCCESSFULLY_MARKED_AS_DELETED = "VendorBook soft deletion successful for Journal with 'DH_PROD_ID': {%d} and 'DH_ID': {%d} %n SurvivorShipLog: {%s}";
    public static final String INFO_WITHDRAWN_DATE_SUCCESSFULLY_MARKED_AS_DELETED = "WithdrawnDate soft deletion successful for Article with 'DH_PROD_ID': {%d} %n SurvivorShipLog: {%s}";


    /**
     * Exception Messages
     */
    public static final String EX_PRODUCT_ALREADY_MARKED_AS_DELETED = "Product with DH_ID: {%d} already marked as deleted in 'C_PRODUCT' table.";
    public static final String EX_PRODUCT_DOES_NOT_EXISTING_IN_C_PRODUCT_XREF = "Product with DH_ID: {%d} doesn't exist in 'C_PRODUCT_XREF' table.";
    public static final String EX_PRODUCT_DOES_NOT_EXISTING_IN_C_PRODUCT = "Product with DH_ID: {%d} doesn't exist in 'C_PRODUCT' table.";
    public static final String EX_PRODUCT_CONTRIBUTOR_DOES_NOT_EXISTING_IN_C_PRODUCT_CONTRIBUTOR_XREF = "ProductContributor with C_DH_ID: {%d} doesn't exist in 'C_PRODUCT_CONTRIBUTOR_XREF' table.";
    public static final String EX_PRODUCT_CONTRIBUTOR_DOES_NOT_EXISTING_IN_C_PRODUCT_CONTRIBUTOR = "ProductContributor with DH_ID: {%d} and DH_PRODREG_ID: {%d} doesn't exist in 'C_PRODUCT_CONTRIBUTOR' table.";
    public static final String EX_PRODUCT_REGION_DOES_NOT_EXISTING_IN_C_PRODUCT_REGION = "ProductRegion with DH_PROD_ID: {%d} doesn't exist in 'C_PRODUCT_REGION' table.";
    public static final String EX_IDENTIFIER_DOES_NOT_EXISTING_IN_C_IDENTIFIER_XREF = "Identifier was not found in 'C_IDENTIFIER_XREF' table by DH_PROD_ID: {%d} and IDENT_CD: {%s}";
    public static final String EX_IDENTIFIER_DOES_NOT_EXISTING_IN_C_IDENTIFIER = "Identifier was not found in 'C_IDENTIFIER' table by DH_PROD_ID: {%d} and IDENT_CD: {%s}";
    public static final String EX_PRODUCT_DATES_XREF_DOES_NOT_EXISTING_IN_C_PRODUCT_DATES_XREF = "ProductDatesXref with DH_PRODREG_ID: {%d} doesn't exist in 'C_PRODUCT_DATES_XREF' table.";
    public static final String EX_PRODUCT_DATES_DOES_NOT_EXISTING_IN_C_PRODUCT_DATES = "ProductDates with DH_PRODREG_ID: {%d} doesn't exist in 'C_PRODUCT_DATES' table.";

    public static final String EX_SURVIVOR_SHIP_LOG_NOT_FOUND = "SurvivorShipLog not found for 'LOG_ID': {%d}";




    public static void validateParams(BigInteger dhId, String ticketId) {
        if (null == dhId) {
            throw new IllegalArgumentException("'DH_ID' is required!");
        }
        if (null == ticketId || ticketId.isBlank()) {
            throw new IllegalArgumentException("'Ticket ID' is required!");
        }
    }

    public static void validateParams(BigInteger dhProdId, String identCd, String ticketId) {
        if (null == dhProdId) {
            throw new IllegalArgumentException("'DH_PROD_ID' is required!");
        }
        if (null == identCd || identCd.isBlank()) {
            throw new IllegalArgumentException("'IDENT_CD' is required!");
        }
        if (null == ticketId || ticketId.isBlank()) {
            throw new IllegalArgumentException("'Ticket ID' is required!");
        }
    }
}
