package com.example.stringsprocessor.beans.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Value;

/**
 * A single processed string. Contains the string itself and the length of its longest word.
 */
@Value
@JsonIgnoreProperties({"maxWord"})
@JsonPropertyOrder({"string", "longestWord"})
public class ProcessedString
{
    private String string;
    private int longestWord;
    private String maxWord;
}
