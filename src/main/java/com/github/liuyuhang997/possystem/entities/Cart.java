package com.github.liuyuhang997.possystem.entities;

import java.util.HashMap;
import java.util.Map;

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
        } else {
            items.put(item.getName(), item);
        }
    }

    public Double getItemsSize() {
        return items.values().stream().mapToDouble(Item::getNum).sum();
    }
}
