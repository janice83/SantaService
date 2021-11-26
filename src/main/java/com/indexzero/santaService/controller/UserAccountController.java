package com.indexzero.santaService.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.indexzero.santaService.model.UserAccount;
import com.indexzero.santaService.repositories.UserAccountRepository;
import com.indexzero.santaService.services.UserAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private UserAccountRepository userAccountRepository;

    /* Create */

    public String registerUser() {

        return "redirect:/";
    }

    /* Read */
    /* get all links */


    /* Get all santas: */
    @ResponseBody
    @RequestMapping(
        value = "santa-users",
        method = RequestMethod.GET,
        produces = "application/json"
    )
    public List<UserAccount> getAllSantaUsers() {
        return userAccountRepository.findAll().stream()
            .filter(user -> user.getUserRole().equals("ROLE_SANTA"))
            .collect(Collectors.toList());
    }

    /* Update */

    /* Delete */
    
}
