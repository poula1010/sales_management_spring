package com.poula.sales_management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleDto {
    private int id;
    private int clientId;
    private int sellerId;
    private List<SaleDetailDto> saleDetailDtoList;

}
