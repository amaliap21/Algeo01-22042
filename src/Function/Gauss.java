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
                for (int l = i+1; l < row; l++) {
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

    public static String strparametriksolution(double[][] m){
        String[] a = parametrikGauss(m);
        String hasil = ""; 
        for (int i = 0; i<MatrixOP.getColEff(m)-1; i++) {
            hasil += "x" + (i + 1) + " = " + a[i] + "\n";
        }
        return hasil;
    }

    public static String uniqueSolGauss(double[][] matriks){
        double[][] m = Gauss.forwardOBE(matriks);
        double[] arrayhasil = new double[MatrixOP.getColEff(m)-1];
        String hasil = "";

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

        DecimalFormat df = new DecimalFormat("0.000");  
        System.out.println("Matriks Gauss:");
        for (int i = 0; i<nRow; i++) {
            hasil += "x" + (i + 1) + " = " + df.format(arrayhasil[i])+"\n";
        }
        return hasil;
    }

    public static void displayuniqueSolGauss(double[][] matriks){
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

        DecimalFormat df = new DecimalFormat("0.000");  
        System.out.println("Matriks Gauss:");
        for (int i = 0; i<nRow; i++) {
          System.out.println("x" + (i + 1) + " = " + df.format(arrayhasil[i]));
        }
    }

    public static String arrayResultUniqueSol(double[][] m){
        double[][] matriks = Gauss.forwardOBE(m);
        double[] arrayhasil = new double[MatrixOP.getColEff(m)-1];
        String strarrayhasil = "";//new String[MatrixOP.getColEff(m)-1];
        DecimalFormat df = new DecimalFormat("0.000");

        int nRow = MatrixOP.getRowEff(matriks);
        int nCol = MatrixOP.getColEff(matriks);

        for(int a=0; a<nRow; a++){
            arrayhasil[a] = 0;
        }

        for(int i=nRow-1; i>=0; i--){
            int j=nCol-1;
            if((i==nRow-1)){
                arrayhasil[i] = MatrixOP.getElmt(matriks, i, j);
            } else {
                for(int k=j-1; k>=0; k--){
                    m[i][j] = m[i][j] - (m[i][k]*arrayhasil[k]);
                }
            arrayhasil[i] = m[i][j];
            }    
        }

        for(int i=0; i<MatrixOP.getColEff(m)-1; i++){
            strarrayhasil += "x"+ i + df.format(arrayhasil[i]);
        }

        return strarrayhasil;
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

    public static void fileOfResult(double[][] m){
        double[][] OBEdmatrix = forwardOBE(m);

        if (MatrixOP.solTidakAda(OBEdmatrix)) {
            String[] result = new String[1];
            result[0] = "Tidak ada solusi";
            MatrixOutput.SPLtoFile(result);
        } else if (MatrixOP.solBanyak(OBEdmatrix)) {
            String[] result = parametrikGauss(m);
            MatrixOutput.SPLtoFile(result);
        } else{
            // MatrixOutput.SPLtoFile(arrayResultUniqueSol(m));
            // mencari solusi SPL tunggal
        }
    }
}