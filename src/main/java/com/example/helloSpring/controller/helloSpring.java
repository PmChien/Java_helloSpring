package com.example.helloSpring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloSpring {
    @GetMapping("/hello")
    String sayHello(){
        return "Hello Spring";
    }
}
