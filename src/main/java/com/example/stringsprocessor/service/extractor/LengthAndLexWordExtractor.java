package com.example.stringsprocessor.service.extractor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Comparator;

/**
 * A {@link WordExtractor} that utilizes max word length
 */
@Component
public class LengthAndLexWordExtractor implements WordExtractor
{
    @Resource(name = "lengthAndLexStringComparator")
    private Comparator<String> maxLengthComparator;
    @Value("${string.word.separator}")
    private String wordSeparatorPattern;

    @Override
    public String extractMaxWord(String input)
    {
        if (StringUtils.isEmpty(input))
            return null;
        return Arrays.stream(input.split(wordSeparatorPattern))
                .min(maxLengthComparator) //first element from the sorted list
                .orElse(null);
    }
}
