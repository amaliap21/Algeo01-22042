package Function;

import java.io.*;
import java.util.*;
import ADT_Matrix.*;

public class InverseSpl {
    public static double[][] inverseMatriks(double[][] m) {
        double[][] matriksA = new double[MatrixOP.getRowEff(m)][MatrixOP.getColEff(m) - 1];
        double[][] matriksB = new double[MatrixOP.getRowEff(m)][1];
        int i, j;

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

        for (i = 0; i < MatrixOP.getRowEff(m); i++) {
            for (j = 0; j < MatrixOP.getColEff(m) - 1; j++) {
                matriksA[i][j] = m[i][j];
            }
        }

        for (i = 0; i < MatrixOP.getRowEff(m); i++) {
            matriksB[i][0] = m[i][MatrixOP.getColEff(m) - 1];
        }

        return (MatrixOP.multiplyMatrix(inverseMatriks(matriksA), matriksB));
    }
}