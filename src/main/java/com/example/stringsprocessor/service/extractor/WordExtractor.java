package com.example.stringsprocessor.service.extractor;

/**
 * A contract for extracting words from strings.
 */
public interface WordExtractor
{

    /**
     * Extracts a "maximum word" from a string. The definition
     * of maximum word is not defined by contract and should be
     * implementation-specific.
     *
     * @param input the string to extract max word from
     * @return the max word
     */
    String extractMaxWord(String input);
}
