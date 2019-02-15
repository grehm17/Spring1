package com.geekbrains.controllers;

import com.geekbrains.entities.Item;
import com.geekbrains.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/Items")
public class ItemController {
    private ItemService itService;

    @Autowired
    public void setStudentsService(ItemService itService) {
        this.itService = itService;
    }
    // http://localhost:8189/app/Items/addItem
    @RequestMapping("/addItem")
    public String AddNewItem(Model model){
        Item newItem = new Item();
        model.addAttribute("Item",newItem);
        return "item-add";
    }

    // http://localhost:8189/app/Items/writeItem
    @RequestMapping("/writeItem")
    public String writeItem(@ModelAttribute("Item") Item it){
        itService.write(it);
        return "success";
    }

    // http://localhost:8189/app/Items/ItemList
    @RequestMapping("/ItemList")
    public String ShowAll(Model model){
        ArrayList<Item> Items = itService.getAll();
        model.addAttribute("Items",Items);
        return "item-list";
    }

}
