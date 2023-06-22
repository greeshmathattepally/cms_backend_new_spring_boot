package com.example.demo.exception;

import com.example.demo.model.ErrorResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class GlobalExceptionHandler {

    @ExceptionHandler(QuestionNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse questionNotFoundException(QuestionNotFoundException ex) {
        return new ErrorResponse(
                ex.errorCode,
                ex.message,
                HttpStatus.BAD_REQUEST.value());
    }


}
