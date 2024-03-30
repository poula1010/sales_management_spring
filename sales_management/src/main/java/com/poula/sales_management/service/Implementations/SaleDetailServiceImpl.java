package com.poula.sales_management.service.Implementations;

import com.poula.sales_management.dto.SaleDetailDto;
import com.poula.sales_management.entity.Product;
import com.poula.sales_management.entity.SaleDetail;
import com.poula.sales_management.exception.APIException;
import com.poula.sales_management.repository.ProductRepository;
import com.poula.sales_management.repository.SaleDetailRepository;
import com.poula.sales_management.service.SaleDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class SaleDetailServiceImpl implements SaleDetailService {

    private ProductRepository productRepository;

    private SaleDetailRepository saleDetailRepository;
//    @Override
//    public SaleDetail addSaleDetail(SaleDetailDto saleDetailDto) {
//        try {
//            SaleDetail newSaleDetail = new SaleDetail();
//            Product product = productRepository.findById(saleDetailDto.getId()).orElseThrow();
//            newSaleDetail.setPriceAtSaleTime(product.getPrice());
//            newSaleDetail.setProduct(product);
//            newSaleDetail.setQuantity(saleDetailDto.getQuantity());
//            double subTotal = newSaleDetail.calculateSubTotal();
//            newSaleDetail.setSubTotal(subTotal);
//            return saleDetailRepository.save(newSaleDetail);
//
//        }
//        catch (NoSuchElementException e){
//            throw new APIException(HttpStatus.BAD_REQUEST,"this product doesn't exist");
//        }
//        catch (Exception e){
//            throw new APIException(HttpStatus.BAD_REQUEST,"an error has occurred");
//        }
//    }
}
