package Function;
import ADT_Matrix.*;

import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class Gauss{
    // public static double[][] forwardOBE(double[][] m){
    //     double[][] matrix = MatrixOP.copyMatrix(m);

    //     int rowM = MatrixOP.getRowEff(matrix);
    //     int colM = MatrixOP.getColEff(matrix);

    //     for (int i = 0; i < rowM; i++) {
    //         // Nyari indeks dengan elemen terbesar pada suatu kolom
    //         // Tujuan: buat swapping baris
    //         int max = i;
    //         for (int j = i + 1; j < rowM; j++) {
    //             if (Math.abs(matrix[j][i]) > Math.abs(matrix[max][i])) {
    //                 max = j;
    //             }
    //         }

    //         // Swapping baris
    //         double[] temp = matrix[i];
    //         matrix[i] = matrix[max];
    //         matrix[max] = temp;

    //         // Buat elemennya jadi 1
    //         double divider = matrix[i][i];
    //         for (int j = i; j < colM; j++) {
    //             matrix[i][j] /= divider;
    //         }

    //         for (int j = 0; j<rowM; j++){
    //             if (j != i){

    //             }
    //         }
    //     }
    // }
    public static double[][] swapRow(double[][] m, int row1, int row2){
        int j;
        double[][] newM = MatrixOP.copyMatrix(m);
        for(j = 0; j < MatrixOP.getColEff(newM); j++){
            newM[row1][j] = m[row2][j];
            newM[row2][j] = m[row1][j];
        }
        return newM;
    }

    public static double getMaxfromCol(double[][] m, int row, int col){
        int i;

        double max;
        max = Math.abs(MatrixOP.getElmt(m, row, col));

        for(i = row; i<MatrixOP.getRowEff(m); i++){
            if(Math.abs(MatrixOP.getElmt(m, i, col))>=max){
                max = MatrixOP.getElmt(m, i, col);
            }
        }
        return max;
    }

    // *** MULAI OPERASI BARIS ELEMENTER ***
    public static double[][] forwardOBE(double[][] m) {
        double[][] matrix = MatrixOP.copyMatrix(m);
        // *** SWAPPING ***
        for(int j=0; j<MatrixOP.getRowEff(matrix); j++){
            int i=j;
            int rowMax = i;
            
            //Cari elemen yang bernilai maksimum dari 
            double max = getMaxfromCol(matrix, i, j);

            //Cari index tempat elemen maksimum berada
            rowMax = MatrixOP.getIdxRowEl(matrix, j, max);
            if(i!=rowMax){
                matrix = swapRow(matrix, i, rowMax);
            }
        }

        if(matrix[0][0]!=0){
            double ratio1 = matrix[0][0];
            for (int i=0; i<MatrixOP.getColEff(matrix); i++){
                matrix[0][i] = matrix[0][i]/ratio1;
            }
        } // Row 1 sudah dalam bentuk leading one

        //Melakukan proses pengurangan/reduksi baris
        for(int a=1; a<MatrixOP.getRowEff(matrix); a++){   
            for(int i=a; i<MatrixOP.getRowEff(matrix); i++){
                if (matrix[i][a-1] != 0){
                    double ratio = matrix[i][a-1]/matrix[a-1][a-1]; 
                    for(int j=0; j<MatrixOP.getColEff(matrix); j++){
                        matrix[i][j]  = MatrixOP.getElmt(matrix, i, j) - (ratio*(MatrixOP.getElmt(matrix, a-1, j)));
                    }
                } 
            }

            //SWAPPING ELEMEN 0 YANG ADA DI DIAGONAL DENGAN ELEMEN DI BAWAHNYA
            for(int j=a; j<MatrixOP.getRowEff(matrix); j++){
                int i=j;
                int rowMax = i;

                double max = getMaxfromCol(matrix, i, j);
                rowMax = MatrixOP.getIdxRowEl(matrix, j, max);

                matrix = swapRow(matrix, i, rowMax);
            }
        }

        //PEMBENTUKAN LEADING ONE
        for(int i=0; i<MatrixOP.getRowEff(matrix); i++){
            if (MatrixOP.foundColNotZero(matrix, i)){
                for(int j=MatrixOP.getIdxColElNotZero(matrix, i); j<MatrixOP.getColEff(matrix); j++){
                    double divider = MatrixOP.getElmt(matrix, i, MatrixOP.getIdxColElNotZero(matrix, i));
                    matrix[i][j] = MatrixOP.getElmt(matrix, i, j)/divider;
                }
            }
        }
        return matrix;
    }

    // public static void matriksGauss(double[][] m){
    //     System.out.println("Matriks Gauss:");
    //     MatrixOutput.printMatrix(forwardOBE(m));
    // }

    // public static void solGauss(double[][] m) {
    //     double[][] matrix = Gauss.forwardOBE(m);
    //     int rowM = MatrixOP.getRowEff(matrix);
    //     int colM = MatrixOP.getColEff(matrix);

    //     System.out.println("Solusi SPL:");
    //     for (int i = 0; i < rowM; i++) {
    //         System.out.print("x" + (i + 1) + " = ");
    //         DecimalFormat df = new DecimalFormat("0.000");
    //         System.out.println(df.format(matrix[i][colM - 1]));
    //     }
    // }
}

