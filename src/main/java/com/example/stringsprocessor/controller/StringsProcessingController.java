package com.example.stringsprocessor.controller;

import com.example.stringsprocessor.beans.input.StringProcessingList;
import com.example.stringsprocessor.beans.output.ProcessedStringList;
import com.example.stringsprocessor.beans.output.error.FieldError;
import com.example.stringsprocessor.beans.output.error.ValidationErrors;
import com.example.stringsprocessor.service.StringsProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.stringsprocessor.utils.Constants.URL_STRING_PROCESSING;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Controller for specific string-processing tasks.
 */
@Controller
public class StringsProcessingController
{
    @Value("${error.validation.invalidBody}")
    private String invalidRequestBody;
    @Autowired
    private StringsProcessingService stringsProcessingService;

    @RequestMapping(value = URL_STRING_PROCESSING, method = POST, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ProcessedStringList processStrings(@RequestBody @Valid StringProcessingList list)
    {
        return new ProcessedStringList(stringsProcessingService.processStrings(list.getStrings()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ValidationErrors processValidationError(MethodArgumentNotValidException ex)
    {
        return new ValidationErrors(
                ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(err -> new FieldError(err.getField(), err.getDefaultMessage()))
                        .collect(toList()), null
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ValidationErrors processMessageDeserialization()
    {
        return new ValidationErrors(null, invalidRequestBody);
    }
}
