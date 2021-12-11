package com.indexzero.santaService.controller;

import java.util.Optional;

import com.indexzero.santaService.model.UserAccount;
import com.indexzero.santaService.repositories.UserAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CustomLoginController {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @GetMapping("/login-page")
    public String redirectLogin(Model model) {
        return "custom-login-page";
    }

    @GetMapping("/success")
    public String redirectAfterSuccess() {
        System.out.println();
        System.out.println("After success!");
        System.out.println();
        Optional<UserAccount> userAccount = userAccountRepository.findByUsername(getAuthentication().getName());
        if (userAccount.isPresent()) {
            System.out.println();
            System.out.println("Käyttäjätili löytyi!");
            System.out.println();
            /* redirect atributes here? */
            return userAccount.get().getUserRole().equals("ROLE_SANTA") ? "redirect:/santa-profile"
                    : userAccount.get().getUserRole().equals("ROLE_CUSTOMER") ? "redirect:/customer-profile"
                            : "redirect:/";
        }
        System.out.println();
        System.out.println("Ei löytänyt autentikointia!");
        System.out.println();

        return "redirect:/";
    }

    @GetMapping("/custom-success")
    public String successLogout(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("logoutInfo", "Uloskirjautuminen onnistui");
        return "redirect:/login-page";
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
