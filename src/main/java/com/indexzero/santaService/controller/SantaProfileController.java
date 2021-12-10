package com.indexzero.santaService.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
        Authentication auth = getAuthenticatedUser();
        if (auth.isAuthenticated()) {
            Optional<UserAccount> user = userAccountService.findUserAccountByUsername(auth.getName());
            if (user.isPresent()) {
                model.addAttribute("basicInfo", user.get());
                model.addAttribute("profileInfo", user.get().getSantaProfile());
                if (user.get().getSantaProfile().isAvailable()) {
                    model.addAttribute("checkedYes", "true");
                    model.addAttribute("checkedNo", "false");
                } else {
                    model.addAttribute("checkedYes", "false");
                    model.addAttribute("checkedNo", "true");
                }
            }
            return "santa-profile";
        }

        return "redirect:/login-page";

    }

    /* get santas */
    @ResponseBody
    @RequestMapping(value = "santas/available", method = RequestMethod.GET, produces = "application/json")
    public List<SantaProfile> getAllAvailableSantas() {
        return santaProfileService.getSantas();
    }

    /* update santa profile */
    @PostMapping("/update/santa-account")
    public String updateSantaProfile(
            @RequestParam String profilename,
            @RequestParam String info,
            @RequestParam int price,
            @RequestParam int available) {
        System.out.println();
        System.out.println("Profiilinimi:" + profilename);
        System.out.println("Käytettävissä: " + available);
        System.out.println();
        /* get useraccount */
        Optional<UserAccount> userAccount = userAccountService
                .findUserAccountByUsername(getAuthenticatedUser().getName());
        /* get accounts santaprofile */
        SantaProfile existingSantaProfile = userAccount.get().getSantaProfile();
        /* Updated info */
        SantaProfile updatedSantaProfile = new SantaProfile();
        updatedSantaProfile.setSantaProfileName(profilename);
        updatedSantaProfile.setInfo(info);
        updatedSantaProfile.setPrice(price);
        if (available == 0) {
            updatedSantaProfile.setAvailable(false);
        } else if (available == 1) {
            updatedSantaProfile.setAvailable(true);
        }

        santaProfileService.updateSantaProfileInfo(
                userAccount.get(), existingSantaProfile, updatedSantaProfile);

        return "redirect:/success";
    }

    /*  */
    private Authentication getAuthenticatedUser() {
        return SecurityContextHolder.getContext().getAuthentication();

    }

}
