package com.example.demo.exception;

public class QuestionNotFoundException extends RuntimeException
{
    String errorCode;
    String message;
    public QuestionNotFoundException(String errorCode,String message)
    {
        this.errorCode=errorCode;
        this.message=message;
    }
}
