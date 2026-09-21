package com.item_springdemo.item_spring.service.impl;

import com.item_springdemo.item_spring.model.Item;
import com.item_springdemo.item_spring.repo.ItemRepo;
import com.item_springdemo.item_spring.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepo itemRepo;
@Autowired
    public ItemServiceImpl(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepo.findAll();
    }

    @Override
    public Item addItem(Item item) {

        return itemRepo.save(item);
    }

    @Override
    public Item updateItem(Item item) {
        return itemRepo.save(item);
    }

    @Override
    public void deleteItem(Long id) {
        itemRepo.deleteById(id);
    }

    @Override
    public Item getItemById(Long id) {
      return  itemRepo.findById(id).get();
    }
}
