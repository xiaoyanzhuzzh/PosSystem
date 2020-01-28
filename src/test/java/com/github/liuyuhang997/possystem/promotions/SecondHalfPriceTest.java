package com.github.liuyuhang997.possystem.promotions;

import com.github.liuyuhang997.possystem.PosSystem;
import com.github.liuyuhang997.possystem.exceptions.PromotionTypeErrorException;
import com.github.liuyuhang997.possystem.utils.TestUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static com.github.liuyuhang997.possystem.enums.PromotionEnum.SECOND_HALF_PRICE;
import static com.github.liuyuhang997.possystem.factories.PromotionFactory.getPromotion;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

public class SecondHalfPriceTest {
    private String secondHalfPricePromotionPath = TestUtil.getResourcePath("second_half_price_promotion.txt");

    @AfterEach
    void emptyFile() throws IOException {
        TestUtil.deleteFile(secondHalfPricePromotionPath);
        TestUtil.initFileWithContext(secondHalfPricePromotionPath, new ArrayList<>());
    }

    @Test
    void should_return_second_half_price_promotion_when_pos_system_load_promotion_file() throws PromotionTypeErrorException {
        PosSystem posSystem = new PosSystem();
        Promotion promotion = posSystem.loadPromotion(secondHalfPricePromotionPath, getPromotion(SECOND_HALF_PRICE));

        assertThat(promotion.getPromotionItems().size(), is(0));
    }

    @Test
    void should_return_second_half_price_promotion_has_one_item_when_pos_system_load_promotion_file() throws IOException, PromotionTypeErrorException {
        String oneItem = "ITEM000001";
        TestUtil.initFileWithContext(secondHalfPricePromotionPath, Collections.singletonList(oneItem));

        PosSystem posSystem = new PosSystem();
        Promotion promotion = posSystem.loadPromotion(secondHalfPricePromotionPath, getPromotion(SECOND_HALF_PRICE));

        assertThat(promotion.getPromotionItems().keySet(), contains(oneItem));
    }
}
