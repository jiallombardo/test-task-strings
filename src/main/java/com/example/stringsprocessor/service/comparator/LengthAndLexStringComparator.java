package com.example.stringsprocessor.service.comparator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Comparator;

/**
 * A comparator that compares strings by their length and uses lexicographic
 * ordering for equal length strings.
 * As a result, longer and bigger (in terms of lexicographic comparison) strings
 * would go first in a collection sorted by this comparator.
 * <p>A sample ordering would be:</p>
 * <tt>process -> proceed -> pro</tt>
 */
@Component
public class LengthAndLexStringComparator implements Comparator<String>
{

    @Override
    public int compare(String o1, String o2)
    {
        if (StringUtils.isEmpty(o1) && StringUtils.isEmpty(o2))
            return 0; //null strings are equal, as they don't contain any words anyway
        else if (StringUtils.isEmpty(o1))
            return 1;
        else if (StringUtils.isEmpty(o2))
            return -1;
        else
        {
            int lengthDifference = o2.length() - o1.length();
            return lengthDifference == 0 ? o2.compareTo(o1) : lengthDifference;
        }
    }
}