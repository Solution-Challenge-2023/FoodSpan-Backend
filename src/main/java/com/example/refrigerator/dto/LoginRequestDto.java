package com.example.refrigerator.dto;

import com.example.refrigerator.domain.User;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequestDto {

    @NotNull
    private String username;

    @NotNull
    private String password;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }
}
