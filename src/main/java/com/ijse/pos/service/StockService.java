package com.ijse.pos.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.ijse.pos.entity.Stock;

@Service
public interface StockService {
    Stock createStock(Stock stock);

    List<Stock> getAllStocks();

    Stock updateStock(Long id, Stock stock);

    void deleteStock(Long id);
}
