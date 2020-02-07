package com.github.liuyuhang997.possystem.promotions;

import com.github.liuyuhang997.possystem.entities.Item;
import com.github.liuyuhang997.possystem.utils.FileUtil;
import com.github.liuyuhang997.possystem.utils.TestUtil;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;

import static com.github.liuyuhang997.possystem.enums.FileNameEnum.BUY_TWO_GET_ONE_FREE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

public class BuyTwoGetOneFreeTest {
    private final String ITEM = "ITEM00001";
    private final String BUY_TWO_GET_ONE_FREE_PROMOTION_PATH = TestUtil.getResourcePath(BUY_TWO_GET_ONE_FREE.getName());

    @AfterEach
    void emptyFile() throws IOException {
        TestUtil.deleteFile(BUY_TWO_GET_ONE_FREE_PROMOTION_PATH);
        TestUtil.initFileWithContext(BUY_TWO_GET_ONE_FREE_PROMOTION_PATH, Lists.newArrayList());
    }

    @Test
    void should_return_buy_two_get_one_free_promotion_when_pos_system_load_promotion_file() {
        Promotion promotion = FileUtil.loadPromotion(BUY_TWO_GET_ONE_FREE);

        assertThat(promotion.getPromotionItems().size(), is(0));
    }

    @Test
    void should_return_buy_two_get_one_free_promotion_has_one_item_when_pos_system_load_promotion_file() throws IOException {
        TestUtil.initFileWithContext(BUY_TWO_GET_ONE_FREE_PROMOTION_PATH, Collections.singletonList(ITEM));

        Promotion promotion = FileUtil.loadPromotion(BUY_TWO_GET_ONE_FREE);

        assertThat(promotion.getPromotionItems().keySet(), contains(ITEM));
    }

    @Test
    void should_return_item_with_promoted_subtotal_when_item_in_promotion() {
        Item item = Item.builder().name(ITEM).num(5d).price(1d).build();
        BuyTwoGetOneFree buyTwoGetOneFree = new BuyTwoGetOneFree();
        buyTwoGetOneFree.addItem(ITEM);

        double subtotal = buyTwoGetOneFree.calculatePromotion(item);
        assertThat(subtotal, is(4d));
    }

    @Test
    void should_return_item_with_not_promoted_subtotal_when_item_not_in_promotion() {
        Item item = Item.builder().name(ITEM).num(5d).price(1d).build();
        BuyTwoGetOneFree buyTwoGetOneFree = new BuyTwoGetOneFree();

        double subtotal = buyTwoGetOneFree.calculatePromotion(item);
        assertThat(subtotal, is(5d));
    }
}
