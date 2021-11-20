package com.indexzero.santaService.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerProfileController {

    @Secured("ROLE_CUSTOMER")
    @GetMapping("/customer-profile")
    public String customerView() {
        System.out.println();
        System.out.println("Tuli asiakassivulle!");
        return "customer-profile";
    }

    /* @PostMapping("/customer-log")
    public String redirectOnLogin() {

        return "redirect:/customer-profile";
    } */

    
}
