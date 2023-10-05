package Function;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import ADT_Matrix.*;

public class Cramer {
    public static double[][] replace(double[][] m, int n) {
        double[][] newM = MatrixOP.copyMatrix(m);
        double[][] square = new double[MatrixOP.getRowEff(newM)][MatrixOP.getRowEff(newM)];
        int i, j;

        for (i = 0; i < MatrixOP.getRowEff(newM); i++) {
            newM[i][n] = m[i][MatrixOP.getLastIdxCol(newM)];
            // for(j = 0; j < MatrixOP.getRowEff(newM); j++){
            // }
        }
        for (i = 0; i < MatrixOP.getRowEff(newM); i++) {
            for (j = 0; j < MatrixOP.getRowEff(newM); j++) {
                square[i][j] = newM[i][j];
            }
        }
        return square;
    }

    public static void SPLCramer(double[][] m) {
        // System.out.println("Input n (n adalah indeks kolom yang ingin ditukar)");
        // Scanner input = new Scanner(System.in);
        // int n = input.nextInt();
        double det_pure, det_replace = 0;
        int i, j;
        double[][] newM = new double[MatrixOP.getRowEff(m)][MatrixOP.getRowEff(m)];
        for (i = 0; i < MatrixOP.getRowEff(m); i++) {
            for (j = 0; j < MatrixOP.getRowEff(m); j++) {
                newM[i][j] = m[i][j];
            }
        }
        double[][] sol = new double[MatrixOP.getRowEff(newM)][1];
        det_pure = MatrixOP.determinant(newM);
        for (j = 0; j < MatrixOP.getColEff(newM); j++) {
            det_replace = MatrixOP.determinant(replace(m, j));
            if (det_pure != 0.0) {
                MatrixOP.setElmt(sol, j, 0, det_replace / det_pure);
            }
        }
        System.out.println("Solusi SPL:");
        for (j = 0; j < MatrixOP.getRowEff(m); j++) {
            System.out.print("x" + (j + 1) + " = ");
            DecimalFormat df = new DecimalFormat("0.000");
            System.out.println(df.format(sol[j][0]));
        }
    }

    public static double[][] matriksACramer(double[][] m) {
        int rowA = MatrixOP.getRowEff(m);
        int colA = rowA;

        double[][] matriksA = new double[rowA][colA];

        for (int i = 0; i < rowA; i++) {
            for (int j = 0; j < colA; j++) {
                matriksA[i][j] = m[i][j];
            }
        }
        return matriksA;
    }

    public static double[][] matriksBCramer(double[][] m) {
        int rowA = MatrixOP.getRowEff(m);
        int colA = MatrixOP.getColEff(m);

        double[][] matriksB = new double[rowA][1];

        for (int i = 0; i < rowA; i++) {
            matriksB[i][0] = m[i][colA - 1];
        }
        return matriksB;
    }

    public static double[][] matriksXCramer(double[][] matriksACramer, double[][] matriksBCramer) {
        int rowA = MatrixOP.getRowEff(matriksACramer);
        int colA = MatrixOP.getColEff(matriksACramer);

        double[][] matriksX = new double[rowA][1];

        double detA = Triangle.detUpperTriangular(matriksACramer);
        double detX;

        for (int i = 0; i < rowA; i++) {
            double[][] matriksA1 = new double[rowA][colA];
            for (int j = 0; j < rowA; j++) {
                for (int k = 0; k < colA; k++) {
                    if (k == i) {
                        matriksA1[j][k] = matriksBCramer[j][0];
                    } else {
                        matriksA1[j][k] = matriksACramer[j][k];
                    }
                }
            }
            detX = Triangle.detUpperTriangular(matriksA1);
            matriksX[i][0] = detX / detA;
        }
        return matriksX;
    }

    public static void solCramer(double[][] m) {
        double[][] solusi = matriksXCramer(matriksACramer(m), matriksBCramer(m));

        System.out.println("Solusi SPL:");
        for (int i = 0; i < MatrixOP.getRowEff(m); i++) {
            System.out.print("x" + (i + 1) + " = ");
            DecimalFormat df = new DecimalFormat("0.000");
            System.out.println(df.format(solusi[i][0]));
        }
    }

    public static String strSol(double[][] m) {
        String result = ""; // new String[MatrixOP.getColEff(m)-1];
        double[][] solusi;
        int i;
        if (MatrixOP.getRowEff(m) >= 8) {
            solusi = matriksXCramer(matriksACramer(m), matriksBCramer(m));
            DecimalFormat df = new DecimalFormat("0.000");
            for (i = 0; i < MatrixOP.getColEff(m) - 1; i++) {
                result += "x" + (i + 1) + " = " + df.format(solusi[i][0]) + "\n";
            }
        } else {
            solusi = cramerUnique(m);
            DecimalFormat df = new DecimalFormat("0.000");
            for (i = 0; i < MatrixOP.getColEff(m) - 1; i++) {
                result += "x" + (i + 1) + " = " + df.format(solusi[i][0]) + "\n";
            }
        }
        return result;
    }

    public static double[][] cramerUnique(double[][] m) {
        double det_pure, det_replace = 0;
        int i, j;
        double[][] newM = new double[MatrixOP.getRowEff(m)][MatrixOP.getRowEff(m)];
        for (i = 0; i < MatrixOP.getRowEff(m); i++) {
            for (j = 0; j < MatrixOP.getRowEff(m); j++) {
                newM[i][j] = m[i][j];
            }
        }

        double[][] sol = new double[MatrixOP.getRowEff(newM)][1];
        det_pure = MatrixOP.determinant(newM);
        for (j = 0; j < MatrixOP.getColEff(newM); j++) {
            det_replace = MatrixOP.determinant(replace(m, j));
            if (det_pure != 0.0) {
                sol[j][0] = det_replace / det_pure;
            }
        }
        return sol;
    }
}