package com.poula.sales_management.service;

import com.poula.sales_management.dto.ClientRegisterDto;
import com.poula.sales_management.dto.SuccessOrFailDto;

public interface ClientService {
    SuccessOrFailDto addNewClient(ClientRegisterDto clientRegisterDto);
}
