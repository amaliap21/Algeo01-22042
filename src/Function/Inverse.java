package Function;

import java.io.*;
import java.util.*;
import ADT_Matrix.*;

public class Inverse {
    public static double determinanInverse(double[][] m) {
        // Prekondisi: matriks m adalah matriks persegi

        int i, j, k;
        int rowM = MatrixOP.getRowEff(m);
        int colM = MatrixOP.getColEff(m);

        double[][] m2 = new double[rowM][colM];
        double det = 1, temp;

        for (i = 0; i < rowM; i++) {
            for (j = 0; j < colM; j++) {
                m2[i][j] = MatrixOP.getElmt(m, i, j);
            }
        }

        int rowM2 = MatrixOP.getRowEff(m2);
        int colM2 = MatrixOP.getColEff(m2);

        for (i = 0; i < rowM2; i++) {
            for (j = i + 1; j < rowM2; j++) {
                temp = MatrixOP.getElmt(m2, j, i) / MatrixOP.getElmt(m2, i, i);
                for (k = 0; k < colM2; k++) {
                    m2[j][k] = MatrixOP.getElmt(m2, j, k) - (MatrixOP.getElmt(m2, i, k) * temp);
                }
            }
        }

        for (i = 0; i < rowM2; i++) {
            det *= MatrixOP.getElmt(m2, i, i);
        }

        return (float) det;
    }

    public static double[][] inverseMatriks(double[][] m) {
        if (determinanInverse(m) == 0) {
            System.out.println("Determinan matriks 0, tidak bisa dibalikkan");
        }

        return (MatrixOP.multiplyByConst(Cofactor.adj(m), (1 / determinanInverse(m))));
    }

    public static void matriksInverse(double[][] m) {
        System.out.println("Matriks balikan (menggunakan matriks kofaktor):");
        MatrixOutput.printMatrix(inverseMatriks(m));
    }

    public static double[][] balikanGJ(double[][] m) {
        // [A|I] = [I|A^-1]
        int rowM = MatrixOP.getRowEff(m);

        // Buat matriks identitas
        double[][] mIdentitas = new double[rowM][rowM];
        for (int i = 0; i < rowM; i++) {
            for (int j = 0; j < rowM; j++) {
                if (i == j) {
                    mIdentitas[i][i] = 1;
                } else {
                    mIdentitas[i][j] = 0;
                }
            }
        }

        // Gabungin matriks identitas dengan matriks m
        double[][] mGabung = new double[rowM][rowM * 2];
        for (int i = 0; i < rowM; i++) {
            for (int j = 0; j < rowM * 2; j++) {
                if (j < rowM) {
                    mGabung[i][j] = m[i][j];
                } else {
                    mGabung[i][j] = mIdentitas[i][j - rowM];
                }
            }
        }

        mGabung = GaussJordan.gaussJordan(mGabung);

        // // Ngambil matriks balikan dari matriks mGabung
        // double[][] mBalikan = new double[rowM][rowM];
        // for (int i = 0; i < rowM; i++) {
        // for (int j = rowM; j < rowM * 2; j++) {
        // mBalikan[i][j - rowM] = mGabung[i][j];
        // }
        // }

        // return mBalikan;
        return mGabung;
    }

    public static void matriksInverseGJ(double[][] m) {
        System.out.println("Matriks Balikan Gauss-Jordan:");
        MatrixOutput.printMatrix(balikanGJ(m));
    }
}