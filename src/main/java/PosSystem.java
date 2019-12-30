import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PosSystem {

    public Cart loadCart(String path) {
        Cart cart = new Cart();
        try {
            Files.lines(Paths.get(path))
                    .forEach(lineFromFile -> cart.addItem(convertToItem(lineFromFile)));
        } catch (IOException e) {
            System.out.println(e);
        }
        return cart;
    }

    public Promotion loadPromotion(String path) {
        Promotion promotion = new Promotion();
        try {
            Files.lines(Paths.get(path))
                    .forEach(lineFromFile -> promotion.addPromotionItem(lineFromFile, 0.0));
        } catch (IOException e) {
            System.out.println(e);
        }
        return promotion;
    }

    private Item convertToItem(String lineFromFile) {
        String[] itemAndNum = lineFromFile.split("-");
        if (1 == itemAndNum.length) {
            return Item.builder().name(itemAndNum[0]).num(1).build();
        } else {
            Integer num = Integer.parseInt(itemAndNum[1]);
            return Item.builder().name(itemAndNum[0]).num(num).build();
        }
    }
}
