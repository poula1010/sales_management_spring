package com.poula.sales_management.dto;

import lombok.Data;

import java.util.List;
@Data
public class ClientReportDto {
    private int numberOfClients;
    private List<ClientDetailDto> topSpendingClients;

    private List<ClientActivityDto> clientActivity;
    private List<LocationDto> locationStatistics;
}
