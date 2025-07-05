package com.agri.kissanTrack.exception;

import com.agri.kissanTrack.dto.ErrorResponse;
import com.agri.kissanTrack.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DatabaseInteractionException.class)
    public ResponseEntity<ResponseDTO> handleDatabaseError(DatabaseInteractionException ex){

        return new ResponseEntity<>( new ErrorResponse(500, ex.getMessage(), LocalDateTime.now()),
                HttpStatus.INTERNAL_SERVER_ERROR
               );


    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleProductNotFoundError(ProductNotFoundException ex){

        return new ResponseEntity<>( new ErrorResponse(404, ex.getMessage(), LocalDateTime.now()),
                HttpStatus.NOT_FOUND
        );

    }

}
