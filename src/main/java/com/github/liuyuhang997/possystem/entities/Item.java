package com.github.liuyuhang997.possystem.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Item {
    private String name;
    private Double num;
    private Double price;
    private String unit;
    private Double subtotal;

    public static Item parseItem(String lineFromFile) {
        String[] itemAndNum = lineFromFile.split("-");
        if (1 == itemAndNum.length) {
            return buildItem(itemAndNum[0], 1d);
        } else {
            Double num = Double.parseDouble(itemAndNum[1]);
            return buildItem(itemAndNum[0], num);
        }
    }

    private static Item buildItem(String name, Double num) {
        return Item.builder().name(name).num(num).price(1d).unit("KG").build();
    }

    public Double getSubtotal() {
        return round(subtotal);
    }

    public double getOriginalPrice() {
        return round(num * price);
    }

    private double round(Double num) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(num));
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }
}
