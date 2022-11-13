package com.example.Excel.service;

import com.example.Excel.entity.Stock;

public interface StockService extends BaseService<Stock, Long> {

    Integer findTotalValue();

    Integer findTotal();
}
