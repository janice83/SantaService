package com.indexzero.santaService.controller;

import com.indexzero.santaService.model.CustomerAccount;
import com.indexzero.santaService.repositories.CustomerAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("customer-register")
public class CustomerRegisterController {

    @Autowired
    private CustomerAccountRepository cAccountRepository;

    @ModelAttribute
    private CustomerAccount getCustomer() {
        return new CustomerAccount();
    }

    @GetMapping("")
    public String home() {
        return "customer-registration";
    }
    @PostMapping("")
    public String register(@ModelAttribute CustomerAccount cAccount) {
        cAccountRepository.save(cAccount);
        return "redirect:/customer-register";
    }
    
}
