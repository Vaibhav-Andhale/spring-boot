package com.vaibhav.project.exception;

import java.util.Map;

public class CustomException extends RuntimeException{
    private Map<String,String> errors;
    public CustomException(String message) {
        super(message);
    }
    public CustomException(String message,Map<String,String> errors) {
        super(message);
        this.errors=errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
