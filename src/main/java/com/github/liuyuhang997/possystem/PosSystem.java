package com.github.liuyuhang997.possystem;

import com.github.liuyuhang997.possystem.promotions.Promotion;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@NoArgsConstructor
@AllArgsConstructor
public class PosSystem {

    private String shopName;

    public Cart loadCart(String path) {
        Cart cart = new Cart();
        loadFromFile(path).forEach(cart::addItem);
        return cart;
    }

    public Promotion loadPromotion(String path, Promotion promotion) {
        loadFromFile(path).forEach(promotion::addItem);
        return promotion;
    }

    private List<String> loadFromFile(String path) {
        try {
            return Files.lines(Paths.get(path))
                    .filter(line -> !line.isEmpty())
                    .collect(toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        PosSystem posSystem = new PosSystem(args[0]);
        posSystem.checkout();
    }

    public void checkout() {
        System.out.println(format("Shop name: %s", shopName));
        System.out.println(format("Print time: %s", getPrintTime()));
    }

    protected String getPrintTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}
