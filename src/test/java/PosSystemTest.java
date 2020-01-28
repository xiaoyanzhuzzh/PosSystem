import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PosSystemTest {
    private String cartPath = PosSystemTest.class.getResource("cart.txt").getPath();

    @AfterEach
    void emptyFile() throws IOException {
        TestUtil.deleteFile(cartPath);
        TestUtil.initFileWithContext(cartPath, new ArrayList<>());
    }

    @Test
    void should_return_empty_cart_when_pos_system_load_file() {
        PosSystem posSystem = new PosSystem();
        Cart cart = posSystem.loadCart(cartPath);

        assertThat(cart.getItemsSize(), is(0));
    }

    @Test
    void should_return_cart_have_one_item_when_file_have_one_line() throws IOException {
        String oneItem = "ITEM000001";
        TestUtil.initFileWithContext(cartPath, Collections.singletonList(oneItem));

        PosSystem posSystem = new PosSystem();
        Cart cart = posSystem.loadCart(cartPath);

        assertThat(cart.getItemsSize(), is(1));
    }

    @Test
    void should_return_correct_num_of_item_when_file_have_item_with_num() throws IOException {
        String oneItemWithNum = "ITEM000001-2";
        TestUtil.initFileWithContext(cartPath, Collections.singletonList(oneItemWithNum));

        PosSystem posSystem = new PosSystem();
        Cart cart = posSystem.loadCart(cartPath);

        assertThat(cart.getItemsSize(), is(2));
    }
}
