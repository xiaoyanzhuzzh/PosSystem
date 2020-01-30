package com.github.liuyuhang997.possystem.promotions;

import com.github.liuyuhang997.possystem.PosSystem;
import com.github.liuyuhang997.possystem.entities.Item;
import com.github.liuyuhang997.possystem.utils.TestUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static com.github.liuyuhang997.possystem.enums.FileNameEnum.BUY_TWO_GET_ONE_FREE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

public class BuyTwoGetOneFreeTest {
    private String buyTwoGetOneFreePromotionPath = TestUtil.getResourcePath(BUY_TWO_GET_ONE_FREE.getName());
    private PosSystem posSystem;

    @BeforeEach
    void setUp() {
        posSystem = new PosSystem("ShopTest");
    }

    @AfterEach
    void emptyFile() throws IOException {
        TestUtil.deleteFile(buyTwoGetOneFreePromotionPath);
        TestUtil.initFileWithContext(buyTwoGetOneFreePromotionPath, new ArrayList<>());
    }

    @Test
    void should_return_buy_two_get_one_free_promotion_when_pos_system_load_promotion_file() {
        Promotion promotion = posSystem.loadPromotion(BUY_TWO_GET_ONE_FREE);

        assertThat(promotion.getPromotionItems().size(), is(0));
    }

    @Test
    void should_return_buy_two_get_one_free_promotion_has_one_item_when_pos_system_load_promotion_file() throws IOException {
        String oneItem = "ITEM000001";
        TestUtil.initFileWithContext(buyTwoGetOneFreePromotionPath, Collections.singletonList(oneItem));

        Promotion promotion = posSystem.loadPromotion(BUY_TWO_GET_ONE_FREE);

        assertThat(promotion.getPromotionItems().keySet(), contains(oneItem));
    }

    @Test
    void should_return_item_with_promoted_subtotal_when_item_in_promotion() {
        Item item = Item.builder().name("ITEM000001").num(5d).price(1d).build();
        BuyTwoGetOneFree buyTwoGetOneFree = new BuyTwoGetOneFree();
        buyTwoGetOneFree.addItem("ITEM000001");

        buyTwoGetOneFree.calculatePromotion(item);
        assertThat(item.getSubtotal(), is(4d));
    }

    @Test
    void should_return_item_with_not_promoted_subtotal_when_item_not_in_promotion() {
        Item item = Item.builder().name("ITEM000001").num(5d).price(1d).build();
        BuyTwoGetOneFree buyTwoGetOneFree = new BuyTwoGetOneFree();

        buyTwoGetOneFree.calculatePromotion(item);
        assertThat(item.getSubtotal(), is(5d));
    }
}
