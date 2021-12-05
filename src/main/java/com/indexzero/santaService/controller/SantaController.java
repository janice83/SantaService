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
public class SantaController {

    @Autowired
    private UserAccountService userAccountService;

    @ModelAttribute
    public UserAccount getUserAccount() {
        return new UserAccount();
    }
    @GetMapping("/santa")
    public String getSantaPage() {
        return "santa-claus";
    }
    /* Create new santa account */
    @PostMapping("/santa-register")
    public String addSantaProfile(@ModelAttribute UserAccount santaAccount) {
        System.out.println();
        System.out.println("Luodaan tiliä");
        System.out.println(santaAccount);
        System.out.println();
        userAccountService.saveSantaAccount(santaAccount);

        return "redirect:/login-page";
    }
    
}
