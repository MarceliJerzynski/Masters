package initializers;

import common.InitializerException;
import common.IntegerMapper;
import knapsack.Item;
import knapsack.KnapsackAlgorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class KnapsackInitializer {

    public static KnapsackAlgorithm getAlgorithmFromFile(String filePath) throws IOException, InitializerException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String firstLine = reader.readLine();
        int backpackCapacity;
        try {
            backpackCapacity = Integer.parseInt(firstLine);
        } catch (NumberFormatException e) {
            throw new InitializerException("Nie można zainicjować pojemności plecaka");
        }

        Item[] items = reader.lines().map(KnapsackInitializer::mapToItem).toArray(Item[]::new);
        reader.close();

        return new KnapsackAlgorithm(items, backpackCapacity);
    }


    private static Item mapToItem(String line) throws InitializerException {
        String[] splitted = line.split("\\s+");
        if (splitted.length != 3) {
            throw new InitializerException("Przedmiot powinien składać się z 3 liczb!");
        }
        Integer[] parsed = IntegerMapper.mapToInteger(splitted);
        return new Item(parsed[0], parsed[1], parsed[2]);
    }

}
