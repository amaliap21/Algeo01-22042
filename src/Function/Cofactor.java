package Function;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import ADT_Matrix.*;

public class Cofactor {
    public static double determinanCof(double[][] m) {
        double det;
        int tanda, j;
        int row = MatrixOP.getRowEff(m);
        int col = MatrixOP.getColEff(m);

        det = 0;
        tanda = 1;
        if (row == 1) {
            det = m[0][0];
        } else if (row == 2) {
            det = m[0][0] * m[1][1] - m[0][1] * m[1][0];
        } else if (row > 2) {
            for (j = 0; j < col; j++) {
                det += tanda * m[0][j] * determinanCof(MatrixOP.submatrix(m, 0, j));
                tanda = -tanda;
            }
        }
        return det;
    }

    public static double[][] getCofactor(double[][] m, int row, int col) {
        double[][] newM;
        int i, j;
        newM = MatrixOP.copyMatrix(m);

        double[][] sub = new double[MatrixOP.getRowEff(newM) - 1][MatrixOP.getColEff(newM) - 1];
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

        // if (MatrixOP.isSquare(m)) {
            cof = new double[MatrixOP.getRowEff(m)][MatrixOP.getColEff(m)];
            for (i = 0; i < MatrixOP.getRowEff(m); i++) {
                for (j = 0; j < MatrixOP.getColEff(m); j++) {
                    detCof = determinanCof(getCofactor(m, i, j));
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
            return cof;
        } 
        // else {
        //     cof = new double[MatrixOP.getRowEff(m)][MatrixOP.getRowEff(m)];
        //     for (i = 0; i < MatrixOP.getRowEff(m); i++) {
        //         for (j = 0; j < MatrixOP.getRowEff(m); j++) {
        //             detCof = MatrixOP.determinant(getCofactor(m, i, j));
        //             if (i % 2 == 0) {
        //                 if (j % 2 != 0) {
        //                     detCof *= (-1);
        //                 }
        //             } else {
        //                 if (j % 2 == 0) {
        //                     detCof *= (-1);
        //                 }
        //             }
        //             if (detCof == -0.0) {
        //                 detCof = 0.0;
        //             }
        //             MatrixOP.setElmt(cof, i, j, detCof);
        //         }
        //     }
        // }

    // }

    public static double[][] adj(double[][] m) {
        return MatrixOP.transpose(createMatrixCofactor(m));
    }

    public static void detByCofactor(double[][] cof, double[][] m, int n) {
        if (MatrixOP.isSquare(m) == false){
            System.out.println("Matriks ini tidak memiliki determinan karena bukan berukuran nxn");
        }
        else{
            DecimalFormat df = new DecimalFormat("0.000");
            System.out.println("Index merupakan baris/kolom?");
            System.out.println("Jika baris ketik '1'");
            System.out.println("Jika kolom ketik '2'");
            Scanner jenis = new Scanner(System.in);
            int choose = jenis.nextInt();
            double det = 0;
            int i, j;
            if(choose == 1){
                for(j = 0; j < MatrixOP.getRowEff(m); j++){
                    det = det + cof[n][j]*m[n][j];
                }
            } 
            else if (choose == 2) {
                for (i = 0; i < MatrixOP.getRowEff(m); i++) {
                    det = det + cof[i][n] * m[i][n];
                }
            } 
            else {
                System.out.println("Input salah, silakan ulangi.");
            }
            System.out.println("Determinan matriks ini adalah " + df.format(det));
        }
    }

    public static double valDetCofactor(double[][] cof, double[][] m, int n) {
        double det = 0;
        for(int j = 0; j < MatrixOP.getRowEff(m); j++){
            det = det + cof[n][j]*m[n][j];
        } 
        return det;
    }
}