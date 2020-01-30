package com.github.liuyuhang997.possystem.promotions;

import com.github.liuyuhang997.possystem.entities.Item;
import com.github.liuyuhang997.possystem.entities.PromotionItem;

import java.util.HashMap;
import java.util.Map;

public abstract class Promotion {

    protected Map<String, PromotionItem> promotionItems;

    public Map<String, PromotionItem> getPromotionItems() {
        return promotionItems;
    }

    public Promotion() {
        promotionItems = new HashMap<>();
    }

    public abstract void addItem(String lineFromFile);

    public void calculatePromotion(Item item) {
        if (promotionItems.containsKey(item.getName())) {
            calculate(item);
        } else {
            item.setSubtotal(item.getNum() * item.getPrice());
        }
    }

    protected abstract void calculate(Item item);
}
