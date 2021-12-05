package com.indexzero.santaService.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.indexzero.santaService.model.UserAccount;
import com.indexzero.santaService.security.CustomUserAccountUserDetailsService;
import com.indexzero.santaService.services.UserAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    private CustomUserAccountUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authManager;

    /* Create */

    /* Read */

    /* Include only what needed, santa roles */
    @ResponseBody
    @RequestMapping(
        value = "santa-users", 
        method = RequestMethod.GET, 
        produces = "application/json")
    public List<UserAccount> getNewSantas() {
        return userAccountService.getNewSantas();
    }

    /* Update basic account info:*/
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
            /* if successful add message and ridirect by role */
            if (success) {
                redirectAttributes.addFlashAttribute("basicInfoUpdated", "updated successfully!");
                System.out.println();
                System.out.println("Onnistui");
                System.out.println();
                return redirectByUserRole();
            } 
            redirectAttributes.addFlashAttribute("basicInfoNotUpdated", "not updated!");
            System.out.println();
            System.out.println("EI onnistunut");
            System.out.println();
            return redirectByUserRole();
            
        }
        System.out.println();
        System.out.println("Tuli tänne");
        System.out.println();
        redirectAttributes.addFlashAttribute("basicInfoNotUpdated", "Incorrect password!");
        return redirectByUserRole();

    }
    /* Update username */
    @PostMapping("/update/account-username")
    public String updateUsername(
        @RequestParam(required = true) String password, 
        @RequestParam String username,
        HttpServletRequest request) {
            Optional<UserAccount> account = userAccountService
                .findUserAccountByUsername(getAuthenticatedUser().getName());
            if (checkIfAuthenticated(password, account.get().getPassword())) {
                userAccountService.updateUsername(account, username);
                refreshAuth(username, password, request);

            }
            return "redirect:/success";
    }

    /* Delete useraccount */
    @PostMapping("/delete-user")
    public String deleteUserAccount(
        @RequestParam(required = true) String password
    ) {
        boolean deleted = false;
        Optional<UserAccount> account = userAccountService
                .findUserAccountByUsername(getAuthenticatedUser().getName());
        if (checkIfAuthenticated(password, account.get().getPassword())) {
            deleted = userAccountService.deleteAccount(account);
            
        }
        return deleted ? "redirect:/logout":"redirect:/";
        
    }

    /*Check auth  */
    private Authentication getAuthenticatedUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /*Check password match*/
    private boolean checkIfAuthenticated(String inputPassword, String existingPassword) {
        return passwordEncoder.matches(inputPassword, existingPassword);
    }

    private void refreshAuth(
            String username,
            String password,
            HttpServletRequest request) {

        UserDetails user = (User) userDetailsService.loadUserByUsername(username);
        
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication auth = authManager.authenticate(authReq);
        
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);

    }
    private String redirectByUserRole() {
        Optional<UserAccount> oldAccount = userAccountService
                .findUserAccountByUsername(getAuthenticatedUser().getName());
        if (oldAccount.isPresent()) {
            String userRole = oldAccount.get().getUserRole();
                return userRole.equals("ROLE_SANTA") ? 
                    "redirect:/santa-profile":"redirect:/";
        }
        return "redirect:/";
    }
    

}
