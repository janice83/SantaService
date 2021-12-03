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
public class CustomerController {

    @Autowired
    private UserAccountService userAccountService;

    @ModelAttribute
    private UserAccount getCustomer() {
        return new UserAccount();
    }

    @GetMapping("/customer")
    public String getCustomerPage() {
        return "customer";
    }
    @PostMapping("/customer-register")
    public String register(@ModelAttribute UserAccount customerAccount) {
        System.out.println();
        System.out.println("Luodaan tiliä");
        System.out.println(customerAccount);
        System.out.println();
        userAccountService.createCustomerAccount(customerAccount);
        return "redirect:/customer-register";
    }

}
