package com.github.liuyuhang997.possystem.entities;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Cart {

    public Cart() {
        this.items = new HashMap<>();
    }

    private Map<String, Item> items;

    public void addItem(String lineFromFile) {
        Item item = Item.parseItem(lineFromFile);

        if (items.containsKey(item.getName())) {
            Item existedItem = items.get(item.getName());
            existedItem.setNum(existedItem.getNum() + item.getNum());
            existedItem.setSubtotal(existedItem.getOriginalPrice());
        } else {
            item.setSubtotal(item.getOriginalPrice());
            items.put(item.getName(), item);
        }
    }
}
