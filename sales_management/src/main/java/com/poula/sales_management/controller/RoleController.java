package com.poula.sales_management.controller;

import com.poula.sales_management.dto.RoleDto;
import com.poula.sales_management.dto.SuccessOrFailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.poula.sales_management.service.RoleService;
@RestController
@RequestMapping("/role")
public class RoleController {
    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<SuccessOrFailDto> addRole(@RequestBody RoleDto role){
        SuccessOrFailDto successOrFailDto = this.roleService.addRole(role.getRoleName());
        return new ResponseEntity<>(successOrFailDto,HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<SuccessOrFailDto> deleteRole(@RequestBody RoleDto role){
        SuccessOrFailDto successOrFailDto = this.roleService.removeRole(role.getRoleName());
        return new ResponseEntity<>(successOrFailDto,HttpStatus.OK);
    }
}
