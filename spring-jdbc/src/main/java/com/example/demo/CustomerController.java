package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// dummy controller so that app remains up!
public class CustomerController {
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World!";
    }
}
