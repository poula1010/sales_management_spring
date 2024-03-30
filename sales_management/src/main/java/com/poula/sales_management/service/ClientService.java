package com.poula.sales_management.service;

import com.poula.sales_management.dto.ClientDetailDto;
import com.poula.sales_management.dto.ClientRegisterDto;
import com.poula.sales_management.dto.SuccessOrFailDto;
import com.poula.sales_management.entity.User;

import java.util.List;

public interface ClientService {
    SuccessOrFailDto addNewClient(ClientRegisterDto clientRegisterDto);

    List<ClientDetailDto> getAllClients();

    SuccessOrFailDto deleteClientById(int id);

    ClientDetailDto updateClientDetails(ClientDetailDto clientDetailDto);

    ClientDetailDto getClientById(int id);
}
