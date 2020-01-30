package com.github.liuyuhang997.possystem.promotions;

import com.github.liuyuhang997.possystem.entities.Item;

public class BuyTwoGetOneFree extends Promotion {
    @Override
    public void addItem(String lineFromFile) {
        promotionItems.put(lineFromFile, null);
    }

    @Override
    public void calculatePromotion(Item item) {
        if (promotionItems.containsKey(item.getName())) {
            Double num = item.getNum();
            double remainder = num % 3;
            item.setSubtotal(((num - remainder) / 3 * 2 + remainder) * item.getPrice());
        } else {
            item.setSubtotal(item.getNum() * item.getPrice());
        }
    }
}
