package com.example.refrigerator.controller;

import com.example.refrigerator.domain.Item;
import com.example.refrigerator.dto.LoginRequestDto;
import com.example.refrigerator.dto.RefrigeratorRequestDto;
import com.example.refrigerator.dto.UserRequestDto;
import com.example.refrigerator.dto.UserResponseDto;
import com.example.refrigerator.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public Long signup(@RequestBody UserRequestDto userRequestDto) {
        return userService.save(userRequestDto).getId();
    }

    // 로그인
    @PostMapping("/login")
    public Long signin(@RequestBody LoginRequestDto loginRequestDto) {
        return userService.login(loginRequestDto);
    }

    // 비번 찾기
    @PostMapping("/find/password")
    public String findPassword(@RequestBody String username) {
        return userService.findPassword(username);
    }

    // 유저 찾기
    @GetMapping("/user/:id")
    public UserResponseDto findOne(@PathVariable Long id) {
        return userService.findOne(id);
    }

    // 유저 모두 찾기
    @GetMapping("/user")
    public List<UserResponseDto> findAll() {
        return userService.findALl();
    }
}
