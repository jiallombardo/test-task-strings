package com.example.stringsprocessor.service

import com.example.stringsprocessor.service.extractor.WordExtractor
import spock.lang.Specification
import spock.lang.Unroll

import static com.example.stringsprocessor.utils.Constants.NULL_STRING_LENGTH

class StringProcessingServiceImplSpec extends Specification
{
    def testSubject = new StringProcessingServiceImpl()
    def extractor = Mock(WordExtractor, {
        extractMaxWord(_) >> { args -> args[0] } //return the string itself
    })
    def comparator = Mock(Comparator, {
        compare(*_) >> { args -> args[1]?.compareTo(args[0]) ?: -1 }
    })

    def setup()
    {
        testSubject.lengthAndLexWordExtractor = extractor
        testSubject.lengthAndLexStringComparator = comparator
    }

    @Unroll
    def 'string processing: done properly for input strings #strings and limit #limit'()
    {
        given:
            testSubject.stringProcessingLimit = limit

        when:
            def resultList = testSubject.processStrings(strings)

        then:
            resultList.collect { it.string } == expectedStrings
            resultList.collect { it.longestWord } == expectedLongest

        where:
            limit | strings                      | expectedStrings              | expectedLongest
            5     | [null, null, 'bob', 'alice'] | ['bob', 'alice', null, null] | [3, 5, NULL_STRING_LENGTH, NULL_STRING_LENGTH]
            0     | [null, null, 'bob', 'alice'] | []                           | []
            2     | ['super', 'trouper']         | ['trouper', 'super']         | [7, 5]
            1     | ['super', 'trouper']         | ['trouper']                  | [7]
            100   | []                           | []                           | []
    }

    def 'string processing: null list not processed'()
    {
        when:
            testSubject.processStrings(null)

        then:
            def exception = thrown(IllegalArgumentException)
            exception.message == 'Input list cannot be null'
    }
}
