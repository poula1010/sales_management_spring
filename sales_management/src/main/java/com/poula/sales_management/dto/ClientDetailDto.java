package com.poula.sales_management.dto;

import com.poula.sales_management.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDetailDto {
    private int id;

    private String firstName;

    private String lastName;

    private String address;

    private String phone;

    private String email;

    public static ClientDetailDto toClientDetailDto(User user){
        ClientDetailDto clientDetailDto = new ClientDetailDto();
        clientDetailDto.setId(user.getId());
        clientDetailDto.setEmail(user.getEmail());
        clientDetailDto.setAddress(user.getAddress());
        clientDetailDto.setPhone(user.getPhone());
        clientDetailDto.setFirstName(user.getFirstName());
        clientDetailDto.setLastName(user.getLastName());

        return clientDetailDto;
    }
}
