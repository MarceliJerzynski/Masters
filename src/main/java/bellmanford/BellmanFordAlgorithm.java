package bellmanford;

import common.Algorithm;
import common.ResultNotFoundException;
import de.vandermeer.asciitable.AsciiTable;
import org.apache.commons.lang3.tuple.Pair;


public class BellmanFordAlgorithm implements Algorithm {

    private static final Integer[][] M = {
            {null, 6   , 3   , null, null},
            {null, null, null, 10  , null},
            {null, null, null, null, 4   },
            {null, null, null, null, null},
            {null, -3  , null, null, null}
    };


    private static final int START_POINT = 0;
    private static final int END_POINT = 3;

    private Pair<Integer, Integer> relax(Integer[] d, Integer[] p, Integer[][] M, Integer u, Integer v) {
        if (u == null || v == null || M[u][v] == null ) {
            return Pair.of(d[v], p[v]);
        }
        if (d[v] > d[u] + M[u][v]) {
            return Pair.of(d[u] + M[u][v], u);
        }
        return Pair.of(d[v], p[v]);
    }


    @Override
    public void run() {
        displayProblem(M, START_POINT, END_POINT);
        Pair<Integer[], Integer[]> result = run(M, START_POINT);
        displaySolution(result.getLeft(), result.getRight(), START_POINT, END_POINT);
    }

    private Pair<Integer[], Integer[]> run(Integer[][] M, int s) {
        int n = M.length;
        Integer[] d = new Integer[n];
        Integer[] p = new Integer[n];
        for(int i = 0; i < n; i++) {
            d[i] = Integer.MAX_VALUE;
        }

        d[s] = 0;

        for(int i = 1; i < n; i++) {
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < n; k++) {
                    if (M[j][k] != null) {
                        Pair<Integer, Integer> pair = relax(d, p, M, j, k);
                        d[k] = pair.getLeft();
                        p[k] = pair.getRight();
                    }
                }
            }
        }
        for(int j = 0; j < n; j++) {
            for(int k = 0; k < n; k++) {
                if (M[j][k] != null) {
                    if (d[k] > d[j] + M[j][k]) {
                        throw new ResultNotFoundException("Something went wrong, Bellman-Ford didn't find optimal result");
                    }
                }
            }
        }

        return Pair.of(d, p);
    }

    private void displayProblem(Integer[][] m, int startPoint, int endPoint) {
        System.out.println("---Rozwiązywany problem algorytmem Bellmana-Forda---");
        System.out.format("Indeks wierzchołka startowego %s%n", startPoint);
        System.out.format("Indeks  wierzchołka końcowego %s%n", endPoint);

        AsciiTable table = new AsciiTable();
        table.addRule();
        String[] headers = new String[m.length+1];
        headers[0] = "Indeks";
        for(int i = 0; i < m.length; i++) {
            headers[i+1] = String.valueOf(i);
        }
        table.addRow(headers);
        for(int i = 0; i < m.length; i++) {
            String[] row = new String[m.length +1];
            row[0] = String.valueOf(i);
            for(int j = 1; j <= m.length; j++) {
                row[j] = M[i][j-1] == null ? "" : M[i][j-1].toString();
            }
            table.addRule();
            table.addRow(row);
        }
        table.addRule();
        System.out.println("Macierz sąsiedztwa:");
        System.out.println(table.render());
    }

    private void displaySolution(Integer[] d, Integer[] p, int startPoint, int endPoint) {
        System.out.println("---Rozwiązanie---");
        System.out.format("Długość ścieżki z wierzchołka startowego do końcowego wynosi: %s%n", d[endPoint]);
        StringBuilder textResult = new StringBuilder();
        Integer currentVertex = endPoint;
        while(currentVertex != startPoint) {
            textResult.append(currentVertex);
            textResult.append(" <- ");
            currentVertex = p[currentVertex];
        }
        textResult.append(currentVertex);
        System.out.format("Ścieżka w formie tekstowej: %n%s%n", textResult.toString());
    }
}
