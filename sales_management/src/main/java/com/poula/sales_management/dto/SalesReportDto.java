package com.poula.sales_management.dto;

import com.poula.sales_management.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class SalesReportDto {
    private int totalNumberOfSales;
    private double totalRevenue;
    private List<ProductDto> topSellingProduct;
    private List<User> topPerformingSellers;
}
