package com.indexzero.santaService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustController {

    @GetMapping("/customer")
    public String getCustomerPage() {
        return "customer";
    }
    
}
