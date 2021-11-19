package com.indexzero.santaService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SantaProfileController {

    @GetMapping("/login")
    public String getSantaProfilePage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()) {
            System.out.println();
            System.out.println("Autentikointi toimii");
            System.out.println(auth.getDetails());
            System.out.println();
            return "redirect:/santa-profile";
        }
        System.out.println();
        System.out.println("Autentikointi EI toimi");
        System.out.println();
        return "redirect:/login";
    }

    @GetMapping("/santa-profile")
    public String santaProfileView(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if (auth.isAuthenticated()) {
            System.out.println();
            System.out.println("Santa profiilissa");
            System.out.println(auth.getDetails());
            System.out.println(auth.getName());
            System.out.println(auth.getPrincipal());
            System.out.println();
        }
        model.addAttribute("authuser", auth.getCredentials());
        return "santa-profile";
    }

    @PostMapping("/login")
    public String redirectAfterLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()) {
            System.out.println();
            System.out.println("Autentikointi toimii");
            System.out.println();
            return "redirect:/santa-profile";
        }
        System.out.println();
        System.out.println("Autentikointi EI toimi");
        System.out.println();
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

}
