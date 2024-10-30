package com.ijse.pos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.pos.entity.Stock;
import com.ijse.pos.repository.StockRepository;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Override
    public Stock createStock(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    @Override
    public Stock updateStock(Long id, Stock stock) {
        Stock excistingStock = stockRepository.findById(id).orElse(null);

        if (excistingStock == null) {
            return null;
        } else {
            excistingStock.setQty(stock.getQty());
            return stockRepository.save(excistingStock);
        }
    }

    @Override
    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }

}
