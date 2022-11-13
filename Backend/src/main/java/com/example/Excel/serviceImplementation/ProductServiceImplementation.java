package com.example.Excel.serviceImplementation;

import com.example.Excel.entity.Product;
import com.example.Excel.repository.ProductRepository;
import com.example.Excel.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImplementation implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) throws Exception {
        if (product.hasId()) {
            return save(product);
        } else {
            throw new InvalidDnDOperationException("Product id has been required for update operation");
        }
    }

    @Override
    public void deleteByIds(Long... ids) {
        productRepository.deleteAllById(Arrays.asList(ids));
    }

    @Override
    public List<Product> getDataByIds(Long... ids) {
        return productRepository.getByIds(Arrays.asList(ids));
    }

    @Override
    public List<Product> getData() {
        return productRepository.findAll();
    }

    @Override
    public Object[][] getProductIds() {
        return productRepository.getProductIds();
    }

    @Override
    public Integer findTotalValue() {
        return productRepository.findAllValue();
    }

    @Override
    public Integer findTotal() {
        return productRepository.findTotal();
    }
}
