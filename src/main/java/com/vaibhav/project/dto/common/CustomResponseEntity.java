package com.vaibhav.project.dto.common;

import java.util.Map;

public class CustomResponseEntity<D> {
    private String message;
    private Map<String,String> errors;

    private D data;

    public CustomResponseEntity(String message, Map<String, String> errors, D data) {
        this.message = message;
        this.errors = errors;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public D getData() {
        return data;
    }
}
