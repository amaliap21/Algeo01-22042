package Function;

import java.io.*;
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

    public static double[][] SPLCramer(double[][] m){
        // System.out.println("Input n (n adalah indeks kolom yang ingin ditukar)");
        // Scanner input = new Scanner(System.in);
        // int n = input.nextInt();
        double det_pure, det_replace;
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
            if(det_pure == 0.0){
                if (det_replace == 0.0){
                    MatrixOP.setElmt(sol, j, 0, 0.0);
                }
                MatrixOP.setElmt(sol, j, 0, -999);
            } 
            else{
                MatrixOP.setElmt(sol, j, 0, det_replace/det_pure);
            }
        }
        return sol;
    }

    public static void solCramer(double[][] m){
        int j;
        for(j = 0; j < MatrixOP.getRowEff(m); j++){
            System.out.print("x" + (j+1) +" = ");
            System.out.println(SPLCramer(m)[j][0]);

    }

        // for(a = 0; a < getColEff(m)-1; a++){
            
        // }
        // System.out.println();
        // MatrixOutput.printMatrix(newM);
        // System.out.println();
        // MatrixOutput.printMatrix(replace(m, n));
        // System.out.println();
        // System.out.println(det_replace);
        // System.out.println(det_pure);
        
        // return det;
    }
}
