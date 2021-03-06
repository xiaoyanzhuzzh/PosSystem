package com.github.liuyuhang997.possystem.promotions;

import com.github.liuyuhang997.possystem.entities.Item;

public class BuyTwoGetOneFree extends Promotion {
    @Override
    public void addItem(String lineFromFile) {
        promotionItems.put(lineFromFile, null);
    }

    @Override
    public double calculate(Item item) {
        double num = item.getNum();
        double remainder = num % 3;
        return ((num - remainder) / 3 * 2 + remainder) * item.getPrice();
    }
}
