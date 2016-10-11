package com.example.stringsprocessor.beans.output.error;

import lombok.Value;

/**
 * A set of data describing an error pertaining to an object's field.
 */
@Value
public class FieldError
{
    private String field;
    private String message;
}
