package com.poula.sales_management.service.Implementations;

import com.poula.sales_management.dto.ClientRegisterDto;
import com.poula.sales_management.dto.SuccessOrFailDto;
import com.poula.sales_management.entity.Role;
import com.poula.sales_management.entity.User;
import com.poula.sales_management.exception.APIException;
import com.poula.sales_management.repository.RoleRepository;
import com.poula.sales_management.repository.UserRepository;
import com.poula.sales_management.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

public class ClientServiceImpl implements ClientService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;
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
}
