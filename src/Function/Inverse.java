package Function;

import java.io.*;
import java.util.*;
import ADT_Matrix.*;

public class Inverse {
    // metode cofactor
    public static double[][] inverseMatriks(double[][] m) {
        if (MatrixOP.determinant(m) == 0) {
            System.out.println("Determinan matriks 0, tidak bisa dibalikkan");
            return null;
        }

        return (MatrixOP.multiplyByConst(Cofactor.adj(m), (1 / MatrixOP.determinant(m))));
    }

    // print matriks balikan (cofactor)
    public static void matriksInverse(double[][] m) {
        if (MatrixOP.determinant(m) == 0) {
            System.out.println("Determinan matriks 0, tidak bisa dibalikkan");
        } else {
            System.out.println("Matriks balikan (menggunakan matriks kofaktor):");
            MatrixOutput.printMatrix(inverseMatriks(m));
        }
    }

    // metode Gauss-Jordan ([A|I] = [I|A^-1])
    public static double[][] balikanGJ(double[][] m) {
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

    // return matriks balikan (Gauss-Jordan)
    public static double[][] balikanGJReturn(double[][] m) {
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
            // MARK BEDA!!!
            for (int j = 0; j < rowM * 2; j++) {
                if (j < rowM) {
                    mGabung[i][j] = m[i][j];
                } else {
                    mGabung[i][j] = mIdentitas[i][j - rowM];
                }
            }
            // MatrixOutput.printMatrix(mGabung);
            // System.out.println();
        }
        MatrixOutput.printMatrix(mGabung);
        System.out.println();

        System.out.println("MULAI DI GAUSS-JORDAN KAN");
        mGabung = GaussJordan.gaussJordan(mGabung);

        // Ngambil matriks balikan dari matriks mGabung
        // A|I = I | A^-1
        double[][] mBalikan = new double[rowM][rowM];
        for (int i = 0; i < rowM; i++) {
            for (int j = rowM; j < rowM * 2; j++) {
                mBalikan[i][j - rowM] = mGabung[i][j];
            }
            MatrixOutput.printMatrix(mGabung);
            System.out.println();

        }

        return mBalikan;
    }

    // print matriks balikan (Gauss-Jordan)
    public static void matriksInverseGJ(double[][] m) {
        if (MatrixOP.determinant(m) == 0) {
            System.out.println("Determinan matriks 0, tidak bisa dibalikkan");
        } else {
        }
        System.out.println("Matriks Balikan Gauss-Jordan:");
        MatrixOutput.printMatrix(balikanGJ(m));
    }
}