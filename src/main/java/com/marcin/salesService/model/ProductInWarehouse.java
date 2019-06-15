package com.marcin.salesService.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product_in_warehouse")
public class ProductInWarehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product_id")
    private Product product;

    @Column
    private float price;

    @Column
    private float discountPrice;

    @Column
    private int quantityToDiscount;

}
