package com.github.liuyuhang997.possystem.utils;

import java.util.Objects;

public class FileUtil {
    public static String getResourcePath(String name) {
        return Objects.requireNonNull(FileUtil.class.getClassLoader().getResource(name)).getPath();
    }
}
