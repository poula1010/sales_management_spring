package com.poula.sales_management.service.Implementations;

import com.poula.sales_management.dto.SaleDto;
import com.poula.sales_management.dto.SalesPreviewDto;
import com.poula.sales_management.entity.Product;
import com.poula.sales_management.entity.SaleDetail;
import com.poula.sales_management.entity.Sales;
import com.poula.sales_management.entity.User;
import com.poula.sales_management.exception.APIException;
import com.poula.sales_management.repository.ProductRepository;
import com.poula.sales_management.repository.SalesRepository;
import com.poula.sales_management.repository.UserRepository;
import com.poula.sales_management.service.ClientService;
import com.poula.sales_management.service.SaleDetailService;
import com.poula.sales_management.service.SalesService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class SalesServiceImpl implements SalesService {
    private SalesRepository salesRepository;
    private UserRepository userRepository;

    private ProductRepository productRepository;
    @Autowired
    public SalesServiceImpl(SalesRepository salesRepository, UserRepository userRepository,ProductRepository productRepository){
        this.salesRepository = salesRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;

    }
    @Override
    @Transactional
    public SalesPreviewDto addSale(SaleDto saleDto) {
        Sales newSale = new Sales();
        try{
            User client = userRepository.findById(saleDto.getClientId()).orElseThrow();
            User seller = userRepository.findById(saleDto.getSellerId()).orElseThrow();
            LocalDateTime creationTime = LocalDateTime.now();
            newSale.setClient(client);
            newSale.setSeller(seller);
            newSale.setCreationTime(creationTime);
            List<SaleDetail> saleDetailList = new ArrayList<>();
            saleDto.getSaleDetailDtoList().forEach(saleDetailDto -> {
                try {
                    SaleDetail newSaleDetail = new SaleDetail();
                    Product product = productRepository.findById(saleDetailDto.getProductId()).orElseThrow();
                    if(saleDetailDto.getQuantity()> product.getAvailableQuantity()){
                        throw new APIException(HttpStatus.BAD_REQUEST,
                                "the requested quantity of product" + product.getName() +"exceeds the available amount");
                    }
                    product.setAvailableQuantity(product.getAvailableQuantity()-saleDetailDto.getQuantity());
                    newSaleDetail.setPriceAtSaleTime(product.getPrice());
                    newSaleDetail.setProduct(product);
                    newSaleDetail.setQuantity(saleDetailDto.getQuantity());
                    double subTotal = newSaleDetail.calculateSubTotal();
                    newSaleDetail.setSubTotal(subTotal);
                    saleDetailList.add(newSaleDetail);
                }
                catch (NoSuchElementException e){
                    throw new APIException(HttpStatus.BAD_REQUEST,"this product doesn't exist");
                }
            });
            newSale.setSaleDetailList(saleDetailList);
            newSale.calculateTotal();
            return SalesPreviewDto.toSalesPreviewDto(salesRepository.save(newSale));
        }
        catch (Exception e){
            throw new APIException(HttpStatus.BAD_REQUEST,"an error has occured");
        }
    }

    @Override
    @Transactional
    public SalesPreviewDto updateSale(SaleDto saleDto) {

        return null;
    }

    @Override
    public List<SalesPreviewDto> getAllSales() {
        List<SalesPreviewDto> salesPreviewDtoList = this.salesRepository.findAll()
                .stream()
                .map(SalesPreviewDto::toSalesPreviewDto)
                .collect(Collectors.toList());
        return salesPreviewDtoList;

    }
}
