package com.github.liuyuhang997.possystem.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Item {
    private String name;
    private Double num;

    public static Item parseItem(String lineFromFile) {
        String[] itemAndNum = lineFromFile.split("-");
        if (1 == itemAndNum.length) {
            return Item.builder().name(itemAndNum[0]).num(1d).build();
        } else {
            Double num = Double.parseDouble(itemAndNum[1]);
            return Item.builder().name(itemAndNum[0]).num(num).build();
        }
    }
}
