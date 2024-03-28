package com.poula.sales_management.Service.Implementations;

import com.poula.sales_management.DTO.SuccessOrFailDto;
import com.poula.sales_management.Entity.Role;
import com.poula.sales_management.Exception.APIException;
import com.poula.sales_management.Repository.RoleRepository;
import com.poula.sales_management.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.Console;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public SuccessOrFailDto addRole(String roleName) {
        try{
            Role role = new Role();
            role.setName(roleName);
            roleRepository.save(role);
            return new SuccessOrFailDto(true,"role added successfully");
        }
        catch(DataIntegrityViolationException e){
            System.out.println(e.getClass().getName());
            throw new APIException(HttpStatus.BAD_REQUEST,"role Already exists");
        }
        catch (Exception e){
            throw new APIException(HttpStatus.BAD_REQUEST,"An unknown error has occured");
        }
    }

    @Override
    public SuccessOrFailDto removeRole(String roleName) {
        try{
            Role role = roleRepository.findRoleByName(roleName);
            roleRepository.delete(role);
            return new SuccessOrFailDto(true,"role deleted Successfully");
        }
        catch (InvalidDataAccessApiUsageException e){
            throw new APIException(HttpStatus.BAD_REQUEST,"the role doesn't exist");
        }
        catch (Exception e){
            throw new APIException(HttpStatus.BAD_REQUEST,"An unknown error has occured");
        }
    }
}
