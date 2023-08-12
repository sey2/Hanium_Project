package com.fishing.demo.api.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LoginRequestDto {
    private String userId;
    private String password;
}
