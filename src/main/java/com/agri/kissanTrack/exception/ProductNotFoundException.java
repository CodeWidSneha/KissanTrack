package com.agri.kissanTrack.exception;

public class ProductNotFoundException extends RuntimeException{

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public ProductNotFoundException(Integer code, String message){
        super(message);
        this.code = code;

    }
}
