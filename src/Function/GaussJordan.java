package Function;

import ADT_Matrix.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class GaussJordan {
    // setelah melakukan forward OBE, lakukan backward OBE (baris eselon tereduksi)
    public static double[][] gaussJordan(double[][] m) {
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
                /*
                 * 08.24 30/09/2023
                 */
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
            Gauss.parametriksolution(matrix);
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

    public static String strResultUniqueSol(double[][] m){
        double[][] matrix = GaussJordan.gaussJordan(m);
        int rowM = MatrixOP.getRowEff(m);
        int colM = MatrixOP.getColEff(m);

        String result = "";//new String[MatrixOP.getColEff(m)-1];
        DecimalFormat df = new DecimalFormat("0.000");
        
        for(int i = 0; i<MatrixOP.getColEff(m)-1; i++){
            result += "x"+(i+1)+" = "+df.format(matrix[i][colM - 1])+"\n";
        }
        return result;
    }
}