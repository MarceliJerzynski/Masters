package knapsack;

public class Item {
    private final int weight;
    private final int value;
    private final int index;

    public Item(int weight, int value, int index) {
        this.weight = weight;
        this.value = value;
        this.index = index;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }
}
