package com.example.Excel.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "manufacture_date")
    private String manufactureDate;

    @Column(name = "expire_date")
    private String expireDate;

    @Column(name = "original_price")
    private Double originalPrice;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "qty_left")
    private Integer qtyLeft;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private Suppliers suppliers;

    @Transient
    private Double salePrice;


    public Double getSalePrice() {
        return originalPrice == null ? 0.0 : originalPrice + (originalPrice / 100 * 5);
    }
}
