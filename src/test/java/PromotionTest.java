import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PromotionTest {
    String buyTwoGetOneFreePromotionPath = "testFiles/buy_two_get_one_free_promotion.txt";

    @BeforeEach
    void cleanFile() throws IOException {
        TestUtil.deleteFile(buyTwoGetOneFreePromotionPath);
    }

    @Test
    void should_return_discount_promotion_when_pos_system_load_promotion_file() {
        PosSystem posSystem = new PosSystem();
        Promotion promotion = posSystem.loadPromotion(buyTwoGetOneFreePromotionPath);

        assertThat(promotion.getPromotionItemsSize(), is(0));
    }

    @Test
    void should_return_discount_promotion_has_one_item_when_pos_system_load_promotion_file() throws IOException {
        String oneItem = "ITEM000001";
        TestUtil.initFileWithContext(buyTwoGetOneFreePromotionPath, oneItem);

        PosSystem posSystem = new PosSystem();
        Promotion promotion = posSystem.loadPromotion(buyTwoGetOneFreePromotionPath);

        assertThat(promotion.getPromotionItemsSize(), is(1));
    }
}
