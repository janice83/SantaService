package com.indexzero.santaService.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.indexzero.santaService.model.UserAccount;
import com.indexzero.santaService.services.UserAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authManager;

    /* Create */

    /* Read */

    /* Include only what needed, santa roles */
    @ResponseBody
    @RequestMapping(value = "santa-users", method = RequestMethod.GET, produces = "application/json")
    public List<UserAccount> getNewSantas() {
        return userAccountService.getNewSantas();
    }

    /* Update basic account info: */
    @PostMapping("/update/account-basic")
    public String updateUserAccount(
            // @RequestParam String username,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String phonenumber,
            @RequestParam String postalCode,
            @RequestParam(required = true) String password,
            RedirectAttributes redirectAttributes) {

        Optional<UserAccount> oldAccount = userAccountService
                .findUserAccountByUsername(getAuthenticatedUser().getName());
        boolean success = false;
        if (oldAccount.isPresent() && checkIfAuthenticated(password, oldAccount.get().getPassword())) {
            UserAccount newAccount = new UserAccount();
            newAccount.setFirstName(firstName);
            newAccount.setLastName(lastName);
            newAccount.setEmail(email);
            newAccount.setPhoneNumber(phonenumber);
            newAccount.setPostalCode(postalCode);
            success = userAccountService.updateAccountInfo(oldAccount, newAccount);
            /* if successful add message and redirect by role */
            return redirectOnSuccess(success, redirectAttributes);

        }
        redirectAttributes.addFlashAttribute("basicInfoNotUpdated", "Incorrect password!");
        return redirectByUserRole();

    }

    /* Update username */
    @PostMapping("/update/account-username")
    public String updateUsername(
            @RequestParam(required = true) String password,
            @RequestParam String username,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes) {
        boolean success = false;
        Optional<UserAccount> account = userAccountService
                .findUserAccountByUsername(getAuthenticatedUser().getName());

        if (checkIfAuthenticated(password, account.get().getPassword())) {
            success = userAccountService.updateUsername(account, username);
            refreshAuth(username, password, request);
            return redirectOnSuccess(success, redirectAttributes);

        }
        redirectAttributes.addFlashAttribute("basicInfoNotUpdated", "Incorrect password!");
        return redirectByUserRole();


    }

    /* Delete useraccount */
    @PostMapping("/delete-user")
    public String deleteUserAccount(
            @RequestParam(required = true) String password) {
        boolean deleted = false;
        Optional<UserAccount> account = userAccountService
                .findUserAccountByUsername(getAuthenticatedUser().getName());
        if (checkIfAuthenticated(password, account.get().getPassword())) {
            try {
                deleted = userAccountService.deleteAccount(account.get());
                /* Success atribute */
            } catch (Exception e) {
                System.out.println("Tiliä ei löytynyt: " + e.getMessage());
                /* Failure atribute */
            }

        }
        /*  */
        return deleted ? "redirect:/logout" : "redirect:/";

    }

    /* Get auth */
    private Authentication getAuthenticatedUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /* Check password match */
    private boolean checkIfAuthenticated(String inputPassword, String existingPassword) {
        return passwordEncoder.matches(inputPassword, existingPassword);
    }
    /* Refresh session */
    private void refreshAuth(
            String username,
            String password,
            HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(username,
                password);
        Authentication auth = authManager.authenticate(authReq);

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);

    }

    private String redirectByUserRole() {
        Optional<UserAccount> oldAccount = userAccountService
                .findUserAccountByUsername(getAuthenticatedUser().getName());
        if (oldAccount.isPresent()) {
            String userRole = oldAccount.get().getUserRole();
            return userRole.equals("ROLE_SANTA") ? "redirect:/santa-profile"
                    : userRole.equals("ROLE_CUSTOMER") ? "redirect:/customer-profile" : "redirect:/";
        }
        return "redirect:/logout";
    }
    private String redirectOnSuccess(boolean success, RedirectAttributes redirectAttributes) {
        if (success) {
            redirectAttributes.addFlashAttribute("basicInfoUpdated", "updated successfully!");
            return redirectByUserRole();
        }
        redirectAttributes.addFlashAttribute("basicInfoNotUpdated", "Error, not updated!");
        return redirectByUserRole();

    }

}
