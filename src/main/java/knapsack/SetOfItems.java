package knapsack;

import java.util.*;

public class SetOfItems {
    final Set<Item> items = new HashSet<>();

    public Set<Item> getItems() {
        return items;
    }

    public void add(Item item) {
        this.items.add(item);
    }

    public void addAll(SetOfItems setOfItems) {
        this.items.addAll(setOfItems.getItems());
    }
}
