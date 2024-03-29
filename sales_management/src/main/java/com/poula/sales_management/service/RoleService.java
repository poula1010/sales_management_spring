package com.poula.sales_management.service;

import com.poula.sales_management.dto.SuccessOrFailDto;

public interface RoleService {
    SuccessOrFailDto addRole(String roleName);

    SuccessOrFailDto removeRole(String roleName);
}
