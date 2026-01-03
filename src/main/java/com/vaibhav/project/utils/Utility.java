package com.vaibhav.project.utils;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Utility {
    public static boolean isValidString(String input){
        return null != input && !input.isBlank();
    }
    public static UserDetails loadUserByUsername() throws UsernameNotFoundException {
        return User
                .withUsername("vaibhav")
                .password(new BCryptPasswordEncoder().encode("admin123"))
                .roles("ADMIN")
                .build();
    }
}
