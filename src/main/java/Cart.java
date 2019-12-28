import java.util.HashMap;
import java.util.Map;

public class Cart {

    public Cart() {
        this.items = new HashMap<>();
    }

    private Map<String, Item> items;

    public void addItem(Item item) {
        if (items.containsKey(item.getName())) {
            Item targetItem = items.get(item.getName());
            targetItem.setNum(targetItem.getNum() + item.getNum());
        } else {
            items.put(item.getName(), item);
        }
    }

    public int getItemsSize() {
        return items.values().stream().mapToInt(Item::getNum).sum();
    }
}
