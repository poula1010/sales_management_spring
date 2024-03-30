package com.poula.sales_management.dto;

import com.poula.sales_management.entity.User;
import lombok.Data;

@Data
public class ClientActivityDto {
    private int clientId;
    private String clientName;
    private int numberOfOrders;

    public static ClientActivityDto ClientToClientActivityDto(User client,int numberOfOrders){
        ClientActivityDto clientActivityDto=new ClientActivityDto();
        clientActivityDto.setClientId(client.getId());
        clientActivityDto.setNumberOfOrders(numberOfOrders);
        clientActivityDto.setClientName(client.getFirstName());
        return clientActivityDto;
    }
}
