package com.example.Excel.controller;

import com.example.Excel.entity.Product;
import com.example.Excel.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/product/")
@RequiredArgsConstructor
public class ProductController implements BaseController<Product, Long> {
    @Autowired
    private ProductService productService;


    //all information save purpose
    @Override
    public ResponseEntity<Product> save(@RequestBody Product products) {
        productService.save(products);
        return ResponseEntity.ok(products);
    }


    //all information update purpose
    @Override
    public ResponseEntity<String> update(@RequestBody Product products) throws Exception {
        try {
            productService.update(products);
            return ResponseEntity.ok("Successfully product information has been updated");
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }


    //single or multiple user information delete purpose
    @Override
    public ResponseEntity<String> deleteByIds(@PathVariable("ids") Long... ids) {
        productService.deleteByIds(ids);
        return ResponseEntity.ok(null);
    }


    //single information get purpose
    @Override
    public ResponseEntity<List<Product>> getDataByIds(@PathVariable("ids") Long... ids) {
        List<Product> productList = productService.getDataByIds(ids);
        return ResponseEntity.ok(productList);
    }

    //all information get purpose
    @Override
    public List<Product> getData() {
        return productService.getData();
    }

    @GetMapping("id-list")
    public Object[][] getProductIds() {
        return productService.getProductIds();
    }

    @GetMapping("countAll")
    public Integer getTotalProduct() {

        return productService.findTotalValue();
    }

    @GetMapping("countBellow")
    public Integer getTotal() {

        return productService.findTotal();
    }

}
