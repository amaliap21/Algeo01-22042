package ADT_Matrix;
import java.util.*;
import java.io.*;
import java.text.*;

public class MatrixOutput {
    public static void printMatrix(double[][] matrix) {
        int i, j;
        DecimalFormat df = new DecimalFormat("0.000");
        for (i = 0; i < matrix.length; i++) {
            for (j = 0; j < matrix[0].length; j++) {
                System.out.print(df.format(matrix[i][j]) + "\t");
            }
            System.out.println();
        }
    }
}