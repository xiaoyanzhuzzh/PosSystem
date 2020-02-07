package com.github.liuyuhang997.possystem.entities;

import com.github.liuyuhang997.possystem.utils.FileUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PromotionItem {
    private String name;
    private Double discount;

    public static PromotionItem parsePromotionItem(String lineFromFile) {
        List<String> itemAndNum = FileUtil.splitLine(":", lineFromFile);
        double discount = Double.parseDouble(itemAndNum.get(1)) / 100.0;
        return PromotionItem.builder().name(itemAndNum.get(0)).discount(discount).build();
    }
}
