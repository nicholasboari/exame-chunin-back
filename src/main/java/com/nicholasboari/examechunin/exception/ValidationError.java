package com.nicholasboari.examechunin.exception;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationError extends StandardError{

    private List<FieldMessage> errors = new ArrayList<>();

    public void addFieldMessages(String fieldName, String message){
        errors.add(new FieldMessage(fieldName, message));
    }
}
