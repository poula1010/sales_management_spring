package com.poula.sales_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "sale_detail")
public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="sale_detail_id")
    private int id;

    @Column(name="quantity")
    private int quantity;

    @OneToOne
    @JoinColumn(name = "product_id",referencedColumnName = "product_id")
    private Product product;

    @Column(name = "sub_total")
    private double subTotal;

    @ManyToOne
    @JoinColumn(name = "sale_id",referencedColumnName = "sale_id")
    private Sales sales;
}
