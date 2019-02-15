package com.geekbrains.repositories;

import com.geekbrains.entities.Item;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ItemRepository {
    private ArrayList<Item> DataBase;

    public ItemRepository() {
        DataBase = new ArrayList<>();
    }

    public void AddItem(Item it){
        DataBase.add(it);
    }

    public ArrayList<Item> GetAll(){
        return DataBase;
    }
}
