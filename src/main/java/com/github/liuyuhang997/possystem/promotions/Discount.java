package com.github.liuyuhang997.possystem.promotions;

import com.github.liuyuhang997.possystem.entities.Item;
import com.github.liuyuhang997.possystem.entities.PromotionItem;

public class Discount extends Promotion {
    @Override
    public void addItem(String lineFromFile) {
        PromotionItem promotionItem = PromotionItem.parsePromotionItem(lineFromFile);
        promotionItems.put(promotionItem.getName(), promotionItem);
    }

    @Override
    public void calculate(Item item) {
        Double discount = promotionItems.get(item.getName()).getDiscount();
        item.setSubtotal(discount * item.getNum() * item.getPrice());
    }
}
