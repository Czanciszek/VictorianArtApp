package com.VictorianApp.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/error")
public class MyErrorController implements ErrorController {

    @GetMapping
    public String error() {
        return "An error occurred!";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
