package com.example.Excel.service;

import com.example.Excel.entity.Sales;

import java.util.Date;
import java.util.List;

public interface SaleService extends BaseService<Sales, Long> {
    String getLastInsertId();

    Integer findTotalAmount();

    List<Sales> findTotalSale();
    Object[][] findSale();
    Object[][] findSaleByDate();

    List<Sales> getDateRangeValue(Date start, Date end);

    List<Sales> getTotalBetweenDates(Date start, Date end);

    List<Sales> getIdDateRangeValue(List<Long> ids, Date start, Date end);
}
