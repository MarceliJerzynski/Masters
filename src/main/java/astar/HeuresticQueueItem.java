package astar;

public class HeuresticQueueItem<T> {
    private final int index;
    private final T value;
    private final Number heuristic;

    public HeuresticQueueItem(int index, T value, Number heuristic) {
        this.index = index;
        this.value = value;
        this.heuristic = heuristic;
    }

    public int getIndex() {
        return index;
    }

    public T getValue() {
        return value;
    }

    public Number getHeuristic() {
        return heuristic;
    }
}
