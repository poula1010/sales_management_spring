package com.poula.sales_management.service;

import com.poula.sales_management.dto.ClientReportDto;
import com.poula.sales_management.dto.DateRangeDto;
import com.poula.sales_management.dto.ProductReportDto;
import com.poula.sales_management.dto.SalesReportDto;

import java.time.LocalDateTime;

public interface ReportService {

    public SalesReportDto generateSalesReport(DateRangeDto dateRangeDto);

    public ClientReportDto generateClientReport();

    public ProductReportDto generateProductReport();
}
