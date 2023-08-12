package com.fishing.demo.api.controller;

import com.fishing.demo.api.dto.request.RegistrationRequestDto;
import com.fishing.demo.api.dto.response.RegistrationResponseDto;
import com.fishing.demo.api.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponseDto> registerUser(@RequestBody RegistrationRequestDto request) {
        RegistrationResponseDto response = registrationService.registerUser(request);
        return ResponseEntity.ok(response);
    }
}
