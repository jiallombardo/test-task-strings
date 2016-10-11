package com.example.stringsprocessor.beans.output;

import lombok.Value;

import java.util.List;

/**
 * A set of data that describes the result of processing of a subset
 * of strings.
 */
@Value
public class ProcessedStringList
{
    private List<ProcessedString> result;
}
