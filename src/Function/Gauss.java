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
                /*
                 * 08.24 30/09/2023
                 */
                MatrixOP.swapRow(m, i, k);

                // membuat pivot menjadi 1
                double pivot = m[i][j];
                for (int l = j; l < col; l++) {
                    /*
                     * 20.58 29/09/2023
                     */
                    if (pivot == 0) {
                        pivot = 1;
                    } else {
                        m[i][l] /= pivot;
                    }
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

        for(int a=0; a<nCol-1; a++){
            arrayfinal[a] = "";
        }

        for(int a=0; a<nCol-1; a++){
            arrayhasil[a] = 999;
        }

        double[][] OBEdmatrix = GaussJordan.gaussJordan(copyM);
        MatrixOutput.printMatrix(OBEdmatrix);

        int effRow = MatrixOP.getRowEff(OBEdmatrix)-MatrixOP.CountRowZero(OBEdmatrix); //jumlah row yang tidak full zero

        //STEP 1 : Looping untuk cari dulu solusi yang bisa diambil dari baris yang hanya ada leading one dan result
        for(int i=(effRow-1); i>=0; i--){
            if(MatrixOP.oneNotZeroElCol(OBEdmatrix, i)){ //hanya ada leading one dan result dalam satu baris
                int a = MatrixOP.getIdxColElNotZero(OBEdmatrix, i);
                arrayhasil[a] = OBEdmatrix[i][nCol-1];
                arrayfinal[a] = String.valueOf(OBEdmatrix[i][nCol-1]);
            }
        }
        
        for(int i= (effRow-1); i>=0; i--){
            int j = nCol-1;
            for(int k = j-1; k>=0; k--){
                if (arrayhasil[k] == 999){
                    OBEdmatrix[i][j] = OBEdmatrix[i][j] - (OBEdmatrix[i][k]*0);
                } else {
                    OBEdmatrix[i][j] = OBEdmatrix[i][j] - (OBEdmatrix[i][k]*arrayhasil[k]);
                }  
            }  
        }

        // String[] temparrayhasil = new String[MatrixOP.getColEff(OBEdmatrix)-1];

        for(int i=0; i<MatrixOP.getColEff(OBEdmatrix)-1; i++){
            if(MatrixOP.isFullZeroCol(OBEdmatrix, i)){
                arrayfinal[i] = String.valueOf(parameter[i]);
            }
        }

        for(int i = 0; i<effRow; i++){
            for(int j=0; j<nCol-1; j++){
                if(arrayhasil[j]!=999){
                    OBEdmatrix[i][j] = 0;
                }
            }
        }

        // for(int i =0; i<MatrixOP.getColEff(OBEdmatrix)-1; i++){
        //     if(arrayhasil[i]!=999){
        //         temparrayhasil[i] = String.valueOf(arrayhasil[i]);
        //     }
        // }

        for(int i=effRow-1; i>=0; i--){
            for(int j=nCol-1; j>MatrixOP.getIdxColElNotZero(OBEdmatrix, i); j--){

                if(j==nCol-1){
                    if(OBEdmatrix[i][j] == 0){
                        arrayfinal[MatrixOP.getIdxColElNotZero(OBEdmatrix, i)] = "";
                    } else {
                        arrayfinal[MatrixOP.getIdxColElNotZero(OBEdmatrix, i)] += String.valueOf(OBEdmatrix[i][j]);
                    }
                } else {
                    if(OBEdmatrix[i][j]>0){
                        if(OBEdmatrix[i][j]==1){
                            arrayfinal[MatrixOP.getIdxColElNotZero(OBEdmatrix, i)] += "-" + String.valueOf(parameter[j]);
                        } else {
                            arrayfinal[MatrixOP.getIdxColElNotZero(OBEdmatrix, i)] += "-" + String.valueOf(OBEdmatrix[i][j]) + String.valueOf(parameter[j]);
                        }
                    } else if(OBEdmatrix[i][j]<0){
                        if(arrayfinal[MatrixOP.getIdxColElNotZero(OBEdmatrix, i)]==""){
                            arrayfinal[MatrixOP.getIdxColElNotZero(OBEdmatrix, i)] += String.valueOf(Math.abs(OBEdmatrix[i][j])) + String.valueOf(parameter[j]);
                        } else {
                            if(OBEdmatrix[i][j]==-1){
                                arrayfinal[MatrixOP.getIdxColElNotZero(OBEdmatrix, i)] += "+"+String.valueOf(parameter[j]);
                            } else {
                                arrayfinal[MatrixOP.getIdxColElNotZero(OBEdmatrix, i)] += "+" + String.valueOf(Math.abs(OBEdmatrix[i][j])) + String.valueOf(parameter[j]);
                            }  
                        }
                    } else if(OBEdmatrix[i][j] == 0){
                        arrayfinal[MatrixOP.getIdxColElNotZero(OBEdmatrix, i)] += "";
                    }
                }
            }  
        }

        for(int i=0; i<MatrixOP.getColEff(OBEdmatrix)-1; i++){
            if(arrayfinal[i]==""){
                arrayfinal[i] = String.valueOf(parameter[i]);
            }
        }

        return arrayfinal;
    }

    public static void parametriksolution(double[][] m){
        String[] a = parametrikGauss(m); 
        for (int i = 0; i<MatrixOP.getColEff(m)-1; i++) {
            System.out.print("x" + (i + 1) + " = ");          
            System.out.println(a[i]);
        }
    }


    public static void uniqueSolGauss(double[][] matriks){
        double[][] m = Gauss.forwardOBE(matriks);
        double[] arrayhasil = new double[MatrixOP.getColEff(m)-1];

        int nRow = MatrixOP.getRowEff(m);
        int nCol = MatrixOP.getColEff(m);

        for(int a=0; a<nRow; a++){
            arrayhasil[a] = 0;
        }

        for(int i=nRow-1; i>=0; i--){
            int j=nCol-1;
            if((i==nRow-1)){
                arrayhasil[i] = MatrixOP.getElmt(m, i, j);
            } else {
                for(int k=j-1; k>=0; k--){
                    m[i][j] = m[i][j] - (m[i][k]*arrayhasil[k]);
                }
            arrayhasil[i] = m[i][j];
            }    
        }

        System.out.println("Matriks Gauss:");
        for (int i = 0; i<nRow; i++) {
            System.out.print("x" + (i + 1) + " = ");
            DecimalFormat df = new DecimalFormat("0.000");                
            System.out.println(df.format(arrayhasil[i]));
        }

    }

    public static void xsolGauss(double[][] m) {
        double[][] matrix = forwardOBE(m);
        int rowM = MatrixOP.getRowEff(matrix);
        int colM = MatrixOP.getColEff(matrix);

        System.out.println("Solusi SPL:");
        if (MatrixOP.solTidakAda(matrix)) {
            System.out.println("Tidak ada");
        } else if (MatrixOP.solBanyak(matrix)) {
            System.out.println("Banyak");
            parametriksolution(m);
        } else{
            uniqueSolGauss(m);
            // mencari solusi SPL tunggal
        }
    }

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
