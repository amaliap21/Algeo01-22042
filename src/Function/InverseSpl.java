package Function;

import java.io.*;
import java.util.*;
import java.text.*;
import ADT_Matrix.*;

public class InverseSpl {
    public static double[][] inverseSpl(double[][] m) {
        double[][] matriksA = new double[MatrixOP.getRowEff(m)][MatrixOP.getColEff(m) - 1];
        double[][] matriksB = new double[MatrixOP.getRowEff(m)][1];
        int i, j;

        for (i = 0; i < MatrixOP.getRowEff(m); i++) {
            for (j = 0; j < MatrixOP.getColEff(m) - 1; j++) {
                matriksA[i][j] = m[i][j];
            }
        }

        for (i = 0; i < MatrixOP.getRowEff(m); i++) {
            matriksB[i][0] = m[i][MatrixOP.getColEff(m) - 1];
        }

        return (MatrixOP.multiplyMatrix(Inverse.balikanGJReturn(matriksA), matriksB));
    }

    public static void solInverse(double[][] m) {
        if (MatrixOP.determinant(m) != 0) {
            System.out.println("Solusi SPL:");
            for (int i = 0; i < MatrixOP.getRowEff(m); i++) {
                System.out.print("x" + (i + 1) + " = ");
                DecimalFormat df = new DecimalFormat("0.000");
                System.out.println(df.format(inverseSpl(m)[i][0]));
            }
        } else {
            System.out.println("Determinan matriks 0, tidak bisa dibalikkan sehingga tidak bisa diselesaikan");
        }
    }

    public static String strSol(double[][] m) {
        String strhasil = "";
        DecimalFormat df = new DecimalFormat("0.000");
        double[][] matriksA = new double[MatrixOP.getRowEff(m)][MatrixOP.getColEff(m) - 1];
        double[][] matriksB = new double[MatrixOP.getRowEff(m)][1];
        double[][] matriksHasil = new double[MatrixOP.getRowEff(m)][1];
        int i, j;

        for (i = 0; i < MatrixOP.getRowEff(m); i++) {
            for (j = 0; j < MatrixOP.getColEff(m) - 1; j++) {
                matriksA[i][j] = m[i][j];
            }
        }

        for (i = 0; i < MatrixOP.getRowEff(m); i++) {
            matriksB[i][0] = m[i][MatrixOP.getColEff(m) - 1];
        }

        matriksHasil = MatrixOP.multiplyMatrix(Inverse.inverseMatriks(matriksA), matriksB);

        for (i = 0; i < MatrixOP.getColEff(m) - 1; i++) {
            strhasil += "x" + (i + 1) + " = " + df.format(inverseSpl(matriksHasil)[i][0]) + "\n";
        }
        return strhasil;
    }

    public static boolean detEqual0(double[][] m) {
        double[][] matriksA = new double[MatrixOP.getRowEff(m)][MatrixOP.getColEff(m) - 1];
        int i, j;
        for (i = 0; i < MatrixOP.getRowEff(m); i++) {
            for (j = 0; j < MatrixOP.getColEff(m) - 1; j++) {
                matriksA[i][j] = m[i][j];
            }
        }

        if (MatrixOP.determinant(matriksA) == 0) {
            return true;
        } else {
            return false;
        }
    }
}