package com.fishing.demo.api.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationResponseDto {
    private Long memberId;
    private String username;
    private String message;

    // Add other fields as needed (e.g., email, name, etc.)
}
