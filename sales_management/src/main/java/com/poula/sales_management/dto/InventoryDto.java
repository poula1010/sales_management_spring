package com.poula.sales_management.dto;

import lombok.Data;

@Data
public class InventoryDto {
    private int id;
    private String productName;
    private int quantity;
}
