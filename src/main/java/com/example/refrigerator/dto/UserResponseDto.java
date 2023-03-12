package com.example.refrigerator.dto;

import com.example.refrigerator.domain.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private Long id;
    private String username;
    private String email;

    public UserResponseDto (User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
