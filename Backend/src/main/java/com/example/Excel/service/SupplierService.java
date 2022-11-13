package com.example.Excel.service;

import com.example.Excel.entity.Suppliers;

public interface SupplierService extends BaseService<Suppliers, Long> {

    Object[][] getSupplierIds();

    Integer findTotal();
}
