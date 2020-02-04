package com.github.liuyuhang997.possystem.prints;

import com.github.liuyuhang997.possystem.entities.Cart;
import com.github.liuyuhang997.possystem.entities.Item;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.github.liuyuhang997.possystem.prints.PrintFormat.AFTER_DISCOUNT_PRICE;
import static com.github.liuyuhang997.possystem.prints.PrintFormat.BEFORE_DISCOUNT_PRICE;
import static com.github.liuyuhang997.possystem.prints.PrintFormat.DIRECTION_RIGHT;
import static com.github.liuyuhang997.possystem.prints.PrintFormat.DISCOUNT_SPREAD;
import static com.github.liuyuhang997.possystem.prints.PrintFormat.ITEM;
import static com.github.liuyuhang997.possystem.prints.PrintFormat.ITEM_TITLE;
import static com.github.liuyuhang997.possystem.prints.PrintFormat.ITEM_TITLE_NAME;
import static com.github.liuyuhang997.possystem.prints.PrintFormat.PRINT_TIME;
import static com.github.liuyuhang997.possystem.prints.PrintFormat.SHOPPING_DETAILS;
import static com.github.liuyuhang997.possystem.prints.PrintFormat.SHOP_NAME;
import static com.github.liuyuhang997.possystem.prints.PrintFormat.SUBTOTAL;
import static com.github.liuyuhang997.possystem.prints.PrintFormat.TOTAL_PRICE;
import static com.github.liuyuhang997.possystem.prints.PrintFormat.YYYY_MM_DD_HH_MM_SS;
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
        printAndFormat(SHOP_NAME, shopName);
        printAndFormat(PRINT_TIME, getPrintTime());
    }

    private void printShoppingDetails(Cart cart) {
        printLine();
        System.out.println(SHOPPING_DETAILS);
        printAndFormat(ITEM_TITLE, ITEM_TITLE_NAME);
        cart.getItems()
                .forEach((itemName, item) -> {
                    printAndFormat(ITEM, item.getName(), roundNum(item.getNum()), roundNum(item.getPrice()), item.getUnit());
                    printAndFormat(DIRECTION_RIGHT, SUBTOTAL + roundNum(item.getOriginalPrice()));
                });
    }

    private void printTotal() {
        double beforeDiscount = cart.getItems().values().stream().mapToDouble(Item::getOriginalPrice).sum();
        double afterDiscount = cart.getItems().values().stream().mapToDouble(Item::getSubtotal).sum();
        printLine();
        printAndFormat(DIRECTION_RIGHT, BEFORE_DISCOUNT_PRICE + roundNum(beforeDiscount));
        printAndFormat(DIRECTION_RIGHT, AFTER_DISCOUNT_PRICE + roundNum(afterDiscount));
        printAndFormat(DIRECTION_RIGHT, DISCOUNT_SPREAD + roundNum(beforeDiscount - afterDiscount));
        printLine();
        printAndFormat(DIRECTION_RIGHT, TOTAL_PRICE + roundNum(afterDiscount));
    }

    private void printAndFormat(String format, Object... args) {
        System.out.println(format(format, args));
    }

    private String roundNum(Double number) {
        return format("%.2f", number);
    }

    private void printLine() {
        System.out.println(format(DIRECTION_RIGHT, "").replace(" ", "-"));
    }

    protected String getPrintTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);
        return LocalDateTime.now().format(formatter);
    }
}
