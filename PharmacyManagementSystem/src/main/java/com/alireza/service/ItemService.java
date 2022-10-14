package com.alireza.service;

import com.alireza.model.Item;
import com.alireza.repository.ItemRepository;

import java.util.List;

public class ItemService {
    public boolean createItem(Item item) {
        if (findItem(item) == null) {
            ItemRepository.createItem(item);
            return true;
        }
        else {
            return false;
        }
    }

    public void updateItem(Item item){
        ItemRepository.updateItem(item);
    }

    public Item findItem(Item item) {
        return ItemRepository.findItem(item);
    }

    public void deleteItem(Item item){
        ItemRepository.deleteItemById(item);
    }

    public List<Item> showAllItem(){
        return ItemRepository.showAllItem();
    }
}
