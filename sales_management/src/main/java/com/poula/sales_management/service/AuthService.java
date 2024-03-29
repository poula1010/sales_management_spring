package com.poula.sales_management.service;

import com.poula.sales_management.dto.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}
