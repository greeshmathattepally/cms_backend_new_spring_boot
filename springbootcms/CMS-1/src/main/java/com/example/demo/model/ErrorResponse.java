package com.example.demo.model;

import lombok.Value;


@Value
public class ErrorResponse {

    String error;
    String message;
    int status;
}
