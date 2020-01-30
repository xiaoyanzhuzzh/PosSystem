package com.github.liuyuhang997.possystem;

import com.github.liuyuhang997.possystem.entities.Cart;
import com.github.liuyuhang997.possystem.utils.TestUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
    private String cartPath = TestUtil.getResourcePath("cart.txt");
    private String buyTwoGetOneFreePromotionPath = TestUtil.getResourcePath(BUY_TWO_GET_ONE_FREE.getName());
    private String discountPromotionPath = TestUtil.getResourcePath(DISCOUNT.getName());
    private String secondHalfPricePromotionPath = TestUtil.getResourcePath(SECOND_HALF_PRICE.getName());

    private PosSystem posSystem;

    @BeforeEach
    void setUp() {
        posSystem = new PosSystem(SHOP_NAME);
    }

    @AfterEach
    void emptyFile() throws IOException {
        TestUtil.deleteFile(cartPath);
        TestUtil.deleteFile(buyTwoGetOneFreePromotionPath);
        TestUtil.deleteFile(discountPromotionPath);
        TestUtil.deleteFile(secondHalfPricePromotionPath);
        TestUtil.initFileWithContext(cartPath, new ArrayList<>());
        TestUtil.initFileWithContext(buyTwoGetOneFreePromotionPath, new ArrayList<>());
        TestUtil.initFileWithContext(discountPromotionPath, new ArrayList<>());
        TestUtil.initFileWithContext(secondHalfPricePromotionPath, new ArrayList<>());
    }

    @Test
    void should_return_empty_cart_when_pos_system_load_file() {
        Cart cart = posSystem.loadCart(cartPath);

        assertThat(cart.getItems().size(), is(0));
    }

    @Test
    void should_return_cart_have_one_item_when_file_have_one_line() throws IOException {
        String oneItem = ITEM;
        TestUtil.initFileWithContext(cartPath, singletonList(oneItem));

        Cart cart = posSystem.loadCart(cartPath);

        assertThat(cart.getItems().get(oneItem).getNum(), is(1d));
    }

    @Test
    void should_return_correct_num_of_item_when_file_have_item_with_num() throws IOException {
        String oneItemWithNum = ITEM + "-2";
        TestUtil.initFileWithContext(cartPath, singletonList(oneItemWithNum));

        Cart cart = posSystem.loadCart(cartPath);

        assertThat(cart.getItems().get(ITEM).getNum(), is(2d));
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
        TestUtil.initFileWithContext(cartPath, items);
        TestUtil.initFileWithContext(buyTwoGetOneFreePromotionPath, singletonList(ITEM));
        TestUtil.initFileWithContext(discountPromotionPath, singletonList(ITEM + discount));
        TestUtil.initFileWithContext(secondHalfPricePromotionPath, singletonList(ITEM));
    }
}
