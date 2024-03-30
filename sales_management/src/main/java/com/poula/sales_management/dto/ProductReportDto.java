package com.poula.sales_management.dto;

import lombok.Data;

import java.util.List;
@Data
public class ProductReportDto {
    List<InventoryDto> inventoryStatus;
    List<ProductDto> sortedProductsByPerformance;
}
