package com.poula.sales_management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="sales")
public class Sales {
    private int id;
    private LocalDateTime creationTime;

    @OneToOne()
    @JoinColumn(name = "seller_id",referencedColumnName = "user_id")
    private User seller;

    @OneToOne()
    @JoinColumn(name = "client_id",referencedColumnName = "user_id")
    private User client;

    private BigInteger total;

}
