package Function;

import java.io.*;
import java.util.*;
import ADT_Matrix.*;

public class Cofactor {
    public static double[][] getCofactor(double[][] m, int row, int col) {
        double[][] sub, newM;
        int i, j;
        newM = MatrixOP.copyMatrix(m);

        if (MatrixOP.isSquare(newM)) {
            sub = new double[MatrixOP.getRowEff(newM) - 1][MatrixOP.getColEff(newM) - 1];
        } else {
            sub = new double[MatrixOP.getRowEff(newM) - 1][MatrixOP.getRowEff(newM) - 1];
        }

        for (i = 0; i < MatrixOP.getRowEff(sub); i++) {
            for (j = 0; j < MatrixOP.getColEff(sub); j++) {
                if (i < row && j < col) {
                    sub[i][j] = newM[i][j];
                } else if (i < row && j >= col) {
                    sub[i][j] = newM[i][j + 1];
                } else if (i >= row && j < col) {
                    sub[i][j] = newM[i + 1][j];
                } else if (i >= row && j >= col) {
                    sub[i][j] = newM[i + 1][j + 1];
                }
            }
        }
        return sub;
    }

    // public static double detCof(double[][] m, int row, int col){
    // double[][] cof;
    // cof = getCofactor(m, row, col);
    // return MatrixOP.determinant(cof);
    // }

    public static double[][] createMatrixCofactor(double[][] m) {
        double[][] cof;
        int i, j;
        double detCof;

        if (MatrixOP.isSquare(m)) {
            cof = new double[MatrixOP.getRowEff(m)][MatrixOP.getColEff(m)];
            for (i = 0; i < MatrixOP.getRowEff(m); i++) {
                for (j = 0; j < MatrixOP.getColEff(m); j++) {
                    detCof = MatrixOP.determinant(getCofactor(m, i, j));
                    if (i % 2 == 0) {
                        if (j % 2 != 0) {
                            detCof *= (-1);
                        }
                    } else {
                        if (j % 2 == 0) {
                            detCof *= (-1);
                        }
                    }
                    if (detCof == -0.0) {
                        detCof = 0.0;
                    }
                    MatrixOP.setElmt(cof, i, j, detCof);
                }
            }
        } else {
            cof = new double[MatrixOP.getRowEff(m)][MatrixOP.getRowEff(m)];
            for (i = 0; i < MatrixOP.getRowEff(m); i++) {
                for (j = 0; j < MatrixOP.getRowEff(m); j++) {
                    detCof = MatrixOP.determinant(getCofactor(m, i, j));
                    if (i % 2 == 0) {
                        if (j % 2 != 0) {
                            detCof *= (-1);
                        }
                    } else {
                        if (j % 2 == 0) {
                            detCof *= (-1);
                        }
                    }
                    if (detCof == -0.0) {
                        detCof = 0.0;
                    }
                    MatrixOP.setElmt(cof, i, j, detCof);
                }
            }
        }

        return cof;
    }

    public static double[][] adj(double[][] m) {
        return MatrixOP.transpose(createMatrixCofactor(m));
    }

    public static double detByCofactor(double[][] cof, double[][] m, int n) {
        System.out.println("n merupakan baris/kolom?");
        System.out.println("Jika baris ketik '1'");
        System.out.println("Jika kolom ketik '2'");
        Scanner jenis = new Scanner(System.in);
        int choose = jenis.nextInt();
        double det = 0;
        int i, j;

        if(choose == 1){
            for(j=0; j<MatrixOP.getRowEff(m); j++){
                det += cof[n][j]*m[n][j];
            }
        } else if (choose == 2) {
            for (i = 0; i < MatrixOP.getRowEff(m); i++) {
                det += cof[i][n] * m[i][n];
            }
        } else {
            System.out.println("Input salah, silakan ulangi.");
        }
        return det;
    }
}