package Function;

import ADT_Matrix.*;
import java.io.*;
import java.util.*;

public class Bicubic {
    public static void fillMatrix(double[][] matrix, int i, int j, int count) {
        int m, n;
        for (m = 0; m < 4; m++) {
            for (n = 0; n < 4; n++) {
                double value = 1.0;
                if (count == 0) {
                    value = Math.pow(i, n) * Math.pow(j, m);
                } else if (count == 1) {
                    value = Math.pow(i, n - 1) * Math.pow(j, m);
                } else if (count == 2) {
                    value = Math.pow(i, n) * Math.pow(j, m - 1);
                } else if (count == 3) {
                    value = Math.pow(i, n - 1) * Math.pow(j, m - 1);
                }
                matrix[i][j] = value;
                j++;
            }
        }
    }

    public static double[][] matriksBicubicX(double[][] matrix) {
        int rowCount = 16;
        int colCount = 16;
        double[][] matriksX = new double[rowCount][colCount];
        int count = 0;

        for (int i = 0; i < rowCount; i++) {
            int j = 0;
            fillMatrix(matriksX, i % 2, j % 2, count); // Menggunakan i % 2 dan j % 2
            count++;
            if (count == 4) {
                count = 0;
            }
        }
        return matriksX;
    }

    public static double[][] matriksBicubicA(double[][] matrix) {
        double[][] matriksX = Inverse.inverseMatriks(matriksBicubicX(matrix));
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
    public static double hasilFungsi(double[][] matrix, double matriksAB) {
        double result = 0;
        int count = 0;
        for (int i = 0; i < 4; i++) {
            double temp = 0;
            for (int j = 0; j < 4; j++) {
                temp += matrix[count][0] * Math.pow(j, i) * Math.pow(j, j);
                count++;
            }
            result += temp;
        }
        return result;
    }
}