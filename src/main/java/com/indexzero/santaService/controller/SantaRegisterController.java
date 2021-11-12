package com.indexzero.santaService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SantaRegisterController {

    @GetMapping("/santa-register")
    public String home() {
        return "santa-registration";
    }
    
}
