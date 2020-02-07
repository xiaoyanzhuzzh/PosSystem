package com.github.liuyuhang997.possystem.utils;

import com.github.liuyuhang997.possystem.entities.Cart;
import com.github.liuyuhang997.possystem.enums.FileNameEnum;
import com.github.liuyuhang997.possystem.enums.PromotionEnum;
import com.github.liuyuhang997.possystem.factories.PromotionFactory;
import com.github.liuyuhang997.possystem.promotions.Promotion;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class FileUtil {
    public static String getResourcePath(String name) {
        return Objects.requireNonNull(FileUtil.class.getClassLoader().getResource(name)).getPath();
    }

    public static Promotion loadPromotion(FileNameEnum fileName) {
        String path = FileUtil.getResourcePath(fileName.getName());
        Promotion promotion = PromotionFactory.getPromotion(PromotionEnum.valueOf(fileName.toString()));
        loadFromFile(path).forEach(promotion::addItem);
        return promotion;
    }

    public static Cart loadCart(String path) {
        Cart cart = new Cart();
        loadFromFile(path).forEach(cart::addItem);
        return cart;
    }

    public static List<String> loadFromFile(String path) {
        try {
            return Files.lines(Paths.get(path))
                    .filter(line -> !line.isEmpty())
                    .collect(toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Lists.newArrayList();
    }

    public static List<String> splitLine(String separator, String Line) {
        return Splitter.on(separator)
                .trimResults()
                .omitEmptyStrings()
                .splitToList(Line);
    }
}
