package Function;

import ADT_Matrix.*;
import java.io.*;
import java.util.*;

public class Bicubic {
    public static void fillMatrix(double[][] matrix, int i, int j, int k, int count) {
        int m, n;
        double x = 0, y = 0;
        for (m = 0; m < 4; m++) {
            for (n = 0; n < 4; n++) {
                // tackle kasus 0^0
                // double value = 1.0;

                // 0^0 harus dibuat jadi 1

                if (count == 0) {
                    x = Math.pow(i % 2, n);
                    y = Math.pow(j % 2, m);
                    // 0^0 harus dibuat jadi 1
                    if (i % 2 == 0 && n == 0) {
                        x = 1;
                    }
                    if (j % 2 == 0 && m == 0) {
                        y = 1;
                    }
                } else if (count == 1) {
                    x = Math.pow(i % 2, n - 1);
                    y = Math.pow(j % 2, m);
                    // 0^0 harus dibuat jadi 1
                    if (i % 2 == 0 && n == 1) {
                        x = 1;
                    }
                    if (j % 2 == 0 && m == 0) {
                        y = 1;
                    }

                } else if (count == 2) {
                    x = Math.pow(i % 2, n);
                    y = Math.pow(j % 2, m - 1);
                    // 0^0 harus dibuat jadi 1
                    if (i % 2 == 0 && n == 0) {
                        x = 1;
                    }
                    if (j % 2 == 0 && m == 1) {
                        y = 1;
                    }
                } else if (count == 3) {
                    x = Math.pow(i % 2, n - 1);
                    y = Math.pow(j % 2, m - 1);
                    // 0^0 harus dibuat jadi 1
                    if (i % 2 == 0 && n == 1) {
                        x = 1;
                    }
                    if (j % 2 == 0 && m == 1) {
                        y = 1;
                    }
                }
                matrix[i][k] = x * y;
                k++;
            }
        }
    }

    public static double[][] matriksBicubicX() {
        int rowCount = 16;
        int colCount = 16;
        double[][] matriksX = new double[rowCount][colCount];
        int count = 0;
        int j = 0;

        for (int i = 0; i < rowCount; i++) {
            int k = 0;

            if (i % 2 == 0 && (i != 0)) {
                j = 1;
            }

            fillMatrix(matriksX, i, j, k, count); // Menggunakan i % 2 dan j % 2

            if (i % 2 == 1 && j % 2 == 1) {
                j = 0;
            }

            if (i == 3 || i == 7 || i == 11) {
                count++;
            }
            if (count == 4) {
                count = 0;
            }
        }
        return matriksX;
    }

    public static double[][] matriksBicubicA(double[][] matrix) {
        double[][] matriksX = Inverse.inverseMatriks(matriksBicubicX());
        double[][] matriksA = MatrixOP.multiplyMatrix(matriksX, matrix);
        return matriksA;
    }

    /*
     * f(a,b) dengan a dan b merupakan input
     * a dan b adalah nilai x dan y pada fungsi:
     * f(x,y) = a_ij x^i y^j
     * f_x(x,y) = a_ij x^(i-1) y^j
     * f_y(x,y) = a_ij x^i j y^(j-1)
     * f_xy(x,y) = a_ij x^(i-1) j y^(j-1)
     */
    public static double hasilFungsi(double[][] matriksA, double[][] matriksAB) {
        // [A, B] {matriksAB}
        // f(A,B) = a_ij A^i B^j
        // matriksAB = [[A, B]] {1 baris, 2 kolom}
        // f(-0.5,-0,5) = a_ij (-0.5)^i (-0.5)^j
        // matriskA -> 1 kolom1
        double value = 0;

        int m, n;
        for (int i = 0; i < 16; i++) {
            for (m = 0; m < 4; m++) {
                for (n = 0; n < 4; n++) {
                    value += matriksA[i][0] * Math.pow(matriksAB[0][0], n) * Math.pow(matriksAB[0][1], m);
                }
            }
        }

        return value;
    }
}