package com.poula.sales_management.service.Implementations;

import com.poula.sales_management.dto.ClientDetailDto;
import com.poula.sales_management.dto.ProductDto;
import com.poula.sales_management.dto.SuccessOrFailDto;
import com.poula.sales_management.entity.Product;
import com.poula.sales_management.entity.User;
import com.poula.sales_management.exception.APIException;
import com.poula.sales_management.repository.ProductRepository;
import com.poula.sales_management.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public List<ProductDto> getProducts() {
        List<ProductDto> productDtoList = productRepository.findAll().stream().map(ProductDto::toProductDto).collect(Collectors.toList());
        return productDtoList;
    }

    @Override
    public SuccessOrFailDto deleteProductById(int id) {
        try{
            Product product = productRepository.findById(id).orElseThrow(NoSuchElementException::new);
            productRepository.delete(product);
            return new SuccessOrFailDto(true,"Product deleted Successfully");
        }
        catch (NoSuchElementException e){
            throw new APIException(HttpStatus.BAD_REQUEST,"this product doesn't exist");
        }
        catch (Exception e){
            throw new APIException(HttpStatus.BAD_REQUEST,"an error has occurred");
        }
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        try{
            Product product = productRepository.findById(productDto.getId()).orElseThrow();
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setCategory(productDto.getCategory());
            product.setPrice(product.getPrice());
            product.setAvailableQuantity(productDto.getQuantity());
            Product updatedProduct = productRepository.save(product);
            return ProductDto.toProductDto(updatedProduct);
        }
        catch(NoSuchElementException e){
            throw new APIException(HttpStatus.BAD_REQUEST,"This product doesn't exist");
        }
        catch (Exception e){
            throw new APIException(HttpStatus.BAD_REQUEST,"An Error has occurred");
        }
    }

    @Override
    public SuccessOrFailDto addProduct(ProductDto productDto) {
        Product product = ProductDto.toProduct(productDto);
        try{
            if(productRepository.findProductByName(productDto.getName()).isEmpty()){
                productRepository.save(product);
                return new SuccessOrFailDto(true,"product Saved Successfully");
            }
            else{
                throw new APIException(HttpStatus.BAD_REQUEST,"a product with this name already exists");
            }
        }
        catch (Exception e){
            throw new APIException(HttpStatus.BAD_REQUEST,"an error has occurred");
        }
    }
}
