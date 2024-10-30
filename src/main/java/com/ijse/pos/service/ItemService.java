package com.ijse.pos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.pos.entity.Item;

@Service
public interface ItemService {
    Item createItem(Item item);

    List<Item> getAllItems();

    Item updateItem(Long id, Item item);

    void deleteItem(Long id);

    Item getItemById(Long id);
    
}
