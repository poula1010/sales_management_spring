package com.poula.sales_management.service.Implementations;

import com.poula.sales_management.dto.ClientDetailDto;
import com.poula.sales_management.dto.ClientRegisterDto;
import com.poula.sales_management.dto.SuccessOrFailDto;
import com.poula.sales_management.entity.Role;
import com.poula.sales_management.entity.User;
import com.poula.sales_management.exception.APIException;
import com.poula.sales_management.repository.RoleRepository;
import com.poula.sales_management.repository.UserRepository;
import com.poula.sales_management.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;
    @Autowired
    public ClientServiceImpl(UserRepository userRepository,RoleRepository roleRepository,PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.roleRepository=roleRepository;
        this.passwordEncoder=passwordEncoder;
    }
    @Override
    public SuccessOrFailDto addNewClient(ClientRegisterDto clientRegisterDto) {
        try{
            User user = new User();
            user.setFirstName(clientRegisterDto.getFirstName());
            user.setLastName(clientRegisterDto.getLastName());
            user.setEmail(clientRegisterDto.getEmail());
            user.setPassword(passwordEncoder.encode(clientRegisterDto.getPassword()));
            user.setUsername(clientRegisterDto.getUsername());
            user.setAddress(clientRegisterDto.getAddress());
            user.setPhone(clientRegisterDto.getPhone());
            Set<Role> roleSet = new HashSet<>();
            Role client = roleRepository.findRoleByName("ROLE_CLIENT");
            roleSet.add(client);

            user.setRoleSet(roleSet);
            userRepository.save(user);
            return new SuccessOrFailDto(true,"Successful registration");
        }
        catch(Exception e){
            throw new APIException(HttpStatus.BAD_REQUEST,"failed to register");
        }
    }

    @Override
    public List<ClientDetailDto> getAllClients() {
        Role clientRole = roleRepository.findRoleByName("ROlE_CLIENT");
        List<ClientDetailDto> clientList = userRepository.findAll().stream().
                filter(client -> client.getRoleSet().contains(clientRole)).
                map(ClientDetailDto::toClientDetailDto).
                collect(Collectors.toList());
        return clientList;
    }

    @Override
    public SuccessOrFailDto deleteClientById(int id) {
        try{
            User client = userRepository.findById(id).orElseThrow();
            Role clientRole = roleRepository.findRoleByName("ROLE_CLIENT");
            if(client.getRoleSet().contains(clientRole)) {
                userRepository.delete(client);
                return new SuccessOrFailDto(true, "Client deleted successfully");
            }
            else{
                return new SuccessOrFailDto(false,"this User is not a Client");
            }
        }
        catch (NoSuchElementException e){
            throw new APIException(HttpStatus.BAD_REQUEST,"This client doesn't exist");
        }
        catch (Exception e){
            throw new APIException(HttpStatus.BAD_REQUEST,"An Error has occured");
        }
    }

    @Override
    public ClientDetailDto updateClientDetails(ClientDetailDto clientDetailDto) {
        try{
            User client = userRepository.findById(clientDetailDto.getId()).orElseThrow();
            client.setEmail(clientDetailDto.getEmail());
            client.setAddress(clientDetailDto.getAddress());
            client.setPhone(clientDetailDto.getPhone());
            client.setFirstName(clientDetailDto.getFirstName());
            client.setLastName(clientDetailDto.getLastName());
            User updatedClient = userRepository.save(client);
            return ClientDetailDto.toClientDetailDto(updatedClient);
        }
        catch(NoSuchElementException e){
            throw new APIException(HttpStatus.BAD_REQUEST,"This client doesn't exist");
        }
        catch (Exception e){
            throw new APIException(HttpStatus.BAD_REQUEST,"An Error has occurred");
        }
    }

    @Override
    public ClientDetailDto getClientById(int id) {
        try{
            User client = userRepository.findById(id).orElseThrow();
            return ClientDetailDto.toClientDetailDto(client);
        }
        catch(NoSuchElementException e){
            throw new APIException(HttpStatus.BAD_REQUEST,"This client doesn't exist");
        }
        catch (Exception e){
            throw new APIException(HttpStatus.BAD_REQUEST,"An Error has occurred");
        }
    }
}
