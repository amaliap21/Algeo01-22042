package ADT_Matrix;

import java.text.DecimalFormat;

public class MatrixOutput {
    public static void printMatrix(double[][] matrix) {
        int i, j;
        DecimalFormat df = new DecimalFormat("0.0");
        for (i = 0; i < matrix.length; i++) {
            for (j = 0; j < matrix[0].length; j++) {
                System.out.print(df.format(matrix[i][j]) + "  ");
            }
            System.out.println();
        }
    }
}
