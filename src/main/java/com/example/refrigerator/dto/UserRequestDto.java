package com.example.refrigerator.dto;

import com.example.refrigerator.domain.Refrigerator;
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
public class UserRequestDto {

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String password;

    public User toEntity() {
        return User.builder()
                .username(username)
                .email(email)
                .password(password)
                .build();
    }
}
