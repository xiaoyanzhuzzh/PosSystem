package com.github.liuyuhang997.possystem.prints;

public class PrintFormat {
    final static String ITEM = "%-20s %-6s %-6s %-5s";
    final static String ITEM_TITLE = ITEM.replace(" ", "|");
    final static String DIRECTION_RIGHT = "%40s";
    final static String SHOP_NAME = "Shop name: %s";
    final static String PRINT_TIME = "Print time: %s";
    final static String SHOPPING_DETAILS = "Shopping details:";
    final static Object[] ITEM_TITLE_NAME = {"name", "num", "price", "unit"};
    final static String BEFORE_DISCOUNT_PRICE = "Before discount price: ";
    final static String AFTER_DISCOUNT_PRICE = "After discount price: ";
    final static String DISCOUNT_SPREAD = "Discount spread: ";
    final static String TOTAL_PRICE = "Total price: ";
    final static String SUBTOTAL = "Subtotal: ";
    final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
}
