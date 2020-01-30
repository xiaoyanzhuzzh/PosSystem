package com.github.liuyuhang997.possystem;

import com.github.liuyuhang997.possystem.entities.Cart;
import com.github.liuyuhang997.possystem.enums.FileNameEnum;
import com.github.liuyuhang997.possystem.enums.PromotionEnum;
import com.github.liuyuhang997.possystem.factories.PromotionFactory;
import com.github.liuyuhang997.possystem.print.ConsolePrint;
import com.github.liuyuhang997.possystem.promotions.Promotion;
import com.github.liuyuhang997.possystem.utils.FileUtil;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static com.github.liuyuhang997.possystem.enums.FileNameEnum.BUY_TWO_GET_ONE_FREE;
import static com.github.liuyuhang997.possystem.enums.FileNameEnum.CART;
import static com.github.liuyuhang997.possystem.enums.FileNameEnum.DISCOUNT;
import static com.github.liuyuhang997.possystem.enums.FileNameEnum.SECOND_HALF_PRICE;
import static com.github.liuyuhang997.possystem.utils.FileUtil.loadFromFile;
import static java.util.Arrays.asList;

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
        String shopName = args.length > 0 ? args[0] : "Shop";
        PosSystem posSystem = new PosSystem(shopName);
        posSystem.checkout();
    }

    public void checkout() {
        calculatePromotion();
        ConsolePrint consolePrint = new ConsolePrint(shopName, cart);
        consolePrint.printReceipt();
    }

    public Promotion loadPromotion(FileNameEnum fileName) {
        String path = FileUtil.getResourcePath(fileName.getName());
        Promotion promotion = PromotionFactory.getPromotion(PromotionEnum.valueOf(fileName.toString()));
        loadFromFile(path).forEach(promotion::addItem);
        return promotion;
    }

    protected Cart loadCart(String path) {
        Cart cart = new Cart();
        loadFromFile(path).forEach(cart::addItem);
        return cart;
    }

    protected void calculatePromotion() {
        cart.getItems()
                .forEach((itemName, item) -> {
                    item.setSubtotal(item.getOriginalPrice());
                    promotions.forEach(promotion -> {
                        double promotionSubtotal = promotion.calculatePromotion(item);
                        if (promotionSubtotal < item.getSubtotal()) {
                            item.setSubtotal(promotionSubtotal);
                        }
                    });
                });
    }
}
