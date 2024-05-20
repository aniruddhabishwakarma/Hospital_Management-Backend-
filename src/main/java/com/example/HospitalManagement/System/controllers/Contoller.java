package com.example.HospitalManagement.System.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Contoller {

    @GetMapping("hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/bye")
    public String bye(){
        return "bye";
    }
}
