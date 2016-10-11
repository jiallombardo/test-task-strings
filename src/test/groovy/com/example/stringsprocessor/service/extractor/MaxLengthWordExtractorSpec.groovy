package com.example.stringsprocessor.service.extractor

import spock.lang.Specification
import spock.lang.Unroll

class MaxLengthWordExtractorSpec extends Specification
{
    def testSubject = new LengthAndLexWordExtractor()
    def mockComparator = Mock(Comparator, {
        compare(*_) >> { args -> args[1].compareTo(args[0]) }
    })
    def splitExpression = '[\\s,]+' //spaces or commas

    def setup()
    {
        testSubject.wordSeparatorPattern = splitExpression
        testSubject.maxLengthComparator = mockComparator
    }

    @Unroll
    def 'extracting max words: source string \'#string\''()
    {
        when:
            def result = testSubject.extractMaxWord(string)

        then:
            result == expectedResult

        where:
            string                 | expectedResult
            null                   | null
            ''                     | null
            '     , '              | null
            '      ,   , test'     | 'test'
            'test'                 | 'test'
            'test a zest'          | 'zest'
            'tester, for a burger' | 'tester'
    }
}
