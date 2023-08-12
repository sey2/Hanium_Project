package com.fishing.demo.api.controller;

import com.fishing.demo.api.dto.request.LoginRequestDto;
import com.fishing.demo.api.dto.response.LoginResponseDto;
import com.fishing.demo.api.service.AuthenticationService;
import com.fishing.demo.utils.JwtTokenUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthenticationController(AuthenticationService authenticationService, JwtTokenUtil jwtTokenUtil) {
        this.authenticationService = authenticationService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // Authenticate the user
        boolean isAuthenticated = authenticationService.authenticateUser(username, password);

        if (!isAuthenticated) {
            // Return an error response if authentication fails
            return ResponseEntity.badRequest().build();
        }

        // Generate a JWT token
        String token = jwtTokenUtil.createToken(username);

        // Create and return the login response with the JWT token
        LoginResponseDto response = new LoginResponseDto();
        response.setToken(token);
        response.setUsername(username);
        return ResponseEntity.ok(response);
    }
}
