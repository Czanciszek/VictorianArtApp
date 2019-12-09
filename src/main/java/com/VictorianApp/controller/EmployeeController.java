package com.VictorianApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/user")
public class EmployeeController extends UserController {

    @GetMapping
    public String UserHomePage() {
        //implement later
        return "Hello, Employee!";
    }
}
