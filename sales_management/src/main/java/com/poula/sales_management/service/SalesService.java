package com.poula.sales_management.service;

import com.poula.sales_management.dto.SaleDto;
import com.poula.sales_management.dto.SalesPreviewDto;

import java.util.List;

public interface SalesService {
    SalesPreviewDto addSale(SaleDto saleDto);

    SalesPreviewDto updateSale(SaleDto saleDto);

    List<SalesPreviewDto> getAllSales();
}
