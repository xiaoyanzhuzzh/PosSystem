package com.github.liuyuhang997.possystem.utils;

import com.github.liuyuhang997.possystem.entities.Cart;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.github.liuyuhang997.possystem.enums.FileNameEnum.CART;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class FileUtilTest {
    private final String CART_PATH = TestUtil.getResourcePath("cart.txt");
    private final String ITEM = "ITEM00001";

    @AfterEach
    void tearDown() throws IOException {
        TestUtil.deleteFile(CART_PATH);
        TestUtil.initFileWithContext(CART_PATH, new ArrayList<>());
    }

    @Test
    void should_get_cart_path() {
        String path = FileUtil.getResourcePath(CART.getName());

        assertThat(path, containsString("/" + CART.getName()));
    }

    @Test
    void should_return_empty_list_when_path_not_exist() {
        List<String> lines = FileUtil.loadFromFile("error_path");

        assertThat(lines, hasSize(0));
    }

    @Test
    void should_filter_empty_line_when_load_from_file() throws IOException {
        TestUtil.initFileWithContext(CART_PATH, asList(ITEM, ""));

        List<String> lines = FileUtil.loadFromFile(CART_PATH);

        assertThat(lines, hasSize(1));
    }

    @Test
    void should_return_empty_cart_when_file_util_load_file() {
        Cart cart = FileUtil.loadCart(CART_PATH);

        assertThat(cart.getItems().size(), is(0));
    }

    @Test
    void should_return_cart_have_one_item_when_file_have_one_line() throws IOException {
        TestUtil.initFileWithContext(CART_PATH, singletonList(ITEM));

        Cart cart = FileUtil.loadCart(CART_PATH);

        assertThat(cart.getItems().get(ITEM).getNum(), is(1d));
    }

    @Test
    void should_return_correct_num_of_item_when_file_have_item_with_num() throws IOException {
        String oneItemWithNum = ITEM + "-2";
        TestUtil.initFileWithContext(CART_PATH, singletonList(oneItemWithNum));

        Cart cart = FileUtil.loadCart(CART_PATH);

        assertThat(cart.getItems().get(ITEM).getNum(), is(2d));
    }
}
