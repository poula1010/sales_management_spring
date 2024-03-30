package com.poula.sales_management.dto;

import com.poula.sales_management.entity.SaleDetail;
import com.poula.sales_management.entity.Sales;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaleDetailDto {
    private int id;
    private int productId;
    private int quantity;
    private double subTotal;

    public static SaleDetailDto toSaleDetailDto(SaleDetail saleDetail){
        SaleDetailDto saleDetailDto = new SaleDetailDto();
        saleDetailDto.setId(saleDetail.getId());
        saleDetailDto.setQuantity(saleDetail.getQuantity());
        saleDetailDto.setProductId(saleDetail.getProduct().getId());
        saleDetailDto.setSubTotal(saleDetail.getSubTotal());
        return saleDetailDto;
    }
}
