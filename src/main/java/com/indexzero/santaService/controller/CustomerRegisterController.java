package com.indexzero.santaService.controller;

import com.indexzero.santaService.model.CustomerAccount;
import com.indexzero.santaService.model.UserAccount;
import com.indexzero.santaService.services.CustomerAccountService;
import com.indexzero.santaService.services.UserAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer-register")
public class CustomerRegisterController {

    /* @Autowired
    private CustomerAccountService customerAccountService; */
    @Autowired
    private CustomerAccountService customerAccountService;

    @ModelAttribute
    private UserAccount getCustomer() {
        return new UserAccount();
    }

    @GetMapping("")
    public String home() {
        return "customer-registration";
    }
    @PostMapping("")
    public String register(@ModelAttribute UserAccount customerAccount) {
        System.out.println();
        System.out.println("Luodaan tiliä");
        System.out.println(customerAccount);
        System.out.println();
        customerAccountService.createCustomerAccount(customerAccount);
        return "redirect:/customer-register";
    }
    
}
