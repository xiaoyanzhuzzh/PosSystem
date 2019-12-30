import java.util.HashMap;
import java.util.Map;

public class Promotion {

    private Map<String, Double> promotionItems;

    public Promotion() {
        promotionItems = new HashMap<>();
    }

    public void addItem(String lineFromFile) {
        this.promotionItems.put(lineFromFile, null);
    }

    public int getPromotionItemsSize() {
        return promotionItems.keySet().size();
    }
}
