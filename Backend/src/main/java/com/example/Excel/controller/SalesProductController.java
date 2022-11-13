package com.example.Excel.controller;

import com.example.Excel.entity.Sales;
import com.example.Excel.entity.SalesProduct;
import com.example.Excel.service.SaleService;
import com.example.Excel.service.SalesProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/saleProduct/")
@RequiredArgsConstructor
public class SalesProductController implements BaseController<SalesProduct, Long> {
    @Autowired
    private SalesProductService salesProductService;


    //all information save purpose
    @Override
    public ResponseEntity<SalesProduct> save(@RequestBody SalesProduct sales) {
        salesProductService.save(sales);
        return ResponseEntity.ok(sales);
    }


    //all information update purpose
    @Override
    public ResponseEntity<String> update(@RequestBody SalesProduct sales) throws Exception {
        try {
            salesProductService.update(sales);
            return ResponseEntity.ok("SalesProduct information has been updated successfully");
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }


    //single or multiple user information delete purpose
    @Override
    public ResponseEntity<String> deleteByIds(@PathVariable("ids") Long... ids) {
        salesProductService.deleteByIds(ids);
        return ResponseEntity.ok("ID " + Arrays.toString(ids) + " has been deleted successfully");
    }


    //single information get purpose
    @Override
    public ResponseEntity<List<SalesProduct>> getDataByIds(@PathVariable("ids") Long... ids) {
        List<SalesProduct> salesList = salesProductService.getDataByIds(ids);
        return ResponseEntity.ok(salesList);
    }

    //all information get purpose
    @Override
    public List<SalesProduct> getData() {
        return salesProductService.getData();
    }

}
