package com.github.liuyuhang997.possystem.factories;

import com.github.liuyuhang997.possystem.exceptions.PromotionTypeErrorException;
import com.github.liuyuhang997.possystem.promotions.BuyTwoGetOneFree;
import com.github.liuyuhang997.possystem.promotions.Discount;
import com.github.liuyuhang997.possystem.promotions.Promotion;
import com.github.liuyuhang997.possystem.promotions.SecondHalfPrice;
import org.hamcrest.core.IsInstanceOf;
import org.junit.jupiter.api.Test;

import static com.github.liuyuhang997.possystem.enums.PromotionEnum.BUY_TWO_GET_ONE_FREE;
import static com.github.liuyuhang997.possystem.enums.PromotionEnum.DISCOUNT;
import static com.github.liuyuhang997.possystem.enums.PromotionEnum.SECOND_HALF_PRICE;
import static com.github.liuyuhang997.possystem.factories.PromotionFactory.getPromotion;
import static org.hamcrest.MatcherAssert.assertThat;

public class PromotionFactoryTest {
    @Test
    void should_return_buy_two_get_one_free_promotion() throws PromotionTypeErrorException {
        Promotion promotion = getPromotion(BUY_TWO_GET_ONE_FREE);

        assertThat(promotion, new IsInstanceOf(BuyTwoGetOneFree.class));
    }

    @Test
    void should_return_discount_promotion() throws PromotionTypeErrorException {
        Promotion promotion = getPromotion(DISCOUNT);

        assertThat(promotion, new IsInstanceOf(Discount.class));
    }

    @Test
    void should_return_second_half_price_promotion() throws PromotionTypeErrorException {
        Promotion promotion = getPromotion(SECOND_HALF_PRICE);

        assertThat(promotion, new IsInstanceOf(SecondHalfPrice.class));
    }
}
