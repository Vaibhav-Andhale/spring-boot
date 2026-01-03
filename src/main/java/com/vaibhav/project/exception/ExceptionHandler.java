package com.vaibhav.project.exception;

import com.vaibhav.project.dto.common.CustomResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Map;

@ControllerAdvice
public class ExceptionHandler {

    private static final Logger logger= LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomResponseEntity> customExceptionHandler(CustomException customException){
        return new ResponseEntity<>(new CustomResponseEntity(customException.getMessage(),customException.getErrors(),null), HttpStatus.BAD_REQUEST);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponseEntity> ExceptionHandler(Exception customException){
        logger.error("Exception:",customException);
        return new ResponseEntity<>(new CustomResponseEntity(customException.getMessage(),null,null), HttpStatus.BAD_REQUEST);
    }
}
