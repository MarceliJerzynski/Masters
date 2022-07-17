package initializers;

import common.InitializerException;
import common.IntegerMapper;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GraphInitializer {

    public static Triple<Integer, Integer, Integer[][]> getInputGraphDataFromFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        int startPoint = mapToNumber(reader.readLine());
        int endPoint = mapToNumber(reader.readLine());

        Integer[][] matrix = reader.lines().map(GraphInitializer::mapToRow).toArray(Integer[][]::new);
        if (matrix.length < startPoint || matrix.length < endPoint) {
            System.out.println("Podane punkty nie są zawarte w macierzy!");
        }
        reader.close();
        return Triple.of(startPoint, endPoint, matrix);
    }

    public static Triple<Pair<Integer, Integer>, Integer[], Integer[][]> getInputGraphDataWithHeuristicFromFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        int startPoint = mapToNumber(reader.readLine());
        int endPoint = mapToNumber(reader.readLine());

        Integer[] heuristics = mapToRow(reader.readLine());
        Integer[][] matrix = reader.lines().map(GraphInitializer::mapToRow).toArray(Integer[][]::new);
        if (matrix.length < startPoint || matrix.length < endPoint) {
            System.out.println("Podane punkty nie są zawarte w macierzy!");
        }
        reader.close();
        return Triple.of(Pair.of(startPoint, endPoint), heuristics, matrix);
    }

    private static Integer[] mapToRow(String line) {
        String[] splitted = line.split("\\s+");

        Integer[] parsed = IntegerMapper.mapToNullableInteger(splitted);
        return parsed;
    }

    private static Integer mapToNumber(String line) {
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            throw new InitializerException("Nie można zainicjować pojemności plecaka");
        }
    }

}
