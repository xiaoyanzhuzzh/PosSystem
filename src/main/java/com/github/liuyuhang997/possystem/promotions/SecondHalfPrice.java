package com.github.liuyuhang997.possystem.promotions;

import com.github.liuyuhang997.possystem.entities.Item;

public class SecondHalfPrice extends Promotion {
    @Override
    public void addItem(String lineFromFile) {
        promotionItems.put(lineFromFile, null);
    }

    @Override
    public double calculate(Item item) {
        Double num = item.getNum();
        double remainder = num % 2;
        return ((num - remainder) * 0.75d + remainder) * item.getPrice();
    }
}
