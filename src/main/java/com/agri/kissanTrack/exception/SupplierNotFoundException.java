package com.agri.kissanTrack.exception;

public class SupplierNotFoundException extends RuntimeException {
    private final Integer code;

    public Integer getCode() {
        return code;
    }

    public SupplierNotFoundException(Integer code, String message){
        super(message);
        this.code = code;

    }

}
