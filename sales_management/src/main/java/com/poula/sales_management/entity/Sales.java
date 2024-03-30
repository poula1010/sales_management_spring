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

    @ManyToOne()
    @JoinColumn(name = "seller_id",referencedColumnName = "user_id")
    private User seller;

    @ManyToOne()
    @JoinColumn(name = "client_id",referencedColumnName = "user_id")
    private User client;

    private double total;

    @OneToMany(mappedBy = "sales",cascade = CascadeType.ALL)
    private List<SaleDetail> saleDetailList;

    public void calculateTotal(){
        double total = 0.0;
        for(SaleDetail saleDetail : saleDetailList){
            total+= saleDetail.getSubTotal();
        }
        this.total =total;
    }
}
