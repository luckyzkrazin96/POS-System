package com.ijse.pos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.pos.dto.StockRequestDto;
import com.ijse.pos.entity.Item;
import com.ijse.pos.entity.Stock;
import com.ijse.pos.service.ItemService;
import com.ijse.pos.service.StockService;

@RestController
@CrossOrigin(origins = "*")
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private ItemService itemService;

    @PostMapping("/stocks")
    public ResponseEntity<?> createStock(@RequestBody StockRequestDto stockRequestDto) {
        try {
            Stock newStock = new Stock();
            newStock.setQty(stockRequestDto.getQty());

            Item item = itemService.getItemById(stockRequestDto.getItemId());
            newStock.setItem(item);

            Stock createdStock = stockService.createStock(newStock);
            return ResponseEntity.status(201).body(createdStock);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/stocks")
    public ResponseEntity<List<Stock>> getAllStocks() {
        List<Stock> stocks = stockService.getAllStocks();
        return ResponseEntity.status(200).body(stocks);
    }

    @PutMapping("/stocks/{stockId}")
    public ResponseEntity<Stock> updateStock(@PathVariable Long stockId, @RequestBody StockRequestDto stockRequestDto) {
        Stock stock = new Stock();
        stock.setQty(stockRequestDto.getQty());

        Item item = itemService.getItemById(stockRequestDto.getItemId());
        stock.setItem(item);

        Stock updatedStock = stockService.updateStock(stockId, stock);

        if (updatedStock == null) {
            return ResponseEntity.status(404).body(null);
        } else {
            return ResponseEntity.status(200).body(updatedStock);
        }
    }

    @DeleteMapping("/stocks/{stockId}")
    public ResponseEntity<String> deleteStock(@PathVariable Long stockId) {
        stockService.deleteStock(stockId);
        return ResponseEntity.status(200).body("Stock deleted");
    }
}
