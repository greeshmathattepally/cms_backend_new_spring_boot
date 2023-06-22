package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;

import java.time.Instant;

import static com.example.demo.constants.FAQConstants.DATE_PATTERN;
import static com.example.demo.constants.FAQConstants.TIME_ZONE;


@Value
public class ApiErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN, timezone = TIME_ZONE)
    Instant timestamp;
    String error;
    String message;
    int status;
}
