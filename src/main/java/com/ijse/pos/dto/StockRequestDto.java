package com.ijse.pos.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockRequestDto {
    private double qty;
    private long itemId;
}
