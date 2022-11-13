package com.example.Excel.serviceImplementation;

import com.example.Excel.entity.Sales;
import com.example.Excel.entity.SalesProduct;
import com.example.Excel.repository.ProductRepository;
import com.example.Excel.repository.SaleRepository;
import com.example.Excel.repository.SalesProductRepository;
import com.example.Excel.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.dnd.InvalidDnDOperationException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleServiceImplementation implements SaleService {
    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private final SalesProductRepository salesProductRepository;

    @Transactional
    @Override
    public Sales save(Sales sales) {

        for (int i = 0; i < sales.getProduct().size(); i++) {
            SalesProduct product = sales.getProduct().get(i);
            product = salesProductRepository.save(product);
            sales.getProduct().set(i, product);
            productRepository.updateQtyLeft(product.getSoldQuantity(), product.getProductId());
        }

        sales.setTransactionDate(new Date());
        sales.setInvoiceNumber("RS-" + new Date().getTime());
        return saleRepository.save(sales);
    }

    @Override
    public Sales update(Sales sales) throws Exception {
        if (sales.hasId()) {
            return save(sales);
        } else {
            throw new InvalidDnDOperationException("Sales id has been required for update operation");
        }
    }

    @Override
    public void deleteByIds(Long... ids) {
        saleRepository.deleteAllById(Arrays.asList(ids));
    }

    @Override
    public List<Sales> getDataByIds(Long... ids) {
        return saleRepository.getByIds(Arrays.asList(ids));
    }

    @Override
    public List<Sales> getData() {
        return saleRepository.findAll();
    }

    @Override
    public String getLastInsertId() {
        return saleRepository.getLastInsertId();
    }

    @Override
    public Integer findTotalAmount() {
        return saleRepository.findTotalAmount();
    }

    @Override
    public List<Sales> findTotalSale() {
        return saleRepository.findTotalSale();
    }

    @Override
    public Object[][] findSale() {
        return saleRepository.findSale();
    }

    @Override
    public Object[][] findSaleByDate() {
        return saleRepository.findSaleByDate();
    }

    @Override
    public List<Sales> getDateRangeValue(Date start, Date end) {
        return saleRepository.getAllBetweenDates(start, end);
    }

    @Override
    public List<Sales> getTotalBetweenDates(Date start, Date end) {
        return saleRepository.getTotalBetweenDates(start, end);
    }

    @Override
    public List<Sales> getIdDateRangeValue(List<Long> ids, Date start, Date end) {
        return saleRepository.getByIdsBetween(ids, start, end)
                .stream()
                .map(Sales::new)
                .collect(Collectors.toList());
    }
}
