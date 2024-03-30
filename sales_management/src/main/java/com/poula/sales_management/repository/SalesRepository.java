package com.poula.sales_management.repository;

import com.poula.sales_management.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface SalesRepository extends JpaRepository<Sales,Integer> , JpaSpecificationExecutor<Sales> {


}
