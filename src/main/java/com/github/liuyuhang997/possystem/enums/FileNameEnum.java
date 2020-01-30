package com.github.liuyuhang997.possystem.enums;

import lombok.Getter;

@Getter
public enum FileNameEnum {
    CART("cart.txt"),
    BUY_TWO_GET_ONE_FREE("buy_two_get_one_free_promotion.txt"),
    DISCOUNT("discount_promotion.txt"),
    SECOND_HALF_PRICE("second_half_price_promotion.txt");

    private String name;

    FileNameEnum(String name) {
        this.name = name;
    }
}

