package com.github.liuyuhang997.possystem.entities;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PromotionItemTest {
    @Test
    void should_return_promotion_item_when_parse_promotion_item() {
        PromotionItem promotionItem = PromotionItem.parsePromotionItem("ITEM000001:75");

        assertThat(promotionItem.getName(), is("ITEM000001"));
        assertThat(promotionItem.getDiscount(), is(0.75d));
    }
}
