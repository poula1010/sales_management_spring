package com.poula.sales_management.service.Implementations;


import com.poula.sales_management.dto.*;
import com.poula.sales_management.entity.*;
import com.poula.sales_management.repository.ProductRepository;
import com.poula.sales_management.repository.RoleRepository;
import com.poula.sales_management.repository.SalesRepository;
import com.poula.sales_management.repository.UserRepository;
import com.poula.sales_management.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


import static com.poula.sales_management.specification.SalesSpecification.betweenDates;

@Service
public class ReportServiceImpl  implements ReportService {
    private SalesRepository salesRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    private ProductRepository productRepository;
    @Autowired
    public ReportServiceImpl(SalesRepository salesRepository,
                             UserRepository userRepository,
                             RoleRepository roleRepository,
                             ProductRepository productRepository){
        this.salesRepository = salesRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.productRepository = productRepository;
    }
    @Override
    public SalesReportDto generateSalesReport(DateRangeDto dateRangeDto) {
        List<Sales> sales = salesRepository.findAll(betweenDates(dateRangeDto.getFrom(),dateRangeDto.getTo()));
        SalesReportDto salesReportDto = new SalesReportDto();
        //Getting and setting the totalNumberOfSales in the date Range;
        salesReportDto.setTotalNumberOfSales(sales.size());

        //Getting the totalRevenue in the date range;
        double totalRevenue = 0.0;
        for (Sales value : sales) {
            totalRevenue += value.getTotal();
        }
        salesReportDto.setTotalRevenue(totalRevenue);

        //getting the list of the top 3 sellers can be made dynamic by replacing the constant in the limit;
        Map<User,Double> sellerTotalMap = new HashMap<>();
        for (Sales sale : sales) {
            User seller = sale.getSeller();
            double total = sellerTotalMap.getOrDefault(seller, 0.0);
            total += sale.getTotal();
            sellerTotalMap.put(seller, total);
        }
        List<TopSellerDto> topSellerList = sellerTotalMap.entrySet().stream().
                sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).
                limit(3).map(Map.Entry::getKey).map(TopSellerDto::toTopSellerDto).toList();

        // getting the list of the top 3 performing products by sales;
        Map<Product,Double> productTotalSaleMap = new HashMap<>();
        for(Sales sale : sales){
            List<SaleDetail> saleDetailList = sale.getSaleDetailList();
            for(SaleDetail saleDetail : saleDetailList){
                Product product = saleDetail.getProduct();
                double total = productTotalSaleMap.getOrDefault(product,0.0);
                total += saleDetail.getSubTotal();
                productTotalSaleMap.put(product,total);
            }
        }
        List<ProductDto> productDtos = productTotalSaleMap.entrySet().stream().
                sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).
                limit(3).map(Map.Entry::getKey).map(ProductDto::toProductDto).toList();
        salesReportDto.setTopSellingProduct(productDtos);

        salesReportDto.setTopPerformingSellers(topSellerList);
        return salesReportDto;
    }

    @Override
    public ClientReportDto generateClientReport() {
        ClientReportDto clientReportDto = new ClientReportDto();
        //Getting a list of clients and the total number of the clients.
        List<User> users = userRepository.findAll();
        Role clientRole = roleRepository.findRoleByName("ROLE_CLIENT");
        List<User> clients = users.stream().filter(user -> user.getRoleSet().contains(clientRole)).toList();
        int clientsNumber = clients.size();
        clientReportDto.setNumberOfClients(clientsNumber);
        //Getting the top 3 spending clients
        List<Sales> sales = salesRepository.findAll();
        Map<User,Double> clientTotalSpentMap = new HashMap<>();
        for(Sales sale : sales){
            User client = sale.getClient();
            Double total = clientTotalSpentMap.getOrDefault(client,0.0);
            total += sale.getTotal();
            clientTotalSpentMap.put(client,total);
        }
        List<ClientDetailDto> topClientList = clientTotalSpentMap.entrySet().stream().
                sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(3).
                map(Map.Entry::getKey).map(ClientDetailDto::toClientDetailDto).toList();
        clientReportDto.setTopSpendingClients(topClientList);
        //generating client location statistics;
        Map<String,Integer> locationCount = new HashMap<>();
        for(User client : clients){
            int count = locationCount.getOrDefault(client.getAddress(),0);
            count++;
            locationCount.put(client.getAddress(),count);
        }
        List<LocationDto> locationDtoList= locationCount.entrySet().stream().map(LocationDto::entryToDto).toList();
        clientReportDto.setLocationStatistics(locationDtoList);
        //generate client activity report
        Map<User,Integer> clientActivityMap = new HashMap<>();
        //initialize Map with zero activity for all clients;
        for(User client : clients){
            clientActivityMap.put(client,0);
        }
        for(Sales sale : sales){
            User client = sale.getClient();
            int previousActivityCount = clientActivityMap.get(client);
            previousActivityCount++;
            clientActivityMap.put(client,previousActivityCount);
        }
        List<ClientActivityDto> clientActivityDtoList = new ArrayList<>();
        for(User client : clients){
            ClientActivityDto clientActivityDto = ClientActivityDto.ClientToClientActivityDto(client,clientActivityMap.get(client));
            clientActivityDtoList.add(clientActivityDto);
        }
        clientReportDto.setClientActivity(clientActivityDtoList);
        return clientReportDto;
    }

    @Override
    public ProductReportDto generateProductReport() {
        ProductReportDto productReportDto = new ProductReportDto();
        List<Product> products = productRepository.findAll();
        List<InventoryDto> inventoryDtoList = new ArrayList<>();
        for(Product product: products){
            InventoryDto inventoryDto = InventoryDto.toInventoryDto(product);
            inventoryDtoList.add(inventoryDto);
        }
        productReportDto.setInventoryStatus(inventoryDtoList);



        return productReportDto;
    }
}
