package com.example.Excel.controller;

import com.example.Excel.entity.Stock;
import com.example.Excel.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/stock/")
@RequiredArgsConstructor
public class StockController implements BaseController<Stock, Long> {
    @Autowired
    private StockService stockService;


    //all information save purpose
    @Override
    public ResponseEntity<Stock> save(@RequestBody Stock stock) {
        stockService.save(stock);
        return ResponseEntity.ok(stock);
    }


    //all information update purpose
    @Override
    public ResponseEntity<String> update(@RequestBody Stock stock) throws Exception {
        try {
            stockService.update(stock);
            return ResponseEntity.ok("Successfully stock information has been updated");
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }


    //single or multiple user information delete purpose
    @Override
    public ResponseEntity<String> deleteByIds(@PathVariable("ids") Long... ids) {

        stockService.deleteByIds(ids);
        return ResponseEntity.ok("ID: " + Arrays.toString(ids) + " has been deleted successfully");
    }


    //single information get purpose
    @Override
    public ResponseEntity<List<Stock>> getDataByIds(@PathVariable("ids") Long... ids) {
        List<Stock> stockList = stockService.getDataByIds(ids);
        return ResponseEntity.ok(stockList);
    }

    //all information get purpose
    @Override
    public List<Stock> getData() {
        return stockService.getData();
    }


    @GetMapping("countAll")
    public Integer getTotalProduct() {
        return stockService.findTotalValue();
    }

    @GetMapping("countBellow")
    public Integer getTotal() {
        return stockService.findTotal();
    }
}
