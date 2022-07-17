package dijkstra;

import common.Algorithm;
import de.vandermeer.asciitable.AsciiTable;
import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class DijkstraAlgorithm implements Algorithm {

    private static final Integer[][] M = {
            {null, 2   , 3   , null, null},
            {null, null, null, 10  , null},
            {null, null, null, 7   , 13},
            {null, null, null, null, 1},
            {null, null, null, null, null}
    };
    private static final int START_POINT = 0;
    private static final int END_POINT = 4;

    private final Integer[][] m;
    private final int startPoint;
    private final int endPoint;

    public DijkstraAlgorithm() {
        this.startPoint = START_POINT;
        this.endPoint = END_POINT;
        this.m = M;
    }

    public DijkstraAlgorithm(int startPoint, int endPoint, Integer[][] m) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.m = m;
    }

    @Override
    public void run() {
        displayProblem(m, startPoint, endPoint);
        Pair<MutableInt[], Integer[]> result = run(m, startPoint);
        displaySolution(result.getLeft(), result.getRight(), startPoint, endPoint);
    }

    private Pair<Number, Integer> relax(MutableInt[] d, Integer[] p,  Integer[][] M, Integer u, Integer v) {
        if (u == null || v == null || M[u][v] == null ) {
            return Pair.of(d[v], p[v]);
        }
        if (d[v].compareTo(new MutableInt(d[u].getValue() + M[u][v])) > 0) {
            return Pair.of(new MutableInt(d[u].getValue() + M[u][v]), u);
        }
        return Pair.of(d[v], p[v]);
    }

    private List<Integer> getNeighbours(Integer[][] M, int u) {
        List<Integer> neighbours = new ArrayList<>(M.length);
        for(int i = 0; i < M.length; i++) {
            if (M[u][i] != null) {
                neighbours.add(i);
            }
        }
        return neighbours;
    }

    private Pair<MutableInt[], Integer[]> run(Integer[][] M, int s) {
        int n = M.length;
        MutableInt[] d = new MutableInt[n];
        Integer[] p = new Integer[n];
        for(int i = 0; i < n; i++) {
            d[i] = new MutableInt(Integer.MAX_VALUE);
        }
        d[s].setValue(0);
        ArrayPriorityQueue Q = new ArrayPriorityQueue(d);
        while (!Q.isEmpty()) {
            Integer u = Q.pollIndex();
            List<Integer> neighbours = getNeighbours(M, u);
            for(Integer v : neighbours) {
                Pair<Number, Integer> pair = relax(d, p, M, u, v);
                d[v].setValue(pair.getLeft());
                p[v] = pair.getRight();
            }
        }
    return Pair.of(d, p);
    }

    private void displayProblem(Integer[][] m, int startPoint, int endPoint) {
        System.out.println("---Rozwiązywany problem algorytmem Dijkstry---");
        System.out.format("Indeks wierzchołka startowego %s%n", startPoint);
        System.out.format("Indeks wierzchołka końcowego %s%n", endPoint);

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
                row[j] = m[i][j-1] == null ? "" : m[i][j-1].toString();
            }
            table.addRule();
            table.addRow(row);
        }
        table.addRule();
        System.out.println("Macierz sąsiedztwa:");
        System.out.println(table.render());
    }

    private void displaySolution(MutableInt[] d, Integer[] p, int startPoint, int endPoint) {
        System.out.println("---Rozwiązanie---");
        System.out.format("Długość ścieżki z wierzchołka startowego do końcowego wynosi: %s%n", d[endPoint].getValue());
        StringBuilder textResult = new StringBuilder();
        Integer currentVertex = endPoint;
        while(currentVertex != startPoint) {
            textResult.append(currentVertex);
            textResult.append(" <- ");
            currentVertex = p[currentVertex];
        }
        textResult.append(currentVertex);
        System.out.format("Ścieżka w formie tekstowej: %n%s%n", textResult);
    }
}
