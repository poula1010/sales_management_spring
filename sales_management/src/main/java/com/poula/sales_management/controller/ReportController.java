package com.poula.sales_management.controller;

import com.poula.sales_management.dto.ClientReportDto;
import com.poula.sales_management.dto.DateRangeDto;
import com.poula.sales_management.dto.ProductReportDto;
import com.poula.sales_management.dto.SalesReportDto;
import com.poula.sales_management.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/report")
public class ReportController {
    private ReportService reportService;
    @Autowired
    public ReportController(ReportService reportService){
        this.reportService = reportService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/sales")
    public ResponseEntity<SalesReportDto> generateSalesReport(@RequestBody DateRangeDto dateRangeDto){
        SalesReportDto salesReportDto = this.reportService.generateSalesReport(dateRangeDto);
        return new ResponseEntity<>(salesReportDto, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/client")
    public ResponseEntity<ClientReportDto> generateClientReport(){
        ClientReportDto clientReportDto = this.reportService.generateClientReport();
        return new ResponseEntity<>(clientReportDto,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/product")
    public ResponseEntity<ProductReportDto> generateProductReport(){
        ProductReportDto productReportDto = this.reportService.generateProductReport();
        return new ResponseEntity<>(productReportDto,HttpStatus.OK);
    }
}
