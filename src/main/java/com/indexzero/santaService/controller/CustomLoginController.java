package com.indexzero.santaService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomLoginController {

    @GetMapping("/custom-login")
    public String redirectLogin() {
        System.out.println();
        System.out.println("Joo");
        System.out.println();
        return "redirect:/santa-profile";
    }
    
}
