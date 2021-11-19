package com.indexzero.santaService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SantaProfileController {

    @GetMapping("/santa-profile")
    public String getSantaProfilePage() {

        return "santa-profile";
    }
    
}
