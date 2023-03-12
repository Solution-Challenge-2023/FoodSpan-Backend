package com.example.refrigerator.controller;

import com.example.refrigerator.domain.User;
import com.example.refrigerator.dto.FindPwdDto;
import com.example.refrigerator.dto.LoginRequestDto;
import com.example.refrigerator.dto.UserRequestDto;
import com.example.refrigerator.dto.UserResponseDto;
import com.example.refrigerator.service.UserService;
import com.example.refrigerator.util.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@RequestBody UserRequestDto userRequestDto) {
        User user = userService.save(userRequestDto);
        return ResponseDto.ok(new UserResponseDto(user));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> signin(@RequestBody LoginRequestDto loginRequestDto) {
        UserResponseDto user = userService.login(loginRequestDto);
        return ResponseDto.ok(user);
    }

    // 비번 찾기
    @PostMapping("/find/password")
    public ResponseEntity<String> findPassword(@RequestBody FindPwdDto findPwdDto) {
        String pwd = userService.findPassword(findPwdDto.getUsername());
        return ResponseDto.ok(pwd);
    }

    // 유저 찾기
    @GetMapping("/user/:id")
    public ResponseEntity<UserResponseDto> findOne(@PathVariable Long id) {
        UserResponseDto user = userService.findOne(id);
        return ResponseDto.ok(user);
    }

    // 유저 모두 찾기
    @GetMapping("/user")
    public ResponseEntity<List<UserResponseDto>> findAll() {
        List<UserResponseDto> users = userService.findALl();
        return ResponseDto.ok(users);
    }
}
