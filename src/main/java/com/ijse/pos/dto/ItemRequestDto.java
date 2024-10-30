package com.ijse.pos.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRequestDto {
    private String name;
    private Double price;
    private String description;
    private Long categoryId;
}
