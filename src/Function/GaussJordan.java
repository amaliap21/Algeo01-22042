package Function;

import ADT_Matrix.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class GaussJordan {
    public static double[][] gaussJordan(double[][] m) {
        // MatriksX itu tuh matriks yang bakal jadi hasil Matriks dari metode
        // Gauss-Jordan
        double[][] matrix = MatrixOP.copyMatrix(m);

        // Index baris dan kolom yang efektif dari matriks m atau matrix (sama aja)
        int rowM = MatrixOP.getRowEff(matrix);
        int colM = MatrixOP.getColEff(matrix);

        // Proses metode Gauss-Jordan
        for (int i = 0; i < rowM; i++) {
            // Nyari indeks dengan elemen terbesar pada suatu kolom
            // Tujuan: buat swapping baris
            int max = i;
            for (int j = i + 1; j < rowM; j++) {
                if (Math.abs(matrix[j][i]) > Math.abs(matrix[max][i])) {
                    max = j;
                }
            }

            // Swapping baris
            double[] temp = matrix[i];
            matrix[i] = matrix[max];
            matrix[max] = temp;

            // Buat elemennya jadi 1
            double divider = matrix[i][i];
            for (int j = i; j < colM; j++) {
                matrix[i][j] /= divider;
            }

            // Ngurangin baris lain dengan baris yang sudah dikurangi
            for (int j = 0; j < rowM; j++) {
                if (j != i) {
                    double multiplier = matrix[j][i];
                    for (int k = i; k < colM; k++) {
                        matrix[j][k] -= multiplier * matrix[i][k];
                    }
                }
            }
        }

        return matrix;
    }

    public static void matriksGaussJordan(double[][] m){
        System.out.println("Matriks Gauss Jordan:");
        MatrixOutput.printMatrix(gaussJordan(m));
    }

    public static void solGaussJordan(double[][] m) {
        double[][] matrix = GaussJordan.gaussJordan(m);
        int rowM = MatrixOP.getRowEff(matrix);
        int colM = MatrixOP.getColEff(matrix);

        System.out.println("Solusi SPL:");
        for (int i = 0; i < rowM; i++) {
            System.out.print("x" + (i + 1) + " = ");
            DecimalFormat df = new DecimalFormat("0.000");
            System.out.println(df.format(matrix[i][colM - 1]));
        }
    }
}