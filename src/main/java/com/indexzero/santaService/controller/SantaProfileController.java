package com.indexzero.santaService.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SantaProfileController {

    @GetMapping("/login")
    public String getSantaProfilePage() {
        return "santa-claus";
    }

    /* Show actual profile page with contextual content per user: */
    @Secured("ROLE_SANTA")
    @GetMapping("/profile")
    public String santaProfileView(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println();
        System.out.println("Uudelleen ohjaus /testi");
        System.out.println();
        if (auth.isAuthenticated()) {
            System.out.println();
            System.out.println("Santa profiilissa");
            System.out.println(auth.getDetails());
            System.out.println(auth.getName());
            System.out.println(auth.getPrincipal());
            System.out.println();
        }
        // model.addAttribute("authuser", auth.getName());
        return "santa-profile";
    }

}
