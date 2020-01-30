package com.github.liuyuhang997.possystem.entities;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ItemTest {

    private final String ITEM = "ITEM000001";

    @Test
    void should_return_single_item_when_line_without_num() {
        Item item = Item.parseItem(ITEM);

        assertThat(item.getName(), is(ITEM));
        assertThat(item.getNum(), is(1d));
    }

    @Test
    void should_return_item_with_num_when_line_with_num() {
        Item item = Item.parseItem("ITEM000001-1.3");

        assertThat(item.getName(), is(ITEM));
        assertThat(item.getNum(), is(1.3d));
    }

    @Test
    void should_return_default_price_and_unit_when_parse_item() {
        Item item = Item.parseItem(ITEM);

        assertThat(item.getPrice(), is(1d));
        assertThat(item.getUnit(), is("KG"));
    }
}
