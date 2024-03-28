package com.poula.sales_management.Repository;

import com.poula.sales_management.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findRoleByName(String name);
}
