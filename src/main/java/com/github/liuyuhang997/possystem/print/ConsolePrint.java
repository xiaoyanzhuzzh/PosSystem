package com.github.liuyuhang997.possystem.print;

import com.github.liuyuhang997.possystem.entities.Cart;
import com.github.liuyuhang997.possystem.entities.Item;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.String.format;

public class ConsolePrint {
    private String shopName;
    private Cart cart;

    public ConsolePrint(String shopName, Cart cart) {
        this.shopName = shopName;
        this.cart = cart;
    }

    public void printReceipt() {
        printHead();
        printShoppingDetails(cart);
        printTotal();
    }

    private void printHead() {
        printLine();
        System.out.println(format("Shop name: %s", shopName));
        System.out.println(format("Print time: %s", getPrintTime()));
    }

    private void printShoppingDetails(Cart cart) {
        String itemFormat = "%-20s %-6s %-6s %-5s";
        String itemTitleFormat = itemFormat.replace(" ", "|");

        printLine();
        System.out.println("Shopping details:");
        System.out.println(format(itemTitleFormat, "itemName", "num", "price", "unit"));
        cart.getItems()
                .forEach((itemName, item) -> {
                    System.out.println(format(itemFormat, item.getName(), roundNum(item.getNum()), roundNum(item.getPrice()), item.getUnit()));
                    System.out.println(format("%40s", "subtotal: " + roundNum(item.getOriginalPrice())));
                });
    }

    private void printTotal() {
        double beforeDiscount = cart.getItems().values().stream().mapToDouble(Item::getOriginalPrice).sum();
        double afterDiscount = cart.getItems().values().stream().mapToDouble(Item::getSubtotal).sum();
        printLine();
        System.out.println(format("%40s", "Before discount price: " + roundNum(beforeDiscount)));
        System.out.println(format("%40s", "After discount price: " + roundNum(afterDiscount)));
        System.out.println(format("%40s", "Discount spread: " + roundNum(beforeDiscount - afterDiscount)));
        printLine();
        System.out.println(format("%40s", "Total price: " + roundNum(afterDiscount)));
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
