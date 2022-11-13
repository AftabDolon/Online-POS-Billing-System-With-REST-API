package com.example.Excel.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "suppliers")
public class Suppliers extends BaseEntity {

    @Column(name = "supplier_id", nullable = false, unique = true)
    private String supplierId;

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "product_details")
    private String productDetails;

}
