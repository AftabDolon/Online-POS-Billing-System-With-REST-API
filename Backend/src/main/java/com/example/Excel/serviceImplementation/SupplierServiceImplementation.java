package com.example.Excel.serviceImplementation;

import com.example.Excel.entity.Suppliers;
import com.example.Excel.repository.SuppliersRepository;
import com.example.Excel.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierServiceImplementation implements SupplierService {
    private final SuppliersRepository suppliersRepository;

    @Override
    public Suppliers save(Suppliers suppliers) {
        return suppliersRepository.save(suppliers);
    }

    @Override
    public Suppliers update(Suppliers suppliers) throws Exception {
        if (suppliers.hasId()) {
            return save(suppliers);
        } else {
            throw new InvalidDnDOperationException("Supplier id required for update operation");
        }
    }

    @Override
    public void deleteByIds(Long... ids) {
        suppliersRepository.deleteAllById(Arrays.asList(ids));
    }

    @Override
    public List<Suppliers> getDataByIds(Long... ids) {
        return suppliersRepository.getByIds(Arrays.asList(ids));
    }

    @Override
    public List<Suppliers> getData() {
        return suppliersRepository.findAll();
    }

    @Override
    public Object[][] getSupplierIds() {
        return suppliersRepository.getSupplierIds();
    }

    @Override
    public Integer findTotal() {
        return suppliersRepository.findAllValue();
    }
}
