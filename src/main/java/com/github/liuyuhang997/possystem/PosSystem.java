package com.github.liuyuhang997.possystem;

import com.github.liuyuhang997.possystem.entities.Cart;
import com.github.liuyuhang997.possystem.enums.FileNameEnum;
import com.github.liuyuhang997.possystem.enums.PromotionEnum;
import com.github.liuyuhang997.possystem.factories.PromotionFactory;
import com.github.liuyuhang997.possystem.promotions.Promotion;
import com.github.liuyuhang997.possystem.utils.FileUtil;
import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.github.liuyuhang997.possystem.enums.FileNameEnum.BUY_TWO_GET_ONE_FREE;
import static com.github.liuyuhang997.possystem.enums.FileNameEnum.CART;
import static com.github.liuyuhang997.possystem.enums.FileNameEnum.DISCOUNT;
import static com.github.liuyuhang997.possystem.enums.FileNameEnum.SECOND_HALF_PRICE;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

@Getter
public class PosSystem {

    private List<Promotion> promotions;
    private String shopName;
    private Cart cart;

    public PosSystem(String shopName) {
        this.shopName = shopName;
        this.promotions = new ArrayList<>();
        cart = loadCart(FileUtil.getResourcePath(CART.getName()));
        promotions.addAll(asList(
                loadPromotion(BUY_TWO_GET_ONE_FREE),
                loadPromotion(DISCOUNT),
                loadPromotion(SECOND_HALF_PRICE)));
    }

    public static void main(String[] args) {
        PosSystem posSystem = new PosSystem(args[0]);
        posSystem.checkout();
    }

    public Cart loadCart(String path) {
        Cart cart = new Cart();
        loadFromFile(path).forEach(cart::addItem);
        return cart;
    }

    public Promotion loadPromotion(FileNameEnum fileName) {
        String path = FileUtil.getResourcePath(fileName.getName());
        Promotion promotion = PromotionFactory.getPromotion(PromotionEnum.valueOf(fileName.toString()));
        loadFromFile(path).forEach(promotion::addItem);
        return promotion;
    }

    public void checkout() {
        //TODO: move print to obj
        printHead();
        printShoppingDetails(cart);
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

    private void printShoppingDetails(Cart cart) {
        String itemFormat = "%-20s %-6s %-6s %-5s";
        String itemTitleFormat = itemFormat.replace(" ", "|");

        printLine();
        System.out.println("Shopping Details:");
        System.out.println(format(itemTitleFormat, "itemName", "num", "price", "unit"));
        cart.getItems()
                .forEach((key, item) -> {
                    System.out.println(format(itemFormat, item.getName(), roundNum(item.getNum()), roundNum(item.getPrice()), item.getUnit()));
                    System.out.println(format("%40s", "subTotal: " + roundNum(item.getNum() * item.getPrice())));
                });
    }

    private void printHead() {
        printLine();
        System.out.println(format("Shop name: %s", shopName));
        System.out.println(format("Print time: %s", getPrintTime()));
    }

    private String roundNum(Double number) {
        return format("%.2f", number);
    }

    private void printLine() {
        System.out.println(format("%40s", "").replace(" ", "-"));
    }

    protected String getPrintTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}
