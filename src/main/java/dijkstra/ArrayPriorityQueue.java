package dijkstra;

import common.ResultNotFoundException;
import org.apache.commons.lang3.mutable.MutableInt;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArrayPriorityQueue {

    private List<QueueItem<MutableInt>> items;

    public ArrayPriorityQueue(MutableInt[] items) {
        initializeItemsWithoutOrder(items);
    }

    public Integer pollIndex() {
        QueueItem<MutableInt> item = null;
        int minValue = Integer.MAX_VALUE;
        for (QueueItem<MutableInt> potentialItem : items) {
            if (potentialItem.getValue().getValue() <= minValue) {
                minValue = potentialItem.getValue().getValue();
                item = potentialItem;
            }
        }
        if (item == null) {
            throw new ResultNotFoundException("Index not found, something went wrong");
        }
        items.remove(item);
        return item.getIndex();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    private void initializeItemsWithoutOrder(MutableInt[] items) {
        this.items = new ArrayList<>(items.length);
        for(int i = 0; i < items.length; i++) {
            QueueItem<MutableInt> item = new QueueItem<>(i, items[i]);
            this.items.add(item);
        }
    }
}
