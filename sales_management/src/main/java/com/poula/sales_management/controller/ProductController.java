package com.poula.sales_management.controller;

import com.poula.sales_management.dto.ProductDto;
import com.poula.sales_management.dto.SuccessOrFailDto;
import com.poula.sales_management.repository.ProductRepository;
import com.poula.sales_management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;
    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> products = this.productService.getProducts();
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SuccessOrFailDto> addProduct(@RequestBody ProductDto productDto){
        SuccessOrFailDto successOrFailDto = this.productService.addProduct(productDto);
        return new ResponseEntity<>(successOrFailDto,HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto){
        ProductDto updatedProduct = this.productService.updateProduct(productDto);
        return new ResponseEntity<>(updatedProduct,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessOrFailDto> deleteProductById(@PathVariable int id){
        SuccessOrFailDto successOrFailDto = productService.deleteProductById(id);
        return new ResponseEntity<>(successOrFailDto,HttpStatus.OK);
    }

}
