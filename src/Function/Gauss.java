package Function;

import ADT_Matrix.*;

import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class Gauss {
    public static double[][] forwardOBE(double[][] m) {
        // OBE matriks gauss
        int row = MatrixOP.getRowEff(m);
        int col = MatrixOP.getColEff(m);

        int i = 0;
        int j = 0;

        while (i < row && j < col) {
            // mencari pivot
            int k = i;
            while (k < row && m[k][j] == 0) {
                k++;
            }

            if (k < row) {
                // swap baris
                MatrixOP.swapRow(m, i, k);

                // membuat pivot menjadi 1
                double pivot = m[i][j];
                for (int l = j; l < col; l++) {
                    /*
                     * 20.58 29/09/2023
                     */
                    if (pivot == 0) {
                        pivot = 1;
                    } else {
                        m[i][l] /= pivot;
                    }
                }

                // membuat kolom menjadi 0
                for (int l = 0; l < row; l++) {
                    if (l != i) {
                        double factor = m[l][j];
                        for (int n = j; n < col; n++) {
                            m[l][n] -= factor * m[i][n];
                        }
                    }
                }
                i++;

            }
            j++;
        }

        return m;

    }

    public static void matriksGauss(double[][] m) {
        System.out.println("Matriks Gauss:");
        MatrixOutput.printMatrix(forwardOBE(m));
    }

    public static void solGauss(double[][] m) {
        double[][] matrix = forwardOBE(m);
        int rowM = MatrixOP.getRowEff(matrix);
        int colM = MatrixOP.getColEff(matrix);

        System.out.println("Solusi SPL:");
        if (MatrixOP.solTidakAda(matrix)) {
            System.out.println("Tidak ada");
        } else if (MatrixOP.solBanyak(matrix)) {
            System.out.println("Banyak");
        } else {
            // mencari solusi SPL tunggal
        }
    }

    // public static double[][] mxSolGauss(double[][] m) {
    // double[][] matrix = forwardOBE(m);
    // int rowM = MatrixOP.getRowEff(matrix);
    // int colM = MatrixOP.getColEff(matrix);

    // double[][] sol = new double[rowM][1];
    // // System.out.println("Solusi SPL:");
    // for (int i = 0; i < rowM; i++) {
    // DecimalFormat df = new DecimalFormat("0.000");
    // sol[i][0] = (matrix[i][colM - 1]);
    // }
    // return sol;
    // }

}
