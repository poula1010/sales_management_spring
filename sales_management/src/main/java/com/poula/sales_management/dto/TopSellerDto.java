package com.poula.sales_management.dto;

import com.poula.sales_management.entity.User;
import lombok.Data;

@Data
public class TopSellerDto {
    private int id;
    private String name;
    private String lastName;

    public static TopSellerDto toTopSellerDto(User user){
       TopSellerDto topSellerDto =   new TopSellerDto();
       topSellerDto.setId(user.getId());
       topSellerDto.setName(user.getFirstName());
       topSellerDto.setLastName(user.getLastName());
       return topSellerDto;
    }
}
