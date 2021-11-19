package com.indexzero.santaService.controller;

import java.util.List;

import com.indexzero.santaService.model.SantaAccount;
import com.indexzero.santaService.services.SantaAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomerController {

    @Autowired
    private SantaAccountService santaService;

    @GetMapping("/customer")
    public String getCustomerPage() {
        return "customer";
    }
    
    @ResponseBody
    @RequestMapping(
        value = "santas",
        method = RequestMethod.GET, 
        produces = "application/json")
    public List<SantaAccount> getSantas() {
        return santaService.getNewSantas();
    }

}
