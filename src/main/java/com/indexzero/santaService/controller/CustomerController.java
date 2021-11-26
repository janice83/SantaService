package com.indexzero.santaService.controller;

import java.util.List;

import com.indexzero.santaService.model.UserAccount;
import com.indexzero.santaService.repositories.UserAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/customer")
@Controller
public class CustomerController {


    @Autowired
    private UserAccountRepository userAccountRepository;

    @GetMapping("")
    public String getCustomerPage() {
        return "customer";
    }
    
    @ResponseBody
    @RequestMapping(
        value = "santas",
        method = RequestMethod.GET, 
        produces = "application/json")
    public List<UserAccount> getSantas() {
        return userAccountRepository.findAll();
        
    }
}
