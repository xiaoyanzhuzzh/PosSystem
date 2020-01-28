import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PromotionTest {
    String buyTwoGetOneFreePromotionPath = PromotionTest.class
            .getResource("buy_two_get_one_free_promotion.txt")
            .getPath();

    @AfterEach
    void emptyFile() throws IOException {
        TestUtil.deleteFile(buyTwoGetOneFreePromotionPath);
        TestUtil.initFileWithContext(buyTwoGetOneFreePromotionPath, new ArrayList<>());
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
        TestUtil.initFileWithContext(buyTwoGetOneFreePromotionPath, Collections.singletonList(oneItem));

        PosSystem posSystem = new PosSystem();
        Promotion promotion = posSystem.loadPromotion(buyTwoGetOneFreePromotionPath);

        assertThat(promotion.getPromotionItemsSize(), is(1));
    }
}
