package astar;

import common.ResultNotFoundException;
import org.apache.commons.lang3.mutable.Mutable;
import org.apache.commons.lang3.mutable.MutableInt;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArrayHeuristicPriorityQueue {
    private List<HeuresticQueueItem<MutableInt>> items;

    public ArrayHeuristicPriorityQueue(MutableInt[] items, Number[] heuristics) {
        initializeItemsWithoutOrder(items, heuristics);
    }

    public Integer pollIndex() {
        int minValue = Integer.MAX_VALUE;
        HeuresticQueueItem<MutableInt> item = null;
        for (HeuresticQueueItem<MutableInt> potentialItem : items) {
            if (potentialItem.getValue().getValue() + potentialItem.getHeuristic().intValue() < minValue) {
                minValue = potentialItem.getValue().getValue() + potentialItem.getHeuristic().intValue();
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

    private void initializeItemsWithoutOrder(MutableInt[] items, Number[] heuristics) {
        this.items = new ArrayList<>(items.length);
        for(int i = 0; i < items.length; i++) {
            HeuresticQueueItem<MutableInt> item = new HeuresticQueueItem<>(i, items[i], heuristics[i]);
            this.items.add(item);
        }
    }
}
