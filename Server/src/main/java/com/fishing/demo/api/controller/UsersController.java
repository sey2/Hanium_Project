package com.fishing.demo.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import com.fishing.demo.api.dto.request.JoinRequestDto;
import com.fishing.demo.api.dto.response.LoginResponseDto;
import com.fishing.demo.api.dto.response.TokenResponseDto;
import com.fishing.demo.api.service.UsersService;


public class UsersController {
    private final UsersService usersService;


    @ApiOperation(value = "중복 확인")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 409, message = "이미 존재하는 아이디입니다")
    })
    @GetMapping(value = "/check/{user-id}", produces = "application/text;charset = utf-8")
    public ResponseEntity<String> checkDuplicate(@PathVariable(name = "user-id") String userId) {

        usersService.checkDuplicate(userId);
        return ResponseEntity.ok().body("중복되는 아이디가 없습니다.");
    }

    @ApiOperation(value = "회원 가입")
    @PostMapping(value = "/join", produces = "application/text;charset = utf-8")
    public ResponseEntity<String> join(@RequestBody JoinRequestDto joinDto) {
        return ResponseEntity.ok().body(usersService.join(joinDto));
    }


    @ApiOperation(value = "로그인")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "아이디를 찾을 수 없습니다"),
            @ApiResponse(code = 401, message = "비밀번호가 일치하지 않습니다")
    })
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginResponseDto loginDto) {

        TokenResponseDto token = usersService.login(loginDto.getUserId(), loginDto.getPassword());
        return ResponseEntity.ok().body(token);
    }

    @ApiOperation(value = "토큰 재발급")
    @GetMapping("/reissue")
    public ResponseEntity<TokenResponseDto> reissue(@RequestHeader("RefreshToken") String refreshToken) {
        return ResponseEntity.ok().body(usersService.reissue(refreshToken));
    }

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }
}
