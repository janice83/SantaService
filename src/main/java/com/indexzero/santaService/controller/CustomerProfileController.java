package com.indexzero.santaService.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


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
    
}
