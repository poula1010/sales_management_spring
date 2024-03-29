package com.poula.sales_management.controller;

import ch.qos.logback.core.net.server.Client;
import com.poula.sales_management.dto.ClientDetailDto;
import com.poula.sales_management.dto.ClientRegisterDto;
import com.poula.sales_management.dto.SuccessOrFailDto;
import com.poula.sales_management.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("permitAll()")
@RequestMapping("/user")
public class ClientController {
    private ClientService clientService;
    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }
    @PostMapping
    public ResponseEntity<SuccessOrFailDto> addNewClient(@RequestBody ClientRegisterDto clientRegisterDto){
        SuccessOrFailDto successOrFailDto = this.clientService.addNewClient(clientRegisterDto);
        return new ResponseEntity<>(successOrFailDto, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ClientDetailDto>> getClients(){
        return new ResponseEntity<>(this.clientService.getAllClients(),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<ClientDetailDto> updateClient(@RequestBody ClientDetailDto clientDetailDto){
        ClientDetailDto updatedClientDto = this.clientService.updateClientDetails(clientDetailDto);
        return new ResponseEntity<>(updatedClientDto,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessOrFailDto> deleteClientById(@PathVariable int id){
        SuccessOrFailDto successOrFailDto = clientService.deleteClientById(id);
        return new ResponseEntity<>(successOrFailDto,HttpStatus.OK);
    }
}
