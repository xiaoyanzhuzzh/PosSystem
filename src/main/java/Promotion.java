import java.util.HashMap;
import java.util.Map;

public class Promotion {

    private Map<String, Double> promotionItems;

    public Promotion() {
        promotionItems = new HashMap<>();
    }

    public void addPromotionItem(String name, Double discount) {
        this.promotionItems.put(name, discount);
    }

    public int getPromotionItemsSize() {
        return promotionItems.keySet().size();
    }
}
