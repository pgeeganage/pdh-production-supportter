package com.wiley.pdh.pdhproductionsupportter.util;

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

    /**
     * Info Messages
     */
    public static final String INFO_PRODUCT_SUCCESSFULLY_MARKED_AS_DELETED = "Article soft deletion successful for DH_ID: {%d} %n SurvivorShipLog: {%s}";
    public static final String INFO_IDENTIFIER_SUCCESSFULLY_MARKED_AS_DELETED = "Identifier soft deletion successful for DH_PROD_ID: {%d} and IDENT_CD: {%s} %n SurvivorShipLog: {%s}";
    public static final String INFO_ARTICLE_SUCCESSFULLY_ADDED_TO_ECORE_JANIS = "Article with 'DH_ID': {%d} was successfully added to the Ecore/Janis. %n SurvivorShipLog: {%s}";
    public static final String INFO_VENDOR_BOOK_DETAILS_SUCCESSFULLY_MARKED_AS_DELETED = "VendorBook soft deletion successful for Journal with 'DH_PROD_ID': {%d} and 'DH_ID': {%d} %n SurvivorShipLog: {%s}";


    /**
     * Exception Messages
     */
    public static final String EX_PRODUCT_ALREADY_MARKED_AS_DELETED = "Product with DH_ID: {%d} already marked as deleted in 'C_PRODUCT' table.";
//    public static final String EX_BULK_PRODUCT_SUCCESSFULLY_MARKED_AS_DELETED = "Article bulk soft deletion successful for {%d} Product DH_IDs. %n SurvivorShipLog: {%s}";
    public static final String EX_PRODUCT_DOES_NOT_EXISTING_IN_C_PRODUCT_XREF = "Product with DH_ID: {%d} doesn't exist in 'C_PRODUCT_XREF' table.";
    public static final String EX_PRODUCT_DOES_NOT_EXISTING_IN_C_PRODUCT = "Product with DH_ID: {%d} doesn't exist in 'C_PRODUCT' table.";

}
