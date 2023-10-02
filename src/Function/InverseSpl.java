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

    public static String[] arrayResultUniqueSol(double[][] m){
        String[] arrayhasil = new String[MatrixOP.getColEff(m)-1];
        DecimalFormat df = new DecimalFormat("0.000");

        for (int i=0; i<MatrixOP.getColEff(m)-1; i++){
            arrayhasil[i] = String.valueOf(df.format(inverseSpl(m)[i][0]));
        }
        return arrayhasil;
    }

    public static void fileOfResult(double[][] m){

        if (MatrixOP.determinant(m) == 0) {
            String[] result = new String[MatrixOP.getColEff(m)-1];
            result[0] = "Tidak ada solusi";
            MatrixOutput.SPLtoFile(result);
        } else{
            MatrixOutput.SPLtoFile(arrayResultUniqueSol(m));
            // mencari solusi SPL tunggal
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