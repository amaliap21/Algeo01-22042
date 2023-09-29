package Function;

import ADT_Matrix.*;
import java.io.*;
import java.util.*;

public class Bicubic {
    public static void fillMatrix(double[][] matrix, int i, int j, int k, int count) {
        int m, n;
        double value = 1.0;
        double x = 0, y = 0;

        for (m = 0; m < 4; m++) {
            for (n = 0; n < 4; n++) {
                // tackle kasus 0^0
                // double value = 1.0;

                // 0^0 harus dibuat jadi 1
                /*
                 * f(x,y) = a_ij x^i y^j
                 * f_x(x,y) = a_ij i x^(i-1) y^j
                 * f_y(x,y) = a_ij j x^i j y^(j-1)
                 * f_xy(x,y) = a_ij ij x^(i-1) j y^(j-1)
                 */
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
                    value = x * y;
                } else if (count == 1) {
                    x = Math.pow(i % 2, Math.abs(n - 1));
                    y = Math.pow(j % 2, m);
                    // 0^0 harus dibuat jadi 1
                    if (i % 2 == 0 && n == 1) {
                        x = 1;
                    }
                    if (j % 2 == 0 && m == 0) {
                        y = 1;
                    }
                    value = n * x * y;

                } else if (count == 2) {
                    x = Math.pow(i % 2, n);
                    y = Math.pow(j % 2, Math.abs(m - 1));
                    // 0^0 harus dibuat jadi 1
                    if (i % 2 == 0 && n == 0) {
                        x = 1;
                    }
                    if (j % 2 == 0 && m == 1) {
                        y = 1;
                    }
                    value = m * x * y;
                } else if (count == 3) {
                    x = Math.pow(i % 2, Math.abs(n - 1));
                    y = Math.pow(j % 2, Math.abs(m - 1));
                    // 0^0 harus dibuat jadi 1
                    if (i % 2 == 0 && n == 1) {
                        x = 1;
                    }
                    if (j % 2 == 0 && m == 1) {
                        y = 1;
                    }
                    value = n * m * x * y;
                }
                matrix[i][k] = value;
                k++;
            }
        }
    }

    public static double[][] matriksBicubicX() {
        double[][] matriksX = new double[16][16];
        int count = 0;
        int j = 0;

        for (int i = 0; i < 16; i++) {
            int k = 0;

            if (i % 2 == 0 && (i != 0)) {
                j = 1;
            }
            if ((i % 4 == 0) && (i != 0)) {
                j = 0;
            }

            fillMatrix(matriksX, i, j, k, count);

            if (i == 3 || i == 7 || i == 11) {
                count++;
            }
            if (count == 4) {
                count = 0;
            }
        }
        return matriksX;
    }

    public static double[][] matriksBicubicA(double[][] inputMatriks) {
        double[][] matriksA = MatrixOP.multiplyMatrix(Inverse.balikanGJReturn(matriksBicubicX()), inputMatriks);
        return matriksA;
    }

    // value f(A,B) = a_ij (A)^i (B)^j
    // terdapat matriks 1x2 yang berisi nilai A dan B
    public static double hasilBicubic(double[][] matriksA, double[][] matriksAB) {
        double hasil = 0;
        double x = 0, y = 0;

        int k = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; i++) {
                x = Math.pow(matriksAB[0][0], i);
                y = Math.pow(matriksAB[0][1], j);
                
                // 0^0 harus dibuat jadi 1

                if (matriksAB[0][0] == 0 && i == 0) {
                    x = 1;
                }
                if (matriksAB[0][1] == 0 && j == 0) {
                    y = 1;
                }

                hasil += matriksA[k][0] * x * y;

                k++;
            }
        }

        return hasil;
    }

    /*
     * f(a,b) dengan a dan b merupakan input
     * a dan b adalah nilai x dan y pada fungsi:
     * f(x,y) = a_ij x^i y^j
     * f_x(x,y) = a_ij i x^(i-1) y^j
     * f_y(x,y) = a_ij j x^i j y^(j-1)
     * f_xy(x,y) = a_ij ij x^(i-1) j y^(j-1)
     */
}