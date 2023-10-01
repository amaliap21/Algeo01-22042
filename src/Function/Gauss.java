package Function;

import ADT_Matrix.*;

import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class Gauss {
    public static double[][] forwardOBE(double[][] m) {
        // OBE matriks gauss
        int row = MatrixOP.getRowEff(m);
        int col = MatrixOP.getColEff(m);

        int i = 0;
        int j = 0;

        while (i < row && j < col) {
            // mencari pivot
            int k = i;
            while (k < row && m[k][j] == 0) {
                k++;
            }

            if (k < row) {
                // swap baris
                MatrixOP.swapRow(m, i, k);

                // membuat pivot menjadi 1
                double pivot = m[i][j];
                for (int l = j; l < col; l++) {
                    m[i][l] /= pivot;
                }

                // membuat kolom menjadi 0
                for (int l = j+1; l < row; l++) {
                    if (l != i) {
                        double factor = m[l][j];
                        for (int n = j; n < col; n++) {
                            m[l][n] -= factor * m[i][n];
                        }
                    }
                }
                i++;

            }
            j++;
        }
        return m;
    }

    public static void matriksGauss(double[][] m) {
        System.out.println("Matriks Gauss:");
        MatrixOutput.printMatrix(forwardOBE(m));
    }

    public static String[] parametrikGauss(double[][] m){
        double[][] copyM = MatrixOP.copyMatrix(m);

        char[] parameter = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        double[] arrayhasil = new double[MatrixOP.getColEff(m)-1];
        String[] arrayfinal = new String[MatrixOP.getColEff(m)-1];

        int nRow = MatrixOP.getRowEff(copyM);
        int nCol = MatrixOP.getColEff(copyM);

        for(int a=0; a<nRow; a++){
            arrayfinal[a] = "";
        }

        for(int a=0; a<nRow; a++){
            arrayhasil[a] = 0;
        }

        for(int j=nCol-1; j>=0; j--){
            int i = nRow-1;
            if((j==nCol-1)){
                arrayhasil[i] = MatrixOP.getElmt(copyM, i, j);
                i--;
            } else {
                for(int k=j-1; k>=0; k++){
                    copyM[i][j] = copyM[i][j] - (copyM[i][k]*arrayhasil[k]);
                }
                arrayhasil[i] = copyM[i][j];
                i--;

                for(int a = 0; a <= nRow; a++){
                    for (int b=0; b< nCol; b++){
                        if (arrayhasil[b] != 0){
                            m[a][b] = 0;
                        } 
                    }  
                }

                //String ant = String.valueOf(arrayhasil[i]);

                for(int c =0; c<=nRow; c++){
                    for(int d=0; d<nCol; d++){

                        if(arrayhasil[d] == 0){
                            if(m[c][d]>=0) {
                                arrayfinal[d] += "+"+String.valueOf(m[c][d])+String.valueOf(parameter[d]);
                            } else {
                                arrayfinal[d] += String.valueOf(m[c][d])+String.valueOf(parameter[d]);
                            }
                            
                        } else {
                            if (arrayhasil[d]>=0){
                                arrayfinal[d] += "+"+String.valueOf(arrayhasil[d]);
                            } else {
                                arrayfinal[d] += String.valueOf(arrayhasil[d]);
                            }
                        }
                        
                    }
                    
                }
            }    
        }
        return arrayfinal;
    }


    public static void solGauss(double[][] matriks){
        double[] arrayhasil = new double[MatrixOP.getColEff(matriks)-1];

        int nRow = MatrixOP.getRowEff(matriks);
        int nCol = MatrixOP.getColEff(matriks);

        for(int a=0; a<nRow; a++){
            arrayhasil[a] = 0;
        }

        for(int j=nCol-1; j>=0; j--){
            int i = nRow-1;
            if((j==nCol-1)){
                arrayhasil[i] = MatrixOP.getElmt(matriks, i, j);
                i--;
            } else {
                for(int k=j-1; k>=0; k++){
                    matriks[i][j] = matriks[i][j] - (matriks[i][k]*arrayhasil[k]);
                }
                arrayhasil[i] = matriks[i][j];
                i--;
            }    
        }

        for (int i = 0; i<nRow; i++) {
            System.out.print("x" + (i + 1) + " = ");
            DecimalFormat df = new DecimalFormat("0.000");                
            System.out.println(df.format(arrayhasil[i]));
        }

    }

    // public static void xsolGauss(double[][] m) {
    //     double[][] matrix = forwardOBE(m);
    //     int rowM = MatrixOP.getRowEff(matrix);
    //     int colM = MatrixOP.getColEff(matrix);

    //     System.out.println("Solusi SPL:");
    //     if (MatrixOP.solTidakAda(matrix)) {
    //         System.out.println("Tidak ada");
    //     } else if (MatrixOP.solBanyak(matrix)) {
    //         System.out.println("Banyak");
    //     } else {
    //         // mencari solusi SPL tunggal
    //     }
    // }

    // public static double[][] mxSolGauss(double[][] m) {
    // double[][] matrix = forwardOBE(m);
    // int rowM = MatrixOP.getRowEff(matrix);
    // int colM = MatrixOP.getColEff(matrix);

    // double[][] sol = new double[rowM][1];
    // // System.out.println("Solusi SPL:");
    // for (int i = 0; i < rowM; i++) {
    // DecimalFormat df = new DecimalFormat("0.000");
    // sol[i][0] = (matrix[i][colM - 1]);
    // }
    // return sol;
    // }

}