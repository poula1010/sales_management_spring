package com.poula.sales_management.service;

import com.poula.sales_management.dto.ProductDto;
import com.poula.sales_management.dto.SuccessOrFailDto;
import com.poula.sales_management.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductDto> getProducts();

    SuccessOrFailDto deleteProductById(int id);

    ProductDto updateProduct(ProductDto productDto);

    SuccessOrFailDto addProduct(ProductDto productDto);
}
