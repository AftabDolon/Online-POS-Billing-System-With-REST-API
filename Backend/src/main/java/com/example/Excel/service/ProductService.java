package com.example.Excel.service;

import com.example.Excel.entity.Product;

public interface ProductService extends BaseService<Product, Long> {

    Object[][] getProductIds();
    Integer findTotalValue();
    Integer findTotal();
}
