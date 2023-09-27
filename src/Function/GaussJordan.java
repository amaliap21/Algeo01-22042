package Function;

import ADT_Matrix.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class GaussJordan {
    // setelah melakukan forward OBE, lakukan backward OBE (baris eselon tereduksi)
    public static double[][] backwardOBE(double[][] m) {
        m = Gauss.forwardOBE(m);
        double[][] matrix = MatrixOP.copyMatrix(m);

        int rowM = MatrixOP.getRowEff(matrix);
        int colM = MatrixOP.getColEff(matrix);

        for (int i = 0; i < rowM; i++) {
            for (int j = i + 1; j < rowM; j++) {
                double multiplier = matrix[i][j];
                for (int k = i; k < colM; k++) {
                    matrix[i][k] -= multiplier * matrix[j][k];
                }
            }
        }
        return matrix;
    }

    public static double[][] gaussJordan(double[][] m) {
        Gauss.forwardOBE(m);
        return GaussJordan.backwardOBE(m);
    }

    public static void matriksGaussJordan(double[][] m) {
        System.out.println("Matriks Gauss Jordan:");
        MatrixOutput.printMatrix(gaussJordan(m));
    }

    public static void solGaussJordan(double[][] m) {
        double[][] matrix = GaussJordan.gaussJordan(m);
        int rowM = MatrixOP.getRowEff(matrix);
        int colM = MatrixOP.getColEff(matrix);

        System.out.println("Solusi SPL:");
        if (MatrixOP.solTidakAda(matrix)) {
            System.out.println("Tidak ada");
        } else if (MatrixOP.solBanyak(matrix)) {
            System.out.println("Banyak");
        } else {
            for (int i = 0; i < rowM; i++) {
                System.out.print("x" + (i + 1) + " = ");
                DecimalFormat df = new DecimalFormat("0.000");
                System.out.println(df.format(matrix[i][colM - 1]));
            }
        }
    }

    public static double[][] mxSolGaussJordan(double[][] m) {
        double[][] matrix = GaussJordan.gaussJordan(m);
        int rowM = MatrixOP.getRowEff(matrix);
        int colM = MatrixOP.getColEff(matrix);
        double[][] sol = new double[rowM][1];
        // System.out.println("Solusi SPL:");
        for (int i = 0; i < rowM; i++) {
            DecimalFormat df = new DecimalFormat("0.000");
            sol[i][0] = (matrix[i][colM - 1]);
        }
        return sol;
    }
}