package com.example.Excel.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "stock")
public class Stock extends BaseEntity {

    @Column(name = "material_code")
    private String rawMaterialCode;

    @Column(name = "material_name")
    private String rawMaterialName;

    @Column(name = "receive_date")
    private String materialReceiveDate;

    @Column(name = "expiry_date")
    private String expiryDate;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "material_price")
    private Double materialPrice;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private Suppliers suppliers;

}
