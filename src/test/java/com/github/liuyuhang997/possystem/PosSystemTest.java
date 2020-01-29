package com.github.liuyuhang997.possystem;

import com.github.liuyuhang997.possystem.entities.Cart;
import com.github.liuyuhang997.possystem.utils.TestUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class PosSystemTest {
    private String cartPath = TestUtil.getResourcePath("cart.txt");
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private PosSystem posSystem;

    @BeforeEach
    void setUp() {
        posSystem = new PosSystem("ShopTest");
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void emptyFile() throws IOException {
        TestUtil.deleteFile(cartPath);
        TestUtil.initFileWithContext(cartPath, new ArrayList<>());
        System.setOut(originalOut);
    }

    @Test
    void should_return_empty_cart_when_pos_system_load_file() {
        Cart cart = posSystem.loadCart(cartPath);

        assertThat(cart.getItemsSize(), is(0d));
    }

    @Test
    void should_return_cart_have_one_item_when_file_have_one_line() throws IOException {
        String oneItem = "ITEM000001";
        TestUtil.initFileWithContext(cartPath, Collections.singletonList(oneItem));

        Cart cart = posSystem.loadCart(cartPath);

        assertThat(cart.getItemsSize(), is(1d));
    }

    @Test
    void should_return_correct_num_of_item_when_file_have_item_with_num() throws IOException {
        String oneItemWithNum = "ITEM000001-2";
        TestUtil.initFileWithContext(cartPath, Collections.singletonList(oneItemWithNum));

        Cart cart = posSystem.loadCart(cartPath);

        assertThat(cart.getItemsSize(), is(2d));
    }

    @Test
    void should_print_shop_name_when_pos_system_running() {
        PosSystem.main(new String[]{"Shop"});
        assertThat(outContent.toString(), containsString("Shop name: Shop"));
    }

    @Test
    void should_print_time_when_pos_system_checkout() {
        PosSystem posSystemTestTime = spy(PosSystem.class);
        when(posSystemTestTime.getPrintTime()).thenReturn("2020-01-29 14:00:00");

        posSystemTestTime.checkout();
        assertThat(outContent.toString(), containsString("Print time: 2020-01-29 14:00:00"));
    }
}
