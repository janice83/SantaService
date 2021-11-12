package com.indexzero.santaService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerRegisterController {

    @GetMapping("/customer-register")
    public String home() {
        return "customer-registration";
    }
    
}
