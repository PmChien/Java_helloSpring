package com.example.helloSpring.helper;

import lombok.Data;

@Data
public class ApiResponse {
    private int code;
    private String message;
    private Object data;

}
