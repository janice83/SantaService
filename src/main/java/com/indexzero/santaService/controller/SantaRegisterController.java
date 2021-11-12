package com.indexzero.santaService.controller;

import com.indexzero.santaService.model.SantaAccount;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/santa-register")
public class SantaRegisterController {

    @ModelAttribute
    public SantaAccount getSantaAccount() {
        return new SantaAccount();
    }

    @GetMapping("")
    public String home() {
        return "santa-registration";
    }
    @PostMapping("")
    public String addSantaProfile() {
        return "redirect:/santa-register";
    }
    
}
