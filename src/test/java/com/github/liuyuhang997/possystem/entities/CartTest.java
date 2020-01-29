package com.github.liuyuhang997.possystem.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


class CartTest {
    Cart cart;

    @BeforeEach
    void setUp() {
        cart = new Cart();
    }

    @Test
    void should_add_new_item_when_cart_not_exist_the_item() {
        cart.addItem("ITEM000001");

        assertThat(cart.getItems().get("ITEM000001").getNum(), is(1d));
    }

    @Test
    void should_add_item_num_when_cart_exist_the_item() {
        String item1 = "ITEM000001";
        cart.addItem(item1);
        assertThat(cart.getItems().get(item1).getNum(), is(1d));

        cart.addItem(item1);
        assertThat(cart.getItems().get(item1).getNum(), is(2d));
    }
}