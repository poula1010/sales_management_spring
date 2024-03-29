package com.poula.sales_management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientRegisterDto {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;
}
