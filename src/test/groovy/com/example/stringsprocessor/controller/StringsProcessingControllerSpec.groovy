package com.example.stringsprocessor.controller

import groovyx.net.http.RESTClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.context.ContextConfiguration
import spock.lang.Shared
import spock.lang.Specification

import static com.example.stringsprocessor.utils.Constants.URL_STRING_PROCESSING
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import static org.springframework.mock.web.MockHttpServletRequest.DEFAULT_REMOTE_HOST
import static org.springframework.mock.web.MockHttpServletRequest.HTTP

@ContextConfiguration
@SpringBootTest(webEnvironment = RANDOM_PORT)
class StringsProcessingControllerSpec extends Specification
{
    @Shared
    def restClient
    @Value('${local.server.port}')
    int serverPort

    def setup()
    {
        restClient = new RESTClient("$HTTP://$DEFAULT_REMOTE_HOST:$serverPort")
        //in case of >399 response, a proper response object will be returned instead of an error
        restClient.handler.failure = restClient.handler.success
        restClient.ignoreSSLIssues()
    }

    def 'string processing: success scenario'()
    {
        given:
            def body = [
                    strings: [
                            'Sound boy proceed to blast into the galaxy',
                            'Go back rocket man into the sky you\'ll see',
                            'Hear it all the time, come back rewind',
                            'Aliens are watching up in the sky',
                            'Sound boy process to blast into the galaxy',
                            'No one gonna harm you',
                            'They all want you to play I watch the birds of prey',
                            '',
                            null
                    ]
            ]

        when:
            def response = restClient.post(path: URL_STRING_PROCESSING,
                    body: body,
                    contentType: APPLICATION_JSON_VALUE)

        then:
            response.status == HttpStatus.OK.value()
            def content = response.data.result
            content.collect { it.longestWord == ['8', '7', '7', '6', '6'] }
            content.collect { it.string } == ['Aliens are watching up in the sky',
                                              'Sound boy process to blast into the galaxy',
                                              'Sound boy proceed to blast into the galaxy',
                                              'Go back rocket man into the sky you\'ll see',
                                              'Hear it all the time, come back rewind']
    }

    def 'string processing: null string list'()
    {
        when:
            def response = restClient.post(path: URL_STRING_PROCESSING,
                    body: [strings: null],
                    contentType: APPLICATION_JSON_VALUE)

        then:
            response.status == HttpStatus.BAD_REQUEST.value()
            def errors = response.data.fieldErrors
            errors.collect { it.field } == ['strings']
            errors.collect { it.message } == ['String list cannot be null']
    }

    def 'string processing: invalid body'()
    {
        when:
            def response = restClient.post(path: URL_STRING_PROCESSING,
                    body: [],
                    contentType: APPLICATION_JSON_VALUE)

        then:
            response.status == HttpStatus.BAD_REQUEST.value()
            response.data.generalError == 'Invalid request body'
    }

}
