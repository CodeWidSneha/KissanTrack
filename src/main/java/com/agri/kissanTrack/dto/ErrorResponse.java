package com.agri.kissanTrack.dto;

import java.time.LocalDateTime;

public class ErrorResponse implements ResponseDTO{

    private Integer  code;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponse(Integer code, String message, LocalDateTime timestamp){
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
    }
}