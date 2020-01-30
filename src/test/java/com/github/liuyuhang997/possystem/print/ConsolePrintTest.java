package com.github.liuyuhang997.possystem.print;

import com.github.liuyuhang997.possystem.PosSystem;
import com.github.liuyuhang997.possystem.entities.Cart;
import com.github.liuyuhang997.possystem.entities.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class ConsolePrintTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String ITEM_1 = "ITEM00001";
    private final String ITEM_3 = "ITEM00003";
    private final String ITEM_5 = "ITEM00005";

    private ConsolePrint consolePrint;

    @BeforeEach
    void setUp() {
        Cart cart = new Cart();
        cart.getItems().put(ITEM_1, buildItem(5d, ITEM_1, 3.75d));
        cart.getItems().put(ITEM_3, buildItem(2d, ITEM_3, 1.5d));
        cart.getItems().put(ITEM_5, buildItem(3d, ITEM_5, 2d));
        consolePrint = new ConsolePrint("ShopTest", cart);
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void should_print_shop_name_when_pos_system_running() {
        PosSystem.main(new String[]{"ShopTest"});
        assertThat(outContent.toString(), containsString("Shop name: ShopTest"));
    }

    @Test
    void should_print_default_shop_name_when_pos_system_running_without_args() {
        PosSystem.main(new String[]{});
        assertThat(outContent.toString(), containsString("Shop name: Shop"));
    }

    @Test
    void should_print_time_when_pos_system_checkout() {
        consolePrint.printReceipt();
        assertThat(outContent.toString(), containsString("Print time:"));
    }

    @Test
    void should_print_item_num_and_price_and_unit_and_subtotal_when_pos_system_checkout() {
        consolePrint.printReceipt();

        assertThat(outContent.toString(), containsString("Shopping details:"));
        assertThat(outContent.toString(), containsString(ITEM_1));
        assertThat(outContent.toString(), containsString(ITEM_3));
        assertThat(outContent.toString(), containsString(ITEM_5));
        assertThat(outContent.toString(), containsString("Subtotal:"));
    }

    @Test
    void should_print_total_price_when_pos_system_running() {
        consolePrint.printReceipt();

        assertThat(outContent.toString(), containsString("Before discount price: 10.00"));
        assertThat(outContent.toString(), containsString("After discount price: 7.25"));
        assertThat(outContent.toString(), containsString("Discount spread: 2.75"));
        assertThat(outContent.toString(), containsString("Total price: 7.25"));
    }

    private Item buildItem(double num, String name, double subtotal) {
        return Item.builder().num(num).price(1d).name(name).unit("KG").subtotal(subtotal).build();
    }
}
