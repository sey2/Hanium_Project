package com.fishing.demo.api.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    private final UsersService usersService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UsersService usersService, PasswordEncoder passwordEncoder) {
        this.usersService = usersService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Implement the logic to fetch user details from your database or user
        // repository
        // and return a UserDetails object (e.g.,
        // org.springframework.security.core.userdetails.User).
        // You may also need to handle exceptions if the user is not found.
        // Example implementation:
        return usersService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public boolean authenticateUser(String username, String password) {
        // Implement the logic to authenticate the user by comparing the provided
        // password
        // with the hashed password stored in the database.
        // Example implementation:
        UserDetails userDetails = loadUserByUsername(username);
        return passwordEncoder.matches(password, userDetails.getPassword());
    }
}
