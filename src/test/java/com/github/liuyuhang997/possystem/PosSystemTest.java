package com.github.liuyuhang997.possystem;

import com.github.liuyuhang997.possystem.utils.TestUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.github.liuyuhang997.possystem.enums.FileNameEnum.BUY_TWO_GET_ONE_FREE;
import static com.github.liuyuhang997.possystem.enums.FileNameEnum.DISCOUNT;
import static com.github.liuyuhang997.possystem.enums.FileNameEnum.SECOND_HALF_PRICE;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class PosSystemTest {
    private final String SHOP_NAME = "ShopTest";
    private final String ITEM = "ITEM00001";
    private final String CART_PATH = TestUtil.getResourcePath("cart.txt");
    private final String BUY_TWO_GET_ONE_FREE_PROMOTION_PATH = TestUtil.getResourcePath(BUY_TWO_GET_ONE_FREE.getName());
    private final String DISCOUNT_PROMOTION_PATH = TestUtil.getResourcePath(DISCOUNT.getName());
    private final String SECOND_HALF_PRICE_PROMOTION_PATH = TestUtil.getResourcePath(SECOND_HALF_PRICE.getName());

    @AfterEach
    void emptyFile() throws IOException {
        TestUtil.deleteFile(CART_PATH);
        TestUtil.deleteFile(BUY_TWO_GET_ONE_FREE_PROMOTION_PATH);
        TestUtil.deleteFile(DISCOUNT_PROMOTION_PATH);
        TestUtil.deleteFile(SECOND_HALF_PRICE_PROMOTION_PATH);
        TestUtil.initFileWithContext(CART_PATH, new ArrayList<>());
        TestUtil.initFileWithContext(BUY_TWO_GET_ONE_FREE_PROMOTION_PATH, new ArrayList<>());
        TestUtil.initFileWithContext(DISCOUNT_PROMOTION_PATH, new ArrayList<>());
        TestUtil.initFileWithContext(SECOND_HALF_PRICE_PROMOTION_PATH, new ArrayList<>());
    }

    @Test
    void should_init_cart_and_promotions_when_init_pos_system() {
        PosSystem posSystemTestInit = new PosSystem(SHOP_NAME);

        assertThat(posSystemTestInit.getCart(), notNullValue());
        assertThat(posSystemTestInit.getPromotions().size(), is(3));
    }

    @Test
    void should_choose_buy_two_get_one_free_when_pos_system_calculate_promotion() throws IOException {
        initCartAndPromotionsFile(asList(ITEM, ITEM, ITEM), ":75");
        PosSystem posSystemTestPromotion = new PosSystem(SHOP_NAME);

        posSystemTestPromotion.calculatePromotion();

        assertThat(posSystemTestPromotion.getCart().getItems().get(ITEM).getSubtotal(), is(2d));
    }

    @Test
    void should_choose_discount_when_pos_system_calculate_promotion() throws IOException {
        initCartAndPromotionsFile(asList(ITEM, ITEM, ITEM), ":65");
        PosSystem posSystemTestPromotion = new PosSystem(SHOP_NAME);

        posSystemTestPromotion.calculatePromotion();

        assertThat(posSystemTestPromotion.getCart().getItems().get(ITEM).getSubtotal(), is(1.95d));
    }

    @Test
    void should_choose_second_half_price_when_pos_system_calculate_promotion() throws IOException {
        initCartAndPromotionsFile(asList(ITEM, ITEM), ":80");
        PosSystem posSystemTestPromotion = new PosSystem(SHOP_NAME);

        posSystemTestPromotion.calculatePromotion();

        assertThat(posSystemTestPromotion.getCart().getItems().get(ITEM).getSubtotal(), is(1.5d));
    }

    private void initCartAndPromotionsFile(List<String> items, String discount) throws IOException {
        TestUtil.initFileWithContext(CART_PATH, items);
        TestUtil.initFileWithContext(BUY_TWO_GET_ONE_FREE_PROMOTION_PATH, singletonList(ITEM));
        TestUtil.initFileWithContext(DISCOUNT_PROMOTION_PATH, singletonList(ITEM + discount));
        TestUtil.initFileWithContext(SECOND_HALF_PRICE_PROMOTION_PATH, singletonList(ITEM));
    }
}
