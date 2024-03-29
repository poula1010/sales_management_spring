package com.poula.sales_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

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

}
