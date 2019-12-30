import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PosSystemTest {
    String cartPath = "testFiles/cart.txt";

    @BeforeEach
    void cleanFile() throws IOException {
        TestUtil.deleteFile(cartPath);
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
        TestUtil.initFileWithContext(cartPath, oneItem);

        PosSystem posSystem = new PosSystem();
        Cart cart = posSystem.loadCart(cartPath);

        assertThat(cart.getItemsSize(), is(1));
    }

    @Test
    void should_return_correct_num_of_item_when_file_have_item_with_num() throws IOException {
        String oneItemWithNum = "ITEM000001-2";
        TestUtil.initFileWithContext(cartPath, oneItemWithNum);

        PosSystem posSystem = new PosSystem();
        Cart cart = posSystem.loadCart(cartPath);

        assertThat(cart.getItemsSize(), is(2));
    }


}
