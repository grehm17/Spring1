package com.geekbrains.services;

import com.geekbrains.entities.Item;
import com.geekbrains.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ItemService {
    private ItemRepository mainItRep;

    @Autowired
    public void setMainItRep(ItemRepository mainItRep) {
        this.mainItRep = mainItRep;
    }

    public void write(Item it){
        mainItRep.AddItem(it);
    }

    public ArrayList<Item> getAll(){
        return mainItRep.GetAll();
    }
}
