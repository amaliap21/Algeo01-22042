package Function;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import ADT_Matrix.*;

public class Cramer {
    public static double[][] replace(double [][] m, int n){
        double[][] newM = MatrixOP.copyMatrix(m);
        double[][] square = new double[MatrixOP.getRowEff(newM)][MatrixOP.getRowEff(newM)];
        int i, j;
        
        for(i = 0; i < MatrixOP.getRowEff(newM); i++){
            newM[i][n] = m[i][MatrixOP.getLastIdxCol(newM)];
            // for(j = 0; j < MatrixOP.getRowEff(newM); j++){
            // }
        }
        for(i = 0; i < MatrixOP.getRowEff(newM); i++){
            for(j = 0; j < MatrixOP.getRowEff(newM); j++){
                square[i][j] = newM[i][j];
            }
        }
        return square;
    }

    public static void SPLCramer(double[][] m){
        // System.out.println("Input n (n adalah indeks kolom yang ingin ditukar)");
        // Scanner input = new Scanner(System.in);
        // int n = input.nextInt();
        double det_pure, det_replace = 0;
        int i, j;
        double[][] newM = new double[MatrixOP.getRowEff(m)][MatrixOP.getRowEff(m)];
        for(i = 0; i < MatrixOP.getRowEff(m); i++){
            for(j = 0; j < MatrixOP.getRowEff(m); j++){
                newM[i][j] = m[i][j];
            }
        }
        
        double[][] sol = new double[MatrixOP.getRowEff(newM)][1];
        det_pure = MatrixOP.determinant(newM);
        for(j = 0; j < MatrixOP.getColEff(newM); j++){
            det_replace = MatrixOP.determinant(replace(m, j));
            if(det_pure == 0.0 && det_replace == 0.0){
                MatrixOP.setElmt(sol, j, 0, 0.0);
            }
            else{
                MatrixOP.setElmt(sol, j, 0, det_replace/det_pure);
            }
        }
        System.out.println("Solusi SPL:");
        if(MatrixOP.getColEff(m) != MatrixOP.getRowEff(m) + 1){
            System.out.println("Solusi tidak dapat dicari menggunakan metode Cramer karena ukuran matriks A tidak n x n.");
        }
        else if (det_pure == 0 && det_replace != 0){
            System.out.println("Solusi tidak dapat dicari menggunakan metode Cramer karena determinan matriks A adalah 0.");
        }
        else if (MatrixOP.solTidakAda(m)){
            System.out.println("Solusi tidak ada.");
        }
        else if (MatrixOP.solBanyak(m)){
            System.out.println("Solusi banyak.");
        }
        else{
            for(j = 0; j < MatrixOP.getRowEff(m); j++){
                System.out.print("x" + (j+1) +" = ");
                DecimalFormat df = new DecimalFormat("0.000");
                System.out.println(df.format(sol[j][0]));
            }
        }
    }
    
    public static double[][] uniqueCramer(double[][] m){
        double det_pure, det_replace = 0;
        int i, j;
        double[][] newM = new double[MatrixOP.getRowEff(m)][MatrixOP.getRowEff(m)];
        for(i = 0; i < MatrixOP.getRowEff(m); i++){
            for(j = 0; j < MatrixOP.getRowEff(m); j++){
                newM[i][j] = m[i][j];
            }
        }
        
        double[][] sol = new double[MatrixOP.getRowEff(newM)][1];
        det_pure = MatrixOP.determinant(newM);
        for(j = 0; j < MatrixOP.getColEff(newM); j++){
            det_replace = MatrixOP.determinant(replace(m, j));
            if(det_pure == 0.0 && det_replace == 0.0){
                MatrixOP.setElmt(sol, j, 0, 0.0);
            }
            else{
                MatrixOP.setElmt(sol, j, 0, det_replace/det_pure);
            }
        }
        return sol;
    }

    // public static void solCramer(double[][] m){
    //     System.out.println("Solusi SPL:");
    //     if(MatrixOP.getColEff(m) != MatrixOP.getRowEff(m) + 1){
    //         System.out.println("Solusi tidak ada.");
    //     }
    //     else if (MatrixOP.solTidakAda(m)){
    //         System.out.println("Solusi tidak ada.");
    //     }
    //     else if (MatrixOP.solBanyak(m)){
    //         System.out.println("Solusi banyak.");
    //     }
    //     else{
    //         for(int j = 0; j < MatrixOP.getRowEff(m); j++){
    //             System.out.print("x" + (j+1) +" = ");
    //             DecimalFormat df = new DecimalFormat("0.000");
    //             System.out.println(df.format(SPLCramer(m)[j][0]));
    //         }
    //     }
    // }

    public static String[] arrayResultUniqueSol(double[][] m){
        String[] result = new String[MatrixOP.getColEff(m)-1];
        DecimalFormat df = new DecimalFormat("0.000");

        for(int i=0; i<MatrixOP.getColEff(m)-1; i++){
            result[i] = String.valueOf(df.format(uniqueCramer(m)[i][0]));
        }
        return result;
    }

    public static void fileOfResult(double[][] m){
        MatrixOutput.SPLtoFile(arrayResultUniqueSol(m));
    }
}
