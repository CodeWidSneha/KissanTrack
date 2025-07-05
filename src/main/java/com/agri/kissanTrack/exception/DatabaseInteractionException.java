package com.agri.kissanTrack.exception;

public class DatabaseInteractionException extends RuntimeException{

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public DatabaseInteractionException(Integer code, String message){
        super(message);
        this.code = code;

    }
}
