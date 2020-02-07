package com.github.liuyuhang997.possystem.promotions;

import com.github.liuyuhang997.possystem.entities.Item;
import com.github.liuyuhang997.possystem.entities.PromotionItem;
import com.google.common.collect.Maps;

import java.util.Map;

public abstract class Promotion {

    protected Map<String, PromotionItem> promotionItems;

    public Promotion() {
        promotionItems = Maps.newHashMap();
    }

    public Map<String, PromotionItem> getPromotionItems() {
        return promotionItems;
    }

    public abstract void addItem(String lineFromFile);

    public double calculatePromotion(Item item) {
        if (promotionItems.containsKey(item.getName())) {
            return calculate(item);
        } else {
            return item.getNum() * item.getPrice();
        }
    }

    protected abstract double calculate(Item item);
}
