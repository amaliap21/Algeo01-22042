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
            System.out.println("Determinan matriks 0, tidak bisa dibalikkan");
        }
    }
}

// matriks m
// 1 2 3 4
// 1 2 3 4
// 1 2 3 4

// matriksA =
// 1 2 3
// 1 2 3
// 1 2 3

// matriksB =
// 4
// 4
// 4
