package com.example.stringsprocessor.service;

import com.example.stringsprocessor.beans.output.ProcessedString;
import com.example.stringsprocessor.service.extractor.WordExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;

import static com.example.stringsprocessor.utils.Constants.NULL_STRING_LENGTH;
import static java.util.stream.Collectors.toList;

/**
 * A concrete implementation of {@link StringsProcessingService}.
 */
@Service
public class StringProcessingServiceImpl implements StringsProcessingService
{
    @Value("${string.processing.limit}")
    private Integer stringProcessingLimit;

    @Autowired
    private WordExtractor lengthAndLexWordExtractor;

    @Resource(name = "lengthAndLexStringComparator")
    private Comparator<String> lengthAndLexStringComparator;


    @Override
    public List<ProcessedString> processStrings(List<String> toProcess)
    {
        Assert.notNull(toProcess, "Input list cannot be null");
        return toProcess.stream()
                .map(str ->
                {
                    String maxWord = lengthAndLexWordExtractor.extractMaxWord(str);
                    return new ProcessedString(str, maxWord == null ? NULL_STRING_LENGTH : maxWord.length(), maxWord);
                })
                .collect(toList())
                .stream()
                .sorted((o1, o2) -> lengthAndLexStringComparator.compare(o1.getMaxWord(), o2.getMaxWord()))
                .limit(stringProcessingLimit)
                .collect(toList());
    }
}
