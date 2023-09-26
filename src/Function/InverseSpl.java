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

        return (MatrixOP.multiplyMatrix(Inverse.inverseMatriks(matriksA), matriksB));
    }

    public static void solusiInverse(double[][] m) {
        InverseSpl.inverseSpl(m);
        int i;
        DecimalFormat df = new DecimalFormat("0.000");

        for (i = 0; i < MatrixOP.getRowEff(m); i++) {
            System.out.print("x" + (i + 1) + " = ");
            System.out.println(df.format(InverseSpl.inverseSpl(m)[i][0]));
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
