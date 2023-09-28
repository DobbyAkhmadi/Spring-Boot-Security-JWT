package com.programming.dobby.service;

import com.programming.dobby.entity.User;
import com.programming.dobby.dto.LoginRequest;
import com.programming.dobby.dto.LoginResponse;
import com.programming.dobby.dto.RegisterRequest;
import com.programming.dobby.repository.UserRepository;
import com.programming.dobby.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public void register(RegisterRequest registerRequest) {
        var user = User.fromRegisterRequest(registerRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.saveUser(user);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        String token = jwtProvider.generateToken(authenticate);
        return new LoginResponse(token);
    }
}
