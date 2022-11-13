package com.example.Excel.controller;

import com.example.Excel.entity.Sales;
import com.example.Excel.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/sale/")
@RequiredArgsConstructor
public class SalesController implements BaseController<Sales, Long> {
    @Autowired
    private SaleService saleService;


    //all information save purpose
    @Override
    public ResponseEntity<Sales> save(@RequestBody Sales sales) {
        saleService.save(sales);
        return ResponseEntity.ok(sales);
    }


    //all information update purpose
    @Override
    public ResponseEntity<String> update(@RequestBody Sales sales) throws Exception {
        try {
            saleService.update(sales);
            return ResponseEntity.ok("Sales information has been updated successfully");
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }


    //single or multiple user information delete purpose
    @Override
    public ResponseEntity<String> deleteByIds(@PathVariable("ids") Long... ids) {
        saleService.deleteByIds(ids);
        return ResponseEntity.ok("ID " + Arrays.toString(ids) + " has been deleted successfully");
    }


    //single information get purpose
    @Override
    public ResponseEntity<List<Sales>> getDataByIds(@PathVariable("ids") Long... ids) {
        List<Sales> salesList = saleService.getDataByIds(ids);
        return ResponseEntity.ok(salesList);
    }

    //all information get purpose
    @Override
    public List<Sales> getData() {
        return saleService.getData();
    }

    @GetMapping("sale-amount")
    public Integer getTotalAmount() {
        return saleService.findTotalAmount();
    }

    @GetMapping("date")
    public ResponseEntity<List<Sales>> getSaleByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        end.setTime(end.getTime() + 86400000);
        return new ResponseEntity<List<Sales>>(saleService.getDateRangeValue(start, end), HttpStatus.OK);
    }

    @GetMapping("dateById")
    public ResponseEntity<List<Sales>> getSalesByIdDate(@RequestParam List<Long> ids,
                                                        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
                                                        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        end.setTime(end.getTime() + 86400000);
        return new ResponseEntity<>(saleService.getIdDateRangeValue(ids, start, end), HttpStatus.OK);
    }

    @GetMapping("sumAmount")
    public ResponseEntity<List<Sales>> getTotalAmountByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        end.setTime(end.getTime() + 86400000);
        return new ResponseEntity<List<Sales>>(saleService.getTotalBetweenDates(start, end), HttpStatus.OK);
    }

    @GetMapping("sale-list")
    public List<Sales> getTotalSale() {
        return saleService.findTotalSale();
    }

    @GetMapping("sale-value")
    public Object[][] getSale() {
        return saleService.findSale();
    }

    @GetMapping("sale-DateValue")
    public Object[][] getSaleByDate() {
        return saleService.findSaleByDate();
    }
}
