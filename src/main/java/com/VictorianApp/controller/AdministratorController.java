package com.VictorianApp.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/admin")
public class AdministratorController extends UserController {

    @GetMapping
    public String AdministratorHomePage() {
        //implement later
        return "Hello, Administrator!";
    }



}
