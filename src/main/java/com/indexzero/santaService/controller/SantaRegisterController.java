package com.indexzero.santaService.controller;

import com.indexzero.santaService.model.UserAccount;
import com.indexzero.santaService.services.UserAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("santa-register")
public class SantaRegisterController {

    @Autowired
    private UserAccountService userAccountService;

    @ModelAttribute
    public UserAccount getUserAccount() {
        return new UserAccount();
    }

    @GetMapping("")
    public String home() {
        return "santa-registration";
    }

    @PostMapping("")
    public String addSantaProfile(@ModelAttribute UserAccount santaAccount) {
        System.out.println();
        System.out.println("Luodaan tiliä");
        System.out.println(santaAccount);
        System.out.println();
        userAccountService.saveSantaAccount(santaAccount);
        return "redirect:/santa-register";
    }

}
