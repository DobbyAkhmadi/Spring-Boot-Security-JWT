package com.programming.dobby.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NonNull
    private String username;
    @NonNull
    private String password;
}
