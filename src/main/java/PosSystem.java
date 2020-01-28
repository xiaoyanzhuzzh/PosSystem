import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class PosSystem {

    public Cart loadCart(String path) {
        Cart cart = new Cart();
        loadFromFile(path).forEach(cart::addItem);
        return cart;
    }

    public Promotion loadPromotion(String path) {
        Promotion promotion = new Promotion();
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
}
