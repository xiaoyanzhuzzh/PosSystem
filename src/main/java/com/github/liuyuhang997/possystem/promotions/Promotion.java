package com.github.liuyuhang997.possystem.promotions;

import java.util.HashMap;
import java.util.Map;

public class Promotion {

    private Map<String, Double> promotionItems;

    public Map<String, Double> getPromotionItems() {
        return promotionItems;
    }

    public Promotion() {
        promotionItems = new HashMap<>();
    }

    public void addItem(String lineFromFile) {
        this.promotionItems.put(lineFromFile, null);
    }
}
