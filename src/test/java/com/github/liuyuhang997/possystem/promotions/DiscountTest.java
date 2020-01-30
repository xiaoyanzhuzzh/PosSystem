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

import static com.github.liuyuhang997.possystem.enums.FileNameEnum.DISCOUNT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

public class DiscountTest {
    private final String ITEM = "ITEM000001";
    private final String ITEM_WITH_DISCOUNT = "ITEM000001:75";
    private String discountPromotionPath = TestUtil.getResourcePath(DISCOUNT.getName());
    private PosSystem posSystem;

    @BeforeEach
    void setUp() {
        posSystem = new PosSystem("ShopTest");
    }

    @AfterEach
    void emptyFile() throws IOException {
        TestUtil.deleteFile(discountPromotionPath);
        TestUtil.initFileWithContext(discountPromotionPath, new ArrayList<>());
    }

    @Test
    void should_return_discount_promotion_when_pos_system_load_promotion_file() {
        Promotion promotion = posSystem.loadPromotion(DISCOUNT);

        assertThat(promotion.getPromotionItems().size(), is(0));
    }

    @Test
    void should_return_discount_promotion_has_one_item_when_pos_system_load_promotion_file() throws IOException {
        TestUtil.initFileWithContext(discountPromotionPath, Collections.singletonList(ITEM_WITH_DISCOUNT));

        Promotion promotion = posSystem.loadPromotion(DISCOUNT);

        assertThat(promotion.getPromotionItems().keySet(), contains(ITEM));
        assertThat(promotion.getPromotionItems().get(ITEM).getDiscount(), is(0.75d));
    }

    @Test
    void should_return_item_with_promoted_subtotal_when_item_in_promotion() {
        Item item = Item.builder().name(ITEM).num(1d).price(1d).build();
        Discount discount = new Discount();
        discount.addItem(ITEM_WITH_DISCOUNT);

        discount.calculatePromotion(item);
        assertThat(item.getSubtotal(), is(0.75d));
    }

    @Test
    void should_return_item_with_not_promoted_subtotal_when_item_not_in_promotion() {
        Item item = Item.builder().name(ITEM).num(1d).price(1d).build();
        Discount discount = new Discount();

        discount.calculatePromotion(item);
        assertThat(item.getSubtotal(), is(1d));
    }

}
