package com.example.Excel.serviceImplementation;

import com.example.Excel.entity.SalesProduct;
import com.example.Excel.repository.SalesProductRepository;
import com.example.Excel.service.SalesProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesProductServiceImplementation implements SalesProductService {
    private final SalesProductRepository salesProductRepository;

    @Override
    public SalesProduct save(SalesProduct salesProduct) {
        return salesProductRepository.save(salesProduct);
    }

    @Override
    public SalesProduct update(SalesProduct salesProduct) throws Exception {
        if (salesProduct.hasId()) {
            return save(salesProduct);
        } else {
            throw new InvalidDnDOperationException("SalesProduct id has been required for update operation");
        }
    }

    @Override
    public void deleteByIds(Long... ids) {
        salesProductRepository.deleteAllById(Arrays.asList(ids));
    }

    @Override
    public List<SalesProduct> getDataByIds(Long... ids) {
        return salesProductRepository.getByIds(Arrays.asList(ids));
    }

    @Override
    public List<SalesProduct> getData() {
        return salesProductRepository.findAll();
    }

}
