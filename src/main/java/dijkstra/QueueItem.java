package dijkstra;

public class QueueItem<T> {
    private final int index;
    private final T value;

    public QueueItem(int index, T value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public T getValue() {
        return value;
    }
}
