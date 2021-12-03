package com.indexzero.santaService.controller;

import java.util.List;

import com.indexzero.santaService.model.SantaProfile;
import com.indexzero.santaService.model.UserAccount;
import com.indexzero.santaService.services.SantaProfileService;
import com.indexzero.santaService.services.UserAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//@RequestMapping("/santa")
@Controller
public class SantaProfileController {

    @Autowired
    private SantaProfileService santaProfileService;

    @Autowired
    private UserAccountService userAccountService;

    /* Show actual profile page with contextual content per user: */
    // @Secured("ROLE_SANTA")
    @GetMapping("/santa-profile")
    public String santaProfileView(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println();
        System.out.println("Uudelleen ohjaus /testi");
        System.out.println();
        if (isAuthenticated()) {
            System.out.println();
            System.out.println("Santa profiilissa");
            System.out.println(auth.getDetails());
            System.out.println(auth.getName());
            System.out.println(auth.getPrincipal());
            System.out.println();

        }
        return "santa-profile";
    }
    /* av santas */
    @ResponseBody
    @RequestMapping(
        value = "santas/available",
        method = RequestMethod.GET,
        produces = "application/json"
    )
    public List<SantaProfile> getAllAvailableSantas() {
        return santaProfileService.getAvailableSantas();
    }
    /* get santas b/postal code */

    private boolean isAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.isAuthenticated();
    }
    

}
