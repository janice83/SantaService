package com.indexzero.santaService.security;

import java.util.Arrays;
import java.util.Optional;

import com.indexzero.santaService.model.SantaAccount;
import com.indexzero.santaService.repositories.SantaAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Profile("sec")
@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private SantaAccountRepository santaAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SantaAccount> santaAccount = santaAccountRepository.findByUsername(username);
        if (santaAccount.isPresent()) {
            return new org.springframework.security.core.userdetails.User(
                santaAccount.get().getUsername(),
                santaAccount.get().getPassword(),
                true,
                true,
                true,
                true,
                Arrays.asList(
                    new SimpleGrantedAuthority(santaAccount.get().getUserRole()))
            );
        }
        throw new UsernameNotFoundException("No such email: "+username);
    }
    
}
