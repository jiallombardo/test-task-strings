package com.example.stringsprocessor.service;

import com.example.stringsprocessor.beans.output.ProcessedString;

import java.util.List;

/**
 * A contract for processing strings.
 */
public interface StringsProcessingService
{
    /**
     * Processes the given list of strings. The contract is as follows:
     * <ul>
     *     <li>All strings are ordered</li>
     *     <li>If the number of input strings is too big, output is
     *     truncated</li>
     * </ul>
     *
     * @param toProcess a list of strings to process
     * @return a list of {@link ProcessedString} objects as defined by the contract
     */
    List<ProcessedString> processStrings(List<String> toProcess);
}
