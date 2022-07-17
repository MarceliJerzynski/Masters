package knapsack;

import common.Algorithm;
import de.vandermeer.asciitable.AsciiTable;
import org.apache.commons.lang3.tuple.Pair;


public class KnapsackAlgorithm implements Algorithm {

    private final static Item[] ITEMS = {
            new Item(2, 3, 1),
            new Item(3, 1, 2),
            new Item(3, 3, 3),
            new Item(5, 4, 4),
            new Item(4, 2, 5),
    };
    private final static int BACKPACK_CAPACITY = 7;

    private final Item[] items;
    private final int backpackCapacity;

    public KnapsackAlgorithm() {
        this.items = ITEMS;
        this.backpackCapacity = BACKPACK_CAPACITY;
    }

    public KnapsackAlgorithm(Item[] items, int backpackCapacity) {
        this.items = items;
        this.backpackCapacity = backpackCapacity;
    }

    @Override
    public void run() {
        displayProblem(backpackCapacity, items);
        Pair<int[][], SetOfItems[][]> result = run(backpackCapacity, items);
        displaySolution(result.getLeft(), result.getRight(), items);
    }

    private Pair<int[][], SetOfItems[][]> run(int backpackCapacity, Item... items) {
        int n = items.length;
        int[][] T = new int[n + 1][backpackCapacity + 1];
        SetOfItems[][] solutionWithItems = new SetOfItems[n + 1][backpackCapacity + 1];
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= backpackCapacity; j++) {
                solutionWithItems[i][j] = new SetOfItems();
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= backpackCapacity; j++) {
                if (items[i - 1].getWeight() > j) {
                    T[i][j] = T[i - 1][j];
                    solutionWithItems[i][j].addAll(solutionWithItems[i - 1][j]);
                } else {
                    if (T[i - 1][j] > T[i - 1][j - items[i - 1].getWeight()] + items[i - 1].getValue()) {
                        T[i][j] = T[i - 1][j];
                        solutionWithItems[i][j].addAll(solutionWithItems[i - 1][j]);
                    } else {
                        T[i][j] = T[i - 1][j - items[i - 1].getWeight()] + items[i - 1].getValue();
                        solutionWithItems[i][j].addAll(solutionWithItems[i - 1][j - items[i - 1].getWeight()]);
                        solutionWithItems[i][j].add(items[i - 1]);
                    }
                }

            }
        }
        return Pair.of(T, solutionWithItems);
    }

    private void displayProblem(int backpackCapacity, Item... items) {
        System.out.println("---Rozwiązywany problem plecakowy---");
        System.out.printf("Pojemność plecaka: %s%n", backpackCapacity);

        AsciiTable table = new AsciiTable();

        table.addRule();
        table.addRow("Indeks", "Waga", "Wartość");
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            table.addRule();
            table.addRow(i + 1, item.getWeight(), item.getValue());
        }
        table.addRule();
        System.out.println(table.render());
    }

    private void displaySolution(int[][] result, SetOfItems[][] solutionWithItems, Item... items) {
        System.out.println("---Rozwiązanie---");
        int n = result.length;
        int k = result[n - 1].length;
        AsciiTable table = new AsciiTable();

        String[] headers = new String[k + 1];
        headers[0] = "Indeks przemiotu";
        for (int i = 1; i <= k; i++) {
            headers[i] = "Pojemność plecaka = " + (i - 1);
        }
        table.addRule();
        table.addRow(headers);
        for (int i = 0; i < n; i++) {
            String[] row = new String[k + 1];
            row[0] = String.valueOf(i);
            for (int j = 1; j <= k; j++) {
                row[j] = String.valueOf(result[i][j - 1]);
            }
            table.addRule();
            table.addRow(row);
        }
        table.addRule();
        System.out.println(table.render());
        System.out.printf("Maksymalna wartość plecaka: %s%n", result[n - 1][k - 1]);

        AsciiTable itemsTable = new AsciiTable();

        System.out.println("Wybrane przemioty: ");
        itemsTable.addRule();
        itemsTable.addRow("Indeks", "Waga", "Wartość");
        int usedCapacity = 0;
        for(Item item : solutionWithItems[n-1][k-1].getItems()) {
            itemsTable.addRule();
            itemsTable.addRow(item.getIndex(), item.getWeight(), item.getValue());
            usedCapacity+=item.getWeight();
        }
        itemsTable.addRule();
        System.out.println(itemsTable.render());
        System.out.format("Wykorzystano %s miejsc w plecaku%n", usedCapacity);
    }
}
