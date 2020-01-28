import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PromotionTest {
    private String buyTwoGetOneFreePromotionPath = TestUtil.getResourcePath("buy_two_get_one_free_promotion.txt");
    private String secondHalfPricePromotionPath = TestUtil.getResourcePath("second_half_price_promotion.txt");

    @AfterEach
    void emptyFile() throws IOException {
        TestUtil.deleteFile(buyTwoGetOneFreePromotionPath);
        TestUtil.initFileWithContext(buyTwoGetOneFreePromotionPath, new ArrayList<>());
    }

    @Test
    void should_return_buy_two_get_one_free_promotion_when_pos_system_load_promotion_file() {
        PosSystem posSystem = new PosSystem();
        Promotion promotion = posSystem.loadPromotion(buyTwoGetOneFreePromotionPath);

        assertThat(promotion.getPromotionItemsSize(), is(0));
    }

    @Test
    void should_return_buy_two_get_one_free_promotion_has_one_item_when_pos_system_load_promotion_file() throws IOException {
        String oneItem = "ITEM000001";
        TestUtil.initFileWithContext(buyTwoGetOneFreePromotionPath, Collections.singletonList(oneItem));

        PosSystem posSystem = new PosSystem();
        Promotion promotion = posSystem.loadPromotion(buyTwoGetOneFreePromotionPath);

        assertThat(promotion.getPromotionItemsSize(), is(1));
    }

    @Test
    void should_return_second_half_price_promotion_when_pos_system_load_promotion_file() {
        PosSystem posSystem = new PosSystem();
        Promotion promotion = posSystem.loadPromotion(secondHalfPricePromotionPath);

        assertThat(promotion.getPromotionItemsSize(), is(0));
    }

    @Test
    void should_return_second_half_price_promotion_has_one_item_when_pos_system_load_promotion_file() throws IOException {
        String oneItem = "ITEM000001";
        TestUtil.initFileWithContext(secondHalfPricePromotionPath, Collections.singletonList(oneItem));

        PosSystem posSystem = new PosSystem();
        Promotion promotion = posSystem.loadPromotion(secondHalfPricePromotionPath);

        assertThat(promotion.getPromotionItemsSize(), is(1));
    }
}
