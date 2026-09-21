package com.item_springdemo.item_spring.service;

import com.item_springdemo.item_spring.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {
    List<Item>getAllItems();

    Item addItem(Item item);
    Item updateItem(Item item);
    void deleteItem(Long id);

    Item getItemById(Long id);
}
