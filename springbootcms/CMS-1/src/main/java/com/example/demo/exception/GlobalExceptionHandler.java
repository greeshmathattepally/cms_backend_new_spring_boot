package com.example.demo.exception;

import com.example.demo.model.ApiErrorResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class GlobalExceptionHandler {

    @ExceptionHandler(QuestionNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse questionNotFoundException(QuestionNotFoundException ex) {
        return new ApiErrorResponse(Instant.now(),
                ex.errorCode,
                ex.message,
                HttpStatus.BAD_REQUEST.value());
    }


}
