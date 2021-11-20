package com.indexzero.santaService.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CustomerProfileController {

    //@Secured("ROLE_CUSTOMER")
    @GetMapping("/customer-profile")
    public String customerProfileView() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println();
        System.out.println("Tuli asiakassivulle!");
        System.out.println(auth.getDetails());
        System.out.println(auth.getName());
        System.out.println(auth.getPrincipal());
        System.out.println();
        return "customer-profile";
    }

    /* @PostMapping("/customer-login")
    public String redirectOnLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (isAuthenticated()) {
            System.out.println();
            System.out.println("Kirjautuminen onnistui");
            System.out.println(auth.getDetails());
            System.out.println(auth.getName());
            System.out.println(auth.getPrincipal());
            System.out.println();
            return "redirect:/customer-profile";
        }
        System.out.println();
        System.out.println("Virhe kirjautuessa");
        System.out.println();
        return "customer";

    } */

    private boolean isAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getDetails());
        return auth.isAuthenticated();
    }

}
