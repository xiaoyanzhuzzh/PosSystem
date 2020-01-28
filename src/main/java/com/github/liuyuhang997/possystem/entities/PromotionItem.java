package com.github.liuyuhang997.possystem.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PromotionItem {
    protected String name;
    protected Double discount;

    public static PromotionItem parsePromotionItem(String lineFromFile) {
        String[] itemAndNum = lineFromFile.split(":");
        Double discount = Double.parseDouble(itemAndNum[1]) / 100.0;
        return PromotionItem.builder().name(itemAndNum[0]).discount(discount).build();
    }
}
