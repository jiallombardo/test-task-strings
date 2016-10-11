package com.example.stringsprocessor.beans.input;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * A list of strings for processing.
 */
@Value
public class StringProcessingList
{
    @NotNull(message = "{error.validation.stringList}")
    private List<String> strings;
}
