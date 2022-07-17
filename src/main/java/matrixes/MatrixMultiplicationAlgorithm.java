package matrixes;

import common.Algorithm;
import de.vandermeer.asciitable.AsciiTable;

public class MatrixMultiplicationAlgorithm implements Algorithm {

    private static final Integer[] P = {5, 10, 15, 5, 30, 10};

    private final Integer[] p;

    public MatrixMultiplicationAlgorithm() {
        this.p = P;
    }

    public MatrixMultiplicationAlgorithm(Integer[] p) {
        this.p = p;
    }

    @Override
    public void run() {
        this.displayProblem(this.p);
        this.run(this.p);
    }

    private void run(Integer... p) {
        int n = p.length - 1;
        int[][] M = new int[n][n];
        int[][] S = new int[n][n];
        String[][] R = new String[n][n];

        for (int i = 0; i < n; i++) {
            M[i][i] = 0;
            S[i][i] = 0;
            R[i][i] = String.format("A_%s", (i+1));
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                int l = j + i + 1;
                M[j][l] = Integer.MAX_VALUE;
                for (int k = j; k < l; k++) {
                    int potentialValue = M[j][k] + M[k + 1][l] + p[j] * p[k + 1] * p[l + 1];
                    if (potentialValue < M[j][l]) {
                        M[j][l] = potentialValue;
                        S[j][l] = k;
                        R[j][l] = String.format("(%s*%s)", R[j][k], R[k+1][l]);
                    }
                }
            }
        }
        System.out.println("---Rozwiązanie---");
        AsciiTable table = new AsciiTable();
        table.addRule();
        String[] headers = new String[n+1];
        headers[0] = "i/j";
        for(int i = 1; i <=n ; i++) {
            headers[i] = String.valueOf(i);
        }
        table.addRow(headers);
        for(int i = 0 ; i < n ; i++) {
            String[] row = new String[n+1];
            row[0] = String.valueOf(n-i);
            for(int j = 1; j <= n ; j++) {
                row[j] = String.valueOf(M[j-1][n-i-1]);
            }
            table.addRule();
            table.addRow(row);
        }
        table.addRule();
        System.out.println(table.render());
        System.out.println("Ilość potrzebnych mnożeń: " + M[0][n - 1]);
        System.out.println("Ostatnia wartość k: " + (S[0][n-1]+1));
        System.out.println("Nawiasowanie: " + R[0][n-1]);
    }
    void displayProblem(Integer... p) {
        System.out.println("---Rozwiązywany problem nawiasowania ciągu macierzy---");

        AsciiTable table = new AsciiTable();

        table.addRule();
        String[] firstRow = new String[p.length+1];
        firstRow[0] = "i";
        String[] secondRow = new String[p.length+1];
        secondRow[0] = "p";
        for(int i = 1; i <= p.length; i++) {
            firstRow[i] = String.valueOf(i-1);
            secondRow[i] = String.valueOf(p[i-1]);
        }
        table.addRow(firstRow);
        table.addRule();
        table.addRow(secondRow);
        table.addRule();
        System.out.println(table.render());
    }
}
