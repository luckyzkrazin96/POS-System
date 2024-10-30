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

import com.ijse.pos.entity.Item;
import com.ijse.pos.dto.ItemRequestDto;
import com.ijse.pos.entity.Category;
import com.ijse.pos.service.CategoryService;
import com.ijse.pos.service.ItemService;

@RestController
@CrossOrigin(origins = "*")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/items")
    public ResponseEntity<Item> createItem(@RequestBody ItemRequestDto itemRequestDto) {
        try {
            // System.out.println("1");
            Item newItem = new Item();
            newItem.setName(itemRequestDto.getName());
            newItem.setPrice(itemRequestDto.getPrice());
            newItem.setDescription(itemRequestDto.getDescription());
            // System.out.println("Name : "+newItem.getName()+", Price :
            // "+newItem.getPrice()+", Description : "+newItem.getDescription()+",
            // categoryID : "+itemRequestDto.getCategoryId());
            Category category = categoryService.getCategoryById(itemRequestDto.getCategoryId());
            newItem.setCategory(category);
            // System.out.println("Category : "+ newItem.getCategory().getName());

            Item createdItem = itemService.createItem(newItem);
            return ResponseEntity.status(201).body(createdItem);

        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return ResponseEntity.status(200).body(items);
    }

    @PutMapping("items/{itemId}")
    public ResponseEntity<Item> updateItem(@PathVariable Long itemId, @RequestBody ItemRequestDto itemRequestDto) {
        Item item = new Item();
        item.setName(itemRequestDto.getName());
        item.setDescription(itemRequestDto.getDescription());
        item.setPrice(itemRequestDto.getPrice());

        Category category = categoryService.getCategoryById(itemRequestDto.getCategoryId());
        item.setCategory(category);

        Item updatedItem = itemService.updateItem(itemId, item);

        if (updatedItem == null) {
            return ResponseEntity.status(404).body(null);
        } else {
            return ResponseEntity.status(200).body(updatedItem);
        }
    }

    @DeleteMapping("items/{itemId}")
    public ResponseEntity<String> deleteItem(@PathVariable Long itemId) {
        itemService.deleteItem(itemId);
        return ResponseEntity.status(200).body("Item deleted");
    }
}
