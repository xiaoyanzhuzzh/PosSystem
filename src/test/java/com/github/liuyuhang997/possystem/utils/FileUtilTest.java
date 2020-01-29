package com.github.liuyuhang997.possystem.utils;

import org.junit.jupiter.api.Test;

import static com.github.liuyuhang997.possystem.enums.FileNameEnums.CART;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class FileUtilTest {
    @Test
    void should_get_cart_path() {
        String path = FileUtil.getResourcePath(CART.getName());

        assertThat(path, containsString("/" + CART.getName()));
    }
}
