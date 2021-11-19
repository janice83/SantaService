package com.indexzero.santaService.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerProfileController {

    @Secured("ROLE_CUSTOMER")
    @GetMapping("/customer-log")
    public String customerView() {

        return "customer-profile";
    }
    
}
