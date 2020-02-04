package com.github.liuyuhang997.possystem.entities;

import com.github.liuyuhang997.possystem.utils.FileUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Item {
    private final static int ONE = 1;
    private String name;
    private Double num;
    private Double price;
    private String unit;
    private Double subtotal;

    public static Item parseItem(String lineFromFile) {
        List<String> itemAndNum = FileUtil.splitLine("-", lineFromFile);
        if (ONE == itemAndNum.size()) {
            return buildItem(itemAndNum.get(0), 1d);
        } else {
            double num = Double.parseDouble(itemAndNum.get(1));
            return buildItem(itemAndNum.get(0), num);
        }
    }

    private static Item buildItem(String name, Double num) {
        return Item.builder().name(name).num(num).price(1d).unit("KG").build();
    }

    public double getSubtotal() {
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
