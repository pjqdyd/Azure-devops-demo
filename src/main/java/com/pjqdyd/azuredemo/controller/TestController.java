package com.pjqdyd.azuredemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/sayHi")
    public String sayHi(){
        return "Hello Azure Devops";
    }
}
