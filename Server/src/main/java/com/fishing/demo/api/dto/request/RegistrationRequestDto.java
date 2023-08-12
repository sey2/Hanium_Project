package com.fishing.demo.api.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequestDto {
    private String nickname;
    private String id;
    private String password;
    private String profileImagePath;
    // Other attributes and getter/setter methods as needed
}
