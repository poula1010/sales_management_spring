package com.poula.sales_management.dto;

import com.poula.sales_management.entity.Product;
import lombok.Data;

@Data
public class InventoryDto {
    private int productId;
    private String productName;
    private int quantity;

    public static InventoryDto toInventoryDto(Product product){
        InventoryDto inventoryDto = new InventoryDto();
        inventoryDto.setProductId(product.getId());
        inventoryDto.setProductName(product.getName());
        inventoryDto.setQuantity(product.getAvailableQuantity());
        return  inventoryDto;
    }
}
