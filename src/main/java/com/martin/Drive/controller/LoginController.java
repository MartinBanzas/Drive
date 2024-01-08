package com.martin.Drive.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    @PostMapping("/login")
    public String showLoginForm() {
        return "login";
    }


}
