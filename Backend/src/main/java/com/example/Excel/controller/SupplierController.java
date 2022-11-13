package com.example.Excel.controller;

import com.example.Excel.entity.Suppliers;
import com.example.Excel.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/supplier/")
@RequiredArgsConstructor
public class SupplierController implements BaseController<Suppliers, Long> {
    @Autowired
    private SupplierService supplierService;

    //all information save purpose
    @Override
    public ResponseEntity<Suppliers> save(@RequestBody Suppliers supplier) {
        supplierService.save(supplier);
        return ResponseEntity.ok(supplier);
    }

    //all information update purpose
    @Override
    public ResponseEntity<String> update(@RequestBody Suppliers supplier) throws Exception {
        try {
            supplierService.update(supplier);
            return ResponseEntity.ok("Successfully supplier information has been update");
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    //single or multiple user information delete purpose
    @Override
    public ResponseEntity<String> deleteByIds(@PathVariable("ids") Long... ids) {
        supplierService.deleteByIds(ids);
        return ResponseEntity.ok("ID: " + Arrays.toString(ids) + " has been deleted successfully");
    }

    //single information get purpose
    @Override
    public ResponseEntity<List<Suppliers>> getDataByIds(@PathVariable("ids") Long... ids) {

        List<Suppliers> suppliersList = supplierService.getDataByIds(ids);
        return ResponseEntity.ok(suppliersList);
    }

    //all information get purpose
    @Override
    public List<Suppliers> getData() {
        return supplierService.getData();
    }

    @GetMapping("id-list")
    public Object[][] getSupplierIds() {
        return supplierService.getSupplierIds();
    }

    @GetMapping("countAll")
    public Integer getTotalSupplier() {
        return supplierService.findTotal();
    }
}
