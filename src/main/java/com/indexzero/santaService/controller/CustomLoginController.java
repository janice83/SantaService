package com.indexzero.santaService.controller;

import java.util.Optional;

import com.indexzero.santaService.model.UserAccount;
import com.indexzero.santaService.repositories.UserAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomLoginController {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @GetMapping("/login-page")
    public String redirectLogin() {
        System.out.println();
        System.out.println("Kirjautumissivu!");
        System.out.println();
        return "custom-login-page";
    }
    @GetMapping("/success")
    public String redirectAfterSuccess() {
        System.out.println();
        System.out.println("After success!");
        System.out.println();
        Optional<UserAccount> userAccount = userAccountRepository.findByUsername(getAuthentication().getName());
        if (userAccount.isPresent()) {
            System.out.println();
            System.out.println("Käyttäjätili löytyi!");
            System.out.println();
            return userAccount.get().getUserRole().equals("ROLE_SANTA") ?
                "redirect:/santa-profile" :
                userAccount.get().getUserRole().equals("ROLE_CUSTOMER") ? "redirect:/customer-profile":
                "redirect:/";
        } 
        System.out.println();
        System.out.println("Ei löytänyt autentikointia!");
        System.out.println();
        return "redirect:/";
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
    
}
