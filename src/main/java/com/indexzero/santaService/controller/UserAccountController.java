package com.indexzero.santaService.controller;

import java.util.List;

import com.indexzero.santaService.model.UserAccount;
import com.indexzero.santaService.services.UserAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;


    /* Create */
    public String registerUser() {

        return "redirect:/";
    }

    /* Read */
    /* public List<UserAccount> getAllSantaUsers() {
        return userAccountService.getSantaAccounts();
    } */

    /* Update */

    /* Delete */
    
}
