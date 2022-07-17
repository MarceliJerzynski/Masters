import astar.AstarAlgorithm;
import bellmanford.BellmanFordAlgorithm;
import common.Algorithm;
import common.IntegerMapper;
import common.Mode;
import dijkstra.DijkstraAlgorithm;
import fibonacci.FibonacciAlgorithm;
import initializers.GraphInitializer;
import knapsack.KnapsackAlgorithm;
import initializers.KnapsackInitializer;
import matrixes.MatrixMultiplicationAlgorithm;
import org.apache.commons.lang3.tuple.Triple;

import java.io.IOException;
import java.util.Arrays;

public class MastersApplication {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Nie podano argumentów. " +
                    "Program uruchomi wszystkie algorytmy na zdefiniowanych wcześniej danych");
            runForNoArguments();
            return;
        }
        final Mode mode;
        try {
            mode = Mode.valueOf(args[0]);
        } catch (IllegalArgumentException e) {
            System.out.println("Podany argument nie zawiera się w liście możliwych trybów");
            return;
        }
        System.out.printf("Wybrany tryb: %s\n", mode);

        switch (mode) {
            case FIBONACCI:
                runFibonacci(args);
                break;
            case KNAPSACK:
                runKnapsack(args);
                break;
            case MATRIXES:
                runMatrixMultiplication(args);
                break;
            case DIJKSTRA:
                runDijkstra(args);
            default:
                System.out.println("Podany argument nie zawiera się w liście możliwych trybów");
        }
    }

    private static void runFibonacci(String[] args) {
        String[] fibonacciArguments = Arrays.copyOfRange(args, 1, args.length);
        Integer[] parsedArguments;
        try {
            parsedArguments = IntegerMapper.mapToInteger(fibonacciArguments);
        } catch (NumberFormatException e) {
            System.out.println("Podane argumenty nie są liczbami całkowitoliczbowymi");
            return;
        }
        switch (parsedArguments.length) {
            case 0:
                new FibonacciAlgorithm().run();
                break;
            case 1:
                new FibonacciAlgorithm(parsedArguments[0]).run();
                break;
            case 2:
                new FibonacciAlgorithm(parsedArguments[0], parsedArguments[1]).run();
                break;
            case 3:
                new FibonacciAlgorithm(parsedArguments[0], parsedArguments[1], parsedArguments[2]).run();
                break;
            case 4:
                new FibonacciAlgorithm(parsedArguments[0], parsedArguments[1], parsedArguments[2], parsedArguments[3]).run();
                break;
            default:
                System.out.println("Maksymalna liczba argumentów liczbowych wynosi 4");
        }

    }

    private static void runKnapsack(String[] args) throws IOException {
        if (args.length == 1) {
            new KnapsackAlgorithm().run();
        } else if (args.length == 2) {
            KnapsackInitializer.getAlgorithmFromFile(args[1]).run();
        } else {
            System.out.println("Maksymalna liczba argumentów liczbowych wynosi 2");
        }


    }

    private static void runMatrixMultiplication(String[] args) {
        if (args.length == 1) {
            new MatrixMultiplicationAlgorithm().run();
            return;
        }
        if (args.length == 2) {
            System.out.println("Minimalna liczba argumentów liczbowych wynosi 2");
            return;
        }
        String[] matricesArguments = Arrays.copyOfRange(args, 1, args.length);
        Integer[] parsedArguments;
        try {
            parsedArguments = IntegerMapper.mapToInteger(matricesArguments);
        } catch (NumberFormatException e) {
            System.out.println("Podane argumenty nie są liczbami całkowitoliczbowymi");
            return;
        }
        new MatrixMultiplicationAlgorithm(parsedArguments).run();

    }

    private static void runDijkstra(String[] args) throws IOException {
        if (args.length == 1) {
            new DijkstraAlgorithm().run();
        } else if (args.length == 2) {
            Triple<Integer, Integer, Integer[][]> graph = GraphInitializer.getInputGraphDataFromFile(args[1]);
            new DijkstraAlgorithm(graph.getLeft(), graph.getMiddle(), graph.getRight()).run();
        } else {
            System.out.println("Maksymalna liczba argumentów liczbowych wynosi 2");
        }
    }

//    private static void runGraphAlgorithm(String[] args, Runnable )

    private static void runForNoArguments() {
        final Algorithm[] algorithms = new Algorithm[]{
                new FibonacciAlgorithm(),
                new KnapsackAlgorithm(),
                new MatrixMultiplicationAlgorithm(),
                new DijkstraAlgorithm(),
                new AstarAlgorithm(),
                new BellmanFordAlgorithm()
        };
        for (Algorithm algorithm : algorithms) {
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
            algorithm.run();
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");

        }
    }
}
