package com.example.Excel.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "sales")
public class Sales extends BaseEntity {

    private Date transactionDate;
    private String customerName;
    private String invoiceNumber;
    private Double totalAmount;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "tbl_sales_product",
            joinColumns = @JoinColumn(name = "sale_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<SalesProduct> product;


    @Transient
    private Double vat;

    @Transient
    private String productName;
    @Transient
    private Double originalPrice;
    private Double customerPay;

    public Sales() {
    }

    public Sales(Tuple tuple) {
        transactionDate = tuple.get("transaction_date", Date.class);
        customerName = tuple.get("customer_name", String.class);
        invoiceNumber = tuple.get("invoice_number", String.class);
        totalAmount = ((Number) tuple.get("total_amount")).doubleValue();
        productName = tuple.get("product_name", String.class);
        originalPrice = ((Number) tuple.get("original_price")).doubleValue();
    }

    public Double getCalculatePrice() {
        return customerPay == null ? 0.0 : customerPay - totalAmount;
    }

    public Double getCalculateVat() {
        return totalAmount == null ? 0.0 : totalAmount / 100 * 5;
    }
}
