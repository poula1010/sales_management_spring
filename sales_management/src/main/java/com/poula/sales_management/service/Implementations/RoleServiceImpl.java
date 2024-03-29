package com.poula.sales_management.service.Implementations;

import com.poula.sales_management.dto.SuccessOrFailDto;
import com.poula.sales_management.entity.Role;
import com.poula.sales_management.exception.APIException;
import com.poula.sales_management.repository.RoleRepository;
import com.poula.sales_management.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
