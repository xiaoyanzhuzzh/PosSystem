import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PosSystemTest {
    String cartFile = "testFiles/cart.txt";

    @BeforeEach
    void cleanFile() throws IOException {
        if (Files.exists(Paths.get(cartFile))) {
            Files.delete(Paths.get(cartFile));
        }
    }

    @Test
    void should_return_empty_cart_when_pos_system_load_file() {
        PosSystem posSystem = new PosSystem();
        Cart cart = posSystem.loadCart(cartFile);

        assertThat(cart.getItemsSize(), is(0));
    }

    @Test
    void should_return_cart_have_one_item_when_file_have_one_line() throws IOException {
        String oneItem = "ITEM000001";
        initFileWithContext(cartFile, oneItem);

        PosSystem posSystem = new PosSystem();
        Cart cart = posSystem.loadCart(cartFile);

        assertThat(cart.getItemsSize(), is(1));
    }

    @Test
    void should_return_correct_num_of_item_when_file_have_item_with_num() throws IOException {
        String oneItemWithNum = "ITEM000001-2";
        initFileWithContext(cartFile, oneItemWithNum);

        PosSystem posSystem = new PosSystem();
        Cart cart = posSystem.loadCart(cartFile);

        assertThat(cart.getItemsSize(), is(2));
    }

    private void initFileWithContext(String file, String context) throws IOException {
        Files.write(Paths.get(file), Collections.singleton(context));
    }
}
