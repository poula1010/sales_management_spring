package com.poula.sales_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
public class LocationDto {
    private String address;
    private int count;

    public static LocationDto entryToDto(Map.Entry<String,Integer> entry){
        LocationDto locationDto = new LocationDto();
        locationDto.setAddress(entry.getKey());
        locationDto.setCount(entry.getValue());
        return locationDto;
    }
}
