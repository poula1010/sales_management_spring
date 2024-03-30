package com.poula.sales_management.dto;

import com.poula.sales_management.entity.Sales;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesPreviewDto {

    private int id;
    private LocalDateTime creation_time;
    private String seller;
    private String Client;
    private double total;

    public static SalesPreviewDto toSalesPreviewDto(Sales sale){
        SalesPreviewDto salesPreviewDto = new SalesPreviewDto();
        salesPreviewDto.setId(sale.getId());
        salesPreviewDto.setCreation_time(sale.getCreationTime());
        salesPreviewDto.setSeller(sale.getSeller().getFirstName());
        salesPreviewDto.setClient(sale.getClient().getFirstName());
        salesPreviewDto.setTotal(sale.getTotal());
        return salesPreviewDto;
    }
}
