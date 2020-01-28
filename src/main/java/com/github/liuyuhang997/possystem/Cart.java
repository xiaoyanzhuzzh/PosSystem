package com.github.liuyuhang997.possystem;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    public Cart() {
        this.items = new HashMap<>();
    }

    private Map<String, Item> items;

    public void addItem(String lineFromFile) {
        Item item = convertToItem(lineFromFile);

        if (items.containsKey(item.getName())) {
            Item targetItem = items.get(item.getName());
            targetItem.setNum(targetItem.getNum() + item.getNum());
        } else {
            items.put(item.getName(), item);
        }
    }

    private Item convertToItem(String lineFromFile) {
        String[] itemAndNum = lineFromFile.split("-");
        if (1 == itemAndNum.length) {
            return Item.builder().name(itemAndNum[0]).num(1).build();
        } else {
            Integer num = Integer.parseInt(itemAndNum[1]);
            return Item.builder().name(itemAndNum[0]).num(num).build();
        }
    }

    public int getItemsSize() {
        return items.values().stream().mapToInt(Item::getNum).sum();
    }
}
