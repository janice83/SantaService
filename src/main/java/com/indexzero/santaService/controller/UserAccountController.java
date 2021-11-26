package com.indexzero.santaService.controller;

import java.util.List;

import com.indexzero.santaService.model.SantaProfile;
import com.indexzero.santaService.model.UserAccount;
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

    /* Create */

    /* Read */

    /* Include only what needed, santa roles */
    @ResponseBody
    @RequestMapping(value = "santa-users", 
    method = RequestMethod.GET, 
    produces = "application/json")
    public List<UserAccount> getNewSantas() {
        return  userAccountService.getNewSantas();
    }

    /* Update */

    /* Delete */

}
