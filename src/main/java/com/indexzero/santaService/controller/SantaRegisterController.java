package com.indexzero.santaService.controller;

import com.indexzero.santaService.model.SantaAccount;
import com.indexzero.santaService.services.SantaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/santa-register")
public class SantaRegisterController {

    @Autowired
    private SantaService santaService;

    @ModelAttribute
    public SantaAccount getSantaAccount() {
        return new SantaAccount();
    }

    @GetMapping("")
    public String home() {
        return "santa-registration";
    }
    @PostMapping("")
    public String addSantaProfile(@ModelAttribute SantaAccount santaAccount) {
        santaService.save(santaAccount);
        return "redirect:/santa-register";
    }
    
}
