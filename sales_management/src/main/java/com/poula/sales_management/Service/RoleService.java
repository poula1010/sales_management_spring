package com.poula.sales_management.Service;

import com.poula.sales_management.DTO.SuccessOrFailDto;

public interface RoleService {
    SuccessOrFailDto addRole(String roleName);

    SuccessOrFailDto removeRole(String roleName);
}
