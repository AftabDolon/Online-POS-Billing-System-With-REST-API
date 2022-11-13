package com.example.Excel.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "sale_product")
public class SalesProduct extends BaseEntity {

    private Long productId;
    private int soldQuantity;
    private double soldPrice;
}
