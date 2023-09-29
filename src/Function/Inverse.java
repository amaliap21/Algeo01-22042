package Function;

import java.io.*;
import java.util.*;
import ADT_Matrix.*;

public class Inverse {
    // metode cofactor
    public static double[][] inverseMatriks(double[][] m) {
        if (MatrixOP.determinant(m) == 0) {
            System.out.println("Determinan matriks 0 / Matriks bukan merupakan matriks persegi, tidak bisa dibalikkan");
            return null;
        }

        return (MatrixOP.multiplyByConst(Cofactor.adj(m), (1 / Cofactor.determinanCof(m))));
    }

    // print matriks balikan (cofactor)
    public static void matriksInverse(double[][] m) {
        if (MatrixOP.determinant(m) == 0) {
            System.out.println("Determinan matriks 0 / Matriks bukan merupakan matriks persegi, tidak bisa dibalikkan");
        } else {
            System.out.println("Matriks balikan (menggunakan matriks kofaktor):");
            MatrixOutput.printMatrix(inverseMatriks(m));
        }
    }

    // metode Gauss-Jordan ([A|I] = [I|A^-1])
    public static double[][] gaussJordanBalikan(double[][] m) {
        // MatriksX itu tuh matriks yang bakal jadi hasil Matriks dari metode
        // Gauss-Jordan
        double[][] matrix = MatrixOP.copyMatrix(m);

        // Index baris dan kolom yang efektif dari matriks m atau matrix (sama aja)
        int rowM = MatrixOP.getRowEff(matrix);
        int colM = MatrixOP.getColEff(matrix);

        // Proses metode Gauss-Jordan
        for (int i = 0; i < rowM; i++) {
            // Nyari indeks dengan elemen terbesar pada suatu kolom
            // Tujuan: buat swapping baris
            int max = i;
            for (int j = i + 1; j < rowM; j++) {
                if (Math.abs(matrix[j][i]) > Math.abs(matrix[max][i])) {
                    max = j;
                }
            }

            // Swapping baris
            double[] temp = matrix[i];
            matrix[i] = matrix[max];
            matrix[max] = temp;

            // Buat elemennya jadi 1
            double divider = matrix[i][i];
            for (int j = i; j < colM; j++) {
                matrix[i][j] /= divider;
            }

            // Ngurangin baris lain dengan baris yang sudah dikurangi
            for (int j = 0; j < rowM; j++) {
                if (j != i) {
                    double multiplier = matrix[j][i];
                    for (int k = i; k < colM; k++) {
                        matrix[j][k] -= multiplier * matrix[i][k];
                    }
                }
            }
        }

        return matrix;
    }

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

        mGabung = gaussJordanBalikan(mGabung);

        return mGabung;
    }

    // print matriks balikan (Gauss-Jordan)
    public static void matriksInverseGJ(double[][] m) {
        if (MatrixOP.determinant(m) == 0) {
            System.out.println("Determinan matriks / Matriks bukan merupakan matriks persegi, tidak bisa dibalikkan");
        } else {
        }
        System.out.println("Matriks Balikan Gauss-Jordan:");
        MatrixOutput.printMatrix(balikanGJ(m));
    }
}