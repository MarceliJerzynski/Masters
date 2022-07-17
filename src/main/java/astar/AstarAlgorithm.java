package astar;

import common.Algorithm;
import common.ResultNotFoundException;
import de.vandermeer.asciitable.AsciiTable;
import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class AstarAlgorithm implements Algorithm {

    private static final Integer[][] M = {
            {null, 2   , null, null, 3   },
            {null, null, 6   , null, null},
            {null, null, null, 7   , null},
            {null, null, null, null, null},
            {null, null, null, 8   , null}
    };

    private static final Integer[] H = {13, 12, 1, 0, 11};

    private static final int START_POINT = 0;
    private static final int END_POINT = 3;

    private final Integer[][] m;
    private final Integer[] h;
    private final int startPoint;
    private final int endPoint;

    public AstarAlgorithm() {
        this.startPoint = START_POINT;
        this.endPoint = END_POINT;
        this.m = M;
        this.h = H;
    }

    public AstarAlgorithm(int startPoint, int endPoint, Integer[][] m, Integer[] h) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.m = m;
        this.h = h;
    }


    @Override
    public void run() {
        displayProblem(m, h, startPoint, endPoint);
        Pair<MutableInt[], Integer[]> result = run(m, h, startPoint, endPoint);
        displaySolution(result.getLeft(), result.getRight(), startPoint, endPoint);
    }

    private Pair<MutableInt, Integer> relax(MutableInt[] d, Integer[] p,  Integer[][] M, Integer u, Integer v) {
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

    private Pair<MutableInt[], Integer[]> run(Integer[][] M, Integer[] H, int s, int endPoint) {
        int n = M.length;
        MutableInt[] d = new MutableInt[n];
        Integer[] p = new Integer[n];
        for(int i = 0; i < n; i++) {
            d[i] = new MutableInt(Integer.MAX_VALUE - 100); //TODO
        }
        d[s].setValue(0);
        ArrayHeuristicPriorityQueue Q = new ArrayHeuristicPriorityQueue(d, H);
        //nie działa, bo w kolejce sa inne obiekty niz te tutaj! To samo w Dijkstra, działa przypadkiem

        while (!Q.isEmpty()) {
            Integer u = Q.pollIndex();
            if (u == endPoint) {
                return Pair.of(d, p);
            }
            List<Integer> neighbours = getNeighbours(M, u);
            for(Integer v : neighbours) {
                Pair<MutableInt, Integer> pair = relax(d, p, M, u, v);
                d[v].setValue(pair.getLeft());
                p[v] = pair.getRight();
            }
        }
        throw new ResultNotFoundException("Something went wrong, algorithm didn't find result");
    }

    private void displayProblem(Integer[][] m, Integer[] h, int startPoint, int endPoint) {
        System.out.println("---Rozwiązywany problem algorytmem heurystycznym A*---");
        System.out.format("Indeks wierzchołka startowego %s%n", startPoint);
        System.out.format("Indeks wierzchołka końcowego %s%n", endPoint);

        AsciiTable graphTable = new AsciiTable();
        graphTable.addRule();
        String[] headers = new String[m.length+1];
        headers[0] = "Indeks";
        for(int i = 0; i < m.length; i++) {
            headers[i+1] = String.valueOf(i);
        }
        graphTable.addRow(headers);
        for(int i = 0; i < m.length; i++) {
            String[] row = new String[m.length +1];
            row[0] = String.valueOf(i);
            for(int j = 1; j <= m.length; j++) {
                row[j] = m[i][j-1] == null ? "" : m[i][j-1].toString();
            }
            graphTable.addRule();
            graphTable.addRow(row);
        }
        graphTable.addRule();
        System.out.println("Macierz sąsiedztwa:");
        System.out.println(graphTable.render());

        AsciiTable heuresticsTable = new AsciiTable();
        String[] firstRow = new String[m.length+1];
        firstRow[0] = "Indeks";
        String[] secondRow = new String[m.length+1];
        secondRow[0] = "Heurystyka";
        for(int i = 0; i < m.length; i++) {
            firstRow[i+1] = String.valueOf(i+1);
            secondRow[i+1] = String.valueOf(h[i]);
        }

        heuresticsTable.addRule();
        heuresticsTable.addRow(firstRow);
        heuresticsTable.addRule();
        heuresticsTable.addRow(secondRow);
        heuresticsTable.addRule();
        System.out.println("Heurystyki:");
        System.out.println(heuresticsTable.render());

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
        System.out.format("Ścieżka w formie tekstowej: %n%s%n", textResult.toString());
    }
}
