package Main;

import ADT_Matrix.*;
import java.io.*;
import java.util.*;

public class InversSpl {
    public static void fungsiF(double[][] matriksX, int i, int j, int count) {
        // f(x,y)
        int m, n;
        for (m = 0; m < 4; m++) {
            for (n = 0; n < 4; n++) {
                if (count == 0) {
                    matriksX[i][j] = Math.pow(0, n) * Math.pow(0, m);
                } else if (count == 1) {
                    matriksX[i][j] = Math.pow(1, n) * Math.pow(0, m);

                } else if (count == 2) {
                    matriksX[i][j] = Math.pow(0, n) * Math.pow(1, m);

                } else if (count == 3) {
                    matriksX[i][j] = Math.pow(1, n) * Math.pow(1, m);
                }
                j++;
            }
        }
    }

    public static void fungsiFX(double[][] matriksX, int i, int j, int count) {
        // f(x,y)
        int m, n;
        for (m = 0; m < 4; m++) {
            for (n = 0; n < 4; n++) {
                if (count == 0) {
                    matriksX[i][j] = Math.pow(0, n - 1) * Math.pow(0, m);
                } else if (count == 1) {
                    matriksX[i][j] = Math.pow(1, n - 1) * Math.pow(0, m);

                } else if (count == 2) {
                    matriksX[i][j] = Math.pow(0, n - 1) * Math.pow(1, m);

                } else if (count == 3) {
                    matriksX[i][j] = Math.pow(1, n - 1) * Math.pow(1, m);
                }
                j++;
            }
        }
    }

    public static void fungsiFY(double[][] matriksX, int i, int j, int count) {
        // f(x,y)
        int m, n;
        for (m = 0; m < 4; m++) {
            for (n = 0; n < 4; n++) {
                if (count == 0) {
                    matriksX[i][j] = Math.pow(0, n) * Math.pow(0, m - 1);
                } else if (count == 1) {
                    matriksX[i][j] = Math.pow(1, n) * Math.pow(0, m - 1);

                } else if (count == 2) {
                    matriksX[i][j] = Math.pow(0, n) * Math.pow(1, m - 1);

                } else if (count == 3) {
                    matriksX[i][j] = Math.pow(1, n) * Math.pow(1, m - 1);
                }
                j++;
            }
        }
    }

    public static void fungsiFXY(double[][] matriksX, int i, int j, int count) {
        // f(x,y)
        int m, n;
        for (m = 0; m < 4; m++) {
            for (n = 0; n < 4; n++) {
                if (count == 0) {
                    matriksX[i][j] = Math.pow(0, n - 1) * Math.pow(0, m - 1);
                } else if (count == 1) {
                    matriksX[i][j] = Math.pow(1, n - 1) * Math.pow(0, m - 1);

                } else if (count == 2) {
                    matriksX[i][j] = Math.pow(0, n - 1) * Math.pow(1, m - 1);

                } else if (count == 3) {
                    matriksX[i][j] = Math.pow(1, n - 1) * Math.pow(1, m - 1);
                }
                j++;
            }
        }
    }

    public static void bicubicSpline(double[][] matrix) {
        MatrixInput.choose();
        if (MatrixInput.choose() == 1) {
            System.out.println("Masukkan nilai f(x,y), f_x(x,y), f_y(x,y), dan f_xy(x,y) dalam matrix 4x4");
            matrix = MatrixInput.matrix_user();
            System.out.println("Masukkan nilai a dan b dalam matrix 1x2");
            matrix = MatrixInput.matrix_user();
        } else {
            System.out.println("Masukkan nilai f(x,y), f_x(x,y), f_y(x,y), dan f_xy(x,y) dalam matrix 4x4");
            matrix = MatrixInput.matrix_file();
        }

        int i = 0;
        double[][] matriksX = new double[16][16];

        // Bicubic Spline Interpolation
        // f(x,y) = a_ij x^i y^j
        // f_x(x,y) = a_ij i x^(i-1) y^j
        // f_y(x,y) = a_ij x^i j y^(j-1)
        // f_xy(x,y) = a_ij i x^(i-1) j y^(j-1)

        // input elemen matriksX dari rumus bicubic spline f(0, 0), f(1, 0), f(0, 1),
        // f(1, 1), fx(0, 0), fx(1, 0), fx(0, 1), fx(1, 1), fy(0, 0), fy(1, 0), fy(0,
        // 1), fy(1, 1), fxy(0, 0), fxy(1, 0), fxy(0, 1), fxy(1, 1) berturut-turut
        int count = 0;
        while (i < 16) {
            int j = 0;
            // f(x,y) [0..3]
            if (i < 4) {
                fungsiF(matriksX, i, j, count);
            }

            // fx(x,y) [4..7]
            else if (i < 8) {
                fungsiFX(matriksX, i, j, count);
            }

            // fy(x,y) [8..11]
            else if (i < 12) {
                fungsiFY(matriksX, i, j, count);
            }

            // fxy(x,y) [12..15]
            else if (i < 16) {
                fungsiFXY(matriksX, i, j, count);
            }

            // Masukkan stress code
            count++;
            i++;

            if (count == 4) {
                count = 0;
            }
        }
        MatrixOP.displayMatrix(matriksX);
    }
}

/*
 * // if x = 0 and y = 0
 * matriksX[i][j] = Math.pow(0, n) * Math.pow(0, m);
 * 
 * // if x = 1 and y = 0
 * matriksX[i][j] = Math.pow(1, n) * Math.pow(0, m);
 * 
 * // if x = 0 and y = 1
 * matriksX[i][j] = Math.pow(0, n) * Math.pow(1, m);
 * 
 * // if x = 1 and y = 1
 * matriksX[i][j] = Math.pow(1, n) * Math.pow(1, m);
 * 
 * // f_x(x,y) [4..7]
 * // if x = 0 and y = 0
 * matriksX[i][j] = Math.pow(0, n - 1) * Math.pow(0, m);
 * 
 * // if x = 1 and y = 0
 * matriksX[i][j] = Math.pow(1, n - 1) * Math.pow(0, m);
 * 
 * // if x = 0 and y = 1
 * matriksX[i][j] = Math.pow(0, n - 1) * Math.pow(1, m);
 * 
 * // if x = 1 and y = 1
 * matriksX[i][j] = Math.pow(1, n - 1) * Math.pow(1, m);
 * 
 * // f_y(x,y) [8..11]
 * // if x = 0 and y = 0
 * matriksX[i][j] = Math.pow(0, n) * Math.pow(0, m - 1);
 * 
 * // if x = 1 and y = 0
 * matriksX[i][j] = Math.pow(1, n) * Math.pow(0, m - 1);
 * 
 * // if x = 0 and y = 1
 * matriksX[i][j] = Math.pow(0, n) * Math.pow(1, m - 1);
 * 
 * // if x = 1 and y = 1
 * matriksX[i][j] = Math.pow(1, n) * Math.pow(1, m - 1);
 * 
 * // f_xy(x,y) [12..15]
 * // if x = 0 and y = 0
 * matriksX[i][j] = Math.pow(0, n - 1) * Math.pow(0, m - 1);
 * 
 * // if x = 1 and y = 0
 * matriksX[i][j] = Math.pow(1, n - 1) * Math.pow(0, m - 1);
 * 
 * // if x = 0 and y = 1
 * matriksX[i][j] = Math.pow(0, n - 1) * Math.pow(1, m - 1);
 * 
 * // if x = 1 and y = 1
 * matriksX[i][j] = Math.pow(1, n - 1) * Math.pow(1, m - 1);
 */