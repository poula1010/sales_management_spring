package com.poula.sales_management.controller;

import com.poula.sales_management.dto.SaleDetailDto;
import com.poula.sales_management.dto.SaleDto;
import com.poula.sales_management.dto.SalesPreviewDto;
import com.poula.sales_management.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sales")
public class SalesController {
    private SalesService salesService;
    @Autowired
    public SalesController(SalesService salesService){
        this.salesService = salesService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<SalesPreviewDto> addSale(@RequestBody SaleDto saleDto){
       SalesPreviewDto salesPreviewDto= this.salesService.addSale(saleDto);
       return new ResponseEntity<>(salesPreviewDto, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<SalesPreviewDto>> getSales(){
        List<SalesPreviewDto> salesPreviewDtoList = this.salesService.getAllSales();
        return new ResponseEntity<>(salesPreviewDtoList,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<SalesPreviewDto> updateSale(@RequestBody SaleDto saleDto){
        SalesPreviewDto salesPreviewDto = this.salesService.updateSale(saleDto);
        return new ResponseEntity<>(salesPreviewDto,HttpStatus.OK);
    }
}
