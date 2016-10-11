package com.example.stringsprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * The application class.
 */
@SpringBootApplication
@PropertySource({"classpath:ValidationMessages.properties"})
public class StringsProcessorApp
{
    public static void main(String[] args)
    {
        SpringApplication.run(StringsProcessorApp.class, args);
    }
}
