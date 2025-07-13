package com.agri.kissanTrack.dto;

public class DeleteSuccessResponse implements ResponseDTO{

   private String message;

    public DeleteSuccessResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
