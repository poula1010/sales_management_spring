package com.poula.sales_management.dto;

import com.poula.sales_management.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private int id;
    private String name;

    private String description;

    private String category;

    private Integer quantity;

    private double price;

    public static ProductDto toProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setCategory(product.getCategory());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        return productDto;
    }

    public static Product toProduct(ProductDto productDto){
        Product product = new Product();
        product.setName(productDto.getName());
        product.setCategory(productDto.getCategory());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        return product;
    }
}
