package com.programming.dobby.entity;

import com.programming.dobby.dto.RegisterRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long id;
    private String email;
    private String username;
    private String password;
    private String role;

    public static User fromRegisterRequest(RegisterRequest registerRequest) {
        return User.builder()
                .email(registerRequest.getEmail())
                .username(registerRequest.getUsername())
                .password(registerRequest.getPassword())
                .role("ROLE_USER")
                .build();
    }
}
