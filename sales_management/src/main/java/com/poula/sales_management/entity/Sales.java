package com.poula.sales_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="sales")
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    private int id;

    private LocalDateTime creationTime;

    @OneToOne()
    @JoinColumn(name = "seller_id",referencedColumnName = "user_id")
    private User seller;

    @OneToOne()
    @JoinColumn(name = "client_id",referencedColumnName = "user_id")
    private User client;

    private double total;

    @OneToMany(mappedBy = "sales")
    private List<SaleDetail> saleDetailList;
}
