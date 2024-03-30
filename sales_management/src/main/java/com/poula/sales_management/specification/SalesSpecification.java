package com.poula.sales_management.specification;

import com.poula.sales_management.entity.Sales;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class SalesSpecification {
    public static Specification<Sales> betweenDates(LocalDateTime start, LocalDateTime end){
        return(
                Root<Sales> root,
        CriteriaQuery<?> query,
                CriteriaBuilder builder
                )-> builder.between(root.get("creationTime"),start,end);
    }
}
