package com.example.Excel.serviceImplementation;

import com.example.Excel.entity.Stock;
import com.example.Excel.repository.StockRepository;
import com.example.Excel.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockServiceImplementation implements StockService {
    private final StockRepository stockRepository;

    @Override
    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public Stock update(Stock stock) throws Exception {
        if (stock.hasId()) {
            return save(stock);
        } else {
            throw new InvalidDnDOperationException("Stock id has been required for update operation");
        }
    }

    @Override
    public void deleteByIds(Long... ids) {
        stockRepository.deleteAllById(Arrays.asList(ids));
    }

    @Override
    public List<Stock> getDataByIds(Long... ids) {
        return stockRepository.getByIds(Arrays.asList(ids));
    }

    @Override
    public List<Stock> getData() {
        return stockRepository.findAll();
    }

    @Override
    public Integer findTotalValue() {
        return stockRepository.findAllValue();
    }

    @Override
    public Integer findTotal() {
        return stockRepository.findTotal();
    }
}
