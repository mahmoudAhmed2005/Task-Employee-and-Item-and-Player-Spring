package com.item_springdemo.item_spring.controller;

import com.item_springdemo.item_spring.model.Item;
import com.item_springdemo.item_spring.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/items")

public class ItemController {

private ItemService itemService;
@Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // عرض جميع المنتجات
    @GetMapping
    public String getAllItems(Model model) {

        model.addAttribute( "items", itemService.getAllItems());

        return "items";
    }


    // فتح صفحة الإضافة
    @GetMapping("/add")
    public String showAddForm(Model model) {

        model.addAttribute("item", new Item() );

        return "item-form";
    }


    // حفظ منتج جديد
    @PostMapping("/save")
    public String addItem( @ModelAttribute("item") Item item) {

    itemService.addItem(item);
        return "redirect:/items";
    }

    // فتح صفحة التعديل
    @GetMapping("/edit/{id}")
    public String showUpdateForm( @PathVariable Long id, Model model) {


        Item item = itemService.getItemById(id);

        model.addAttribute("item", item);

        return "item-update";
    }



    // حفظ التعديل
    @PostMapping("/update")
    public String updateItem(  @ModelAttribute("item") Item item) {


        itemService.updateItem(item);

        return "redirect:/items";
    }



    // حذف المنتج
    @PostMapping("/delete/{id}")
    public String deleteItem( @PathVariable Long id) {

        itemService.deleteItem(id);

        return "redirect:/items";
    }



}
