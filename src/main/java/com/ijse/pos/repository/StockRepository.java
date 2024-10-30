package com.ijse.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.pos.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

}
