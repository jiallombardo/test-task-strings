package com.example.stringsprocessor.service.comparator

import spock.lang.Specification
import spock.lang.Unroll

class LengthAndLexStringComparatorSpec extends Specification
{
    def testSubject = new LengthAndLexStringComparator()

    @Unroll
    def 'word comparison: between \'#string1\' and \'#string2\''()
    {
        when:
            def result = testSubject.compare(string1, string2)

        then:
            result == expectedResult

        where:
            string1       | string2      | expectedResult
            null          | null         | 0
            null          | ''           | 0
            ''            | null         | 0
            ''            | ''           | 0
            'test'        | ''           | -1
            null          | 'test'       | 1
            'testing'     | 'testarossa' | 3
            'testaburger' | 'burger'     | -5
            'process'     | 'proceed'    | -14
            'process'     | 'process'    | 0
    }
}
