package com.github.liuyuhang997.possystem.promotions;

public class BuyTwoGetOneFree extends Promotion {
    @Override
    public void addItem(String lineFromFile) {
        promotionItems.put(lineFromFile, null);
    }
}