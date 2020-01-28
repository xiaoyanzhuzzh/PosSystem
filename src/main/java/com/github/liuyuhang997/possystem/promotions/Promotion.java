package com.github.liuyuhang997.possystem.promotions;

import java.util.HashMap;
import java.util.Map;

public abstract class Promotion {

    protected Map<String, Double> promotionItems;

    public Map<String, Double> getPromotionItems() {
        return promotionItems;
    }

    public Promotion() {
        promotionItems = new HashMap<>();
    }

    public abstract void addItem(String lineFromFile);
}
