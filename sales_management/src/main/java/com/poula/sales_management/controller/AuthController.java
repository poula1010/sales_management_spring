package com.poula.sales_management.controller;

import com.poula.sales_management.dto.JwtAuthResponse;
import com.poula.sales_management.dto.LoginDto;
import com.poula.sales_management.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;
    @Autowired
    public AuthController(AuthService authService){
        this.authService =authService;
    }
    @PreAuthorize("permitAll()")
    @PostMapping
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
        String token =  authService.login(loginDto);
        JwtAuthResponse jwtAuthResponse= new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }
}
