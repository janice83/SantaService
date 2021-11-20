package com.indexzero.santaService.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@RequestMapping("/santa")
@Controller
public class SantaProfileController {

    /* Show actual profile page with contextual content per user: */
    // @Secured("ROLE_SANTA")
    @GetMapping("/santa-profile")
    public String santaProfileView(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println();
        System.out.println("Uudelleen ohjaus /testi");
        System.out.println();
        if (isAuthenticated()) {
            System.out.println();
            System.out.println("Santa profiilissa");
            System.out.println(auth.getDetails()); System.out.println(auth.getName());
            System.out.println(auth.getPrincipal());       
            System.out.println();

        }
        return "santa-profile";
    }

    /* @PostMapping("/santa-login")
    public String redirectSantaProfile() {
        if (isAuthenticated()) {
            System.out.println();
            System.out.println("Kirjautuminen onnistui");
            System.out.println();
            return "redirect:/santa-profile";
        }
        System.out.println();
        System.out.println("Kirjautuminen epäonnistui");
        System.out.println();
        return "santa";
    } */

    private boolean isAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.isAuthenticated();
    }

}
