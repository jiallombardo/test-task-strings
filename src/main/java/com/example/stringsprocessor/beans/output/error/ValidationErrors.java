package com.example.stringsprocessor.beans.output.error;

import lombok.Value;

import java.util.List;

/**
 * A set of data that defines a set of validation errors.
 */
@Value
public class ValidationErrors
{
    private List<FieldError> fieldErrors;
    private String generalError;
}
