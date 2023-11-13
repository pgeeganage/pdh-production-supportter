package com.wiley.pdh.pdhproductionsupporter.exception;

public class SysRootException extends RuntimeException {

    public SysRootException(String message) {
        super(message);
    }

    public SysRootException(String message, Throwable cause) {
        super(message, cause);
    }
}
