package fibonacci;

import common.Algorithm;
import de.vandermeer.asciitable.AsciiTable;

public class FibonacciAlgorithm implements Algorithm {

    private static final int FIRST_N = 10;
    private static final int LAST_N = 20;
    private static final int INCREMENTER = 1;
    private static final int NUMBER_OF_RUNS = 10000;

    private final int firstN;
    private final int lastN;
    private final int numberOfRuns;
    private final int incrementer;

    public FibonacciAlgorithm() {
        this.firstN = FIRST_N;
        this.lastN = LAST_N;
        this.numberOfRuns = NUMBER_OF_RUNS;
        this.incrementer = INCREMENTER;
    }

    public FibonacciAlgorithm(int n) {
        this.firstN = n;
        this.lastN = n;
        this.numberOfRuns = NUMBER_OF_RUNS;
        this.incrementer = INCREMENTER;
    }

    public FibonacciAlgorithm(int firstN, int lastN) {
         this.firstN = firstN;
         this.lastN = lastN;
        this.numberOfRuns = NUMBER_OF_RUNS;
        this.incrementer = INCREMENTER;
    }

    public FibonacciAlgorithm(int firstN, int lastN, int numberOfRuns) {
        this.firstN = firstN;
        this.lastN = lastN;
        this.numberOfRuns = numberOfRuns;
        this.incrementer = INCREMENTER;
    }

    public FibonacciAlgorithm(int firstN, int lastN, int numberOfRuns, int incrementer) {
        this.firstN = firstN;
        this.lastN = lastN;
        this.numberOfRuns = numberOfRuns;
        this.incrementer = incrementer;
    }

    @Override
    public void run() {
        displayProblem();
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("N", "Dziel i zwyciężaj", "Programowanie dynamiczne", "Proporcja");
        for(int i = firstN; i <= lastN; i+=incrementer) {
            runAndCompareXTimes(i, table);
        }
        table.addRule();
        System.out.println("---Rozwiązanie---");
        System.out.println(table.render());
    }

    private int getFibonacciNumberRecursive(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        return getFibonacciNumberRecursive(n - 1) + getFibonacciNumberRecursive(n - 2);
    }

    private int getFibonacciNumberDynamically(int n) {
        int[] fibonacciNumbers = new int[n+1];
        if (n == 0) {
            return 0;
        }
        fibonacciNumbers[0] = 0;
        fibonacciNumbers[1] = 1;
        for (int i = 2; i <= n; i++) {
            fibonacciNumbers[i] = fibonacciNumbers[i - 1] + fibonacciNumbers[i - 2];
        }
        return fibonacciNumbers[n];
    }

    private void runAndCompareXTimes(int n, AsciiTable table) {
        long sumOfRecursiveTimes = 0;
        long sumOfDynamicTimes = 0;
        for (int i = 0; i < numberOfRuns; i++) {
            long time11 = System.nanoTime();
            int result1 = getFibonacciNumberRecursive(n);
            long time12 = System.nanoTime();

            long time21 = System.nanoTime();
            int result2 = getFibonacciNumberDynamically(n);
            long time22 = System.nanoTime();
            if (result1 != result2) {
                System.out.println("Cos nie tak!");
                System.out.println("Dziel i zwyciężaj: " + result1);
                System.out.println("Programowanie dynamiczne: " + result2);
            }
            sumOfRecursiveTimes += time12 - time11;
            sumOfDynamicTimes += time22 - time21;
        }
        double recursiveTime = (double) sumOfRecursiveTimes/numberOfRuns;
        double dynamicTime = (double) sumOfDynamicTimes/numberOfRuns;

        String[] row = new String[4];

        row[0] = String.valueOf(n);
        row[1] = String.valueOf(recursiveTime);
        row[2] = String.valueOf(dynamicTime);
        row[3] = String.valueOf(recursiveTime/dynamicTime);
        table.addRule();
        table.addRow(row);
    }

    private void displayProblem() {
        System.out.println("---Ciąg Fibbonaciego---");
        System.out.format("Początkowa wartość N: %s%n", firstN);
        System.out.format("Końcowa wartość N: %s%n", lastN);
        System.out.format("Krok: %s%n", INCREMENTER);
        System.out.printf("Ilość uruchomień algorytmu dla danej wartości N: %s%n", numberOfRuns);
    }
}
