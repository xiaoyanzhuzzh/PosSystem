package com.github.liuyuhang997.possystem.factories;

import com.github.liuyuhang997.possystem.enums.PromotionEnum;
import com.github.liuyuhang997.possystem.exceptions.PromotionTypeErrorException;
import com.github.liuyuhang997.possystem.promotions.BuyTwoGetOneFree;
import com.github.liuyuhang997.possystem.promotions.Discount;
import com.github.liuyuhang997.possystem.promotions.Promotion;
import com.github.liuyuhang997.possystem.promotions.SecondHalfPrice;

public class PromotionFactory {

    public static Promotion getPromotion(PromotionEnum promotionEnum) throws PromotionTypeErrorException {
        switch (promotionEnum) {
            case BUY_TWO_GET_ONE_FREE:
                return new BuyTwoGetOneFree();
            case DISCOUNT:
                return new Discount();
            case SECOND_HALF_PRICE:
                return new SecondHalfPrice();
            default:
                throw new PromotionTypeErrorException();
        }
    }
}
