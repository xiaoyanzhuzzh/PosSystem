package com.github.liuyuhang997.possystem.promotions;

import com.github.liuyuhang997.possystem.entities.Item;
import com.github.liuyuhang997.possystem.utils.FileUtil;
import com.github.liuyuhang997.possystem.utils.TestUtil;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;

import static com.github.liuyuhang997.possystem.enums.FileNameEnum.SECOND_HALF_PRICE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

public class SecondHalfPriceTest {
    private final String ITEM = "ITEM00001";
    private final String SECOND_HALF_PRICE_PROMOTION_PATH = TestUtil.getResourcePath(SECOND_HALF_PRICE.getName());

    @AfterEach
    void emptyFile() throws IOException {
        TestUtil.deleteFile(SECOND_HALF_PRICE_PROMOTION_PATH);
        TestUtil.initFileWithContext(SECOND_HALF_PRICE_PROMOTION_PATH, Lists.newArrayList());
    }

    @Test
    void should_return_second_half_price_promotion_when_pos_system_load_promotion_file() {
        Promotion promotion = FileUtil.loadPromotion(SECOND_HALF_PRICE);

        assertThat(promotion.getPromotionItems().size(), is(0));
    }

    @Test
    void should_return_second_half_price_promotion_has_one_item_when_pos_system_load_promotion_file() throws IOException {
        TestUtil.initFileWithContext(SECOND_HALF_PRICE_PROMOTION_PATH, Collections.singletonList(ITEM));

        Promotion promotion = FileUtil.loadPromotion(SECOND_HALF_PRICE);

        assertThat(promotion.getPromotionItems().keySet(), contains(ITEM));
    }

    @Test
    void should_return_item_with_promoted_subtotal_when_item_in_promotion() {
        Item item = Item.builder().name(ITEM).num(2d).price(1d).build();
        SecondHalfPrice secondHalfPrice = new SecondHalfPrice();
        secondHalfPrice.addItem(ITEM);

        double subtotal = secondHalfPrice.calculatePromotion(item);
        assertThat(subtotal, is(1.5d));
    }

    @Test
    void should_return_item_with_not_promoted_subtotal_when_item_not_in_promotion() {
        Item item = Item.builder().name(ITEM).num(2d).price(1d).build();
        SecondHalfPrice secondHalfPrice = new SecondHalfPrice();

        double subtotal = secondHalfPrice.calculatePromotion(item);
        assertThat(subtotal, is(2d));
    }
}
