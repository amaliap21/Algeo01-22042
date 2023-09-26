package Function;
import ADT_Matrix.*;

import java.util.*;
import java.io.*;

public class Gauss{
    public static double[][] swapRow(double[][] m, int row1, int row2){
        int j;
        double[][] newM = MatrixOP.copyMatrix(m);
        for(j = 0; j < MatrixOP.getColEff(newM); j++){
            newM[row1][j] = m[row2][j];
            newM[row2][j] = m[row1][j];
        }
        return newM;
    }


// public class Gauss {
    public static double[][] gaussSPL(double[][] m) {
        int baris, kolom;

        // Scanner scanner = new Scanner(System.in);
        // System.out.println("baris = ");
        // baris = scanner.nextInt();

        // System.out.println("kolom = ");
        // kolom = scanner.nextInt();  

        double[][] matrix = MatrixOP.copyMatrix(m);

        // for(int i=0; i<MatrixOP.getRowEff(m); i++){
        //     for(int j=0; j<MatrixOP.getColEff(m); j++){
        //         System.out.print("matrix["+i+"]["+j+"] = ");
        //         matrix[i][j] = scanner.nextInt();
        //     }
        // }

        // for(int i=0; i<MatrixOP.getRowEff(m); i++){
        //     for(int j=0; j<MatrixOP.getColEff(m); j++){
        //         System.out.print(matrix[i][j]);
        //         if(j!=kolom-1){
        //             System.out.print(" ");
        //         }
        //     } System.out.print("\n");
        // }
    
    // *** MULAI OPERASI BARIS ELEMENTER ***
    int i = 0, j =0;
        while (MatrixOP.getElmt(matrix, i, j) == 0){
            int k = i+1;
            swapRow(matrix, i, k);
        }

        double ratio1 = matrix[0][0];
        for (i=0; i<matrix[0].length; i++){
            matrix[0][i] = matrix[0][i]/ratio1;
        }


    for(int a=1; a<matrix.length; a++){   
        for(i=a; i<matrix.length; i++){
            if (matrix[i][a-1] != 0){
                double ratio = matrix[i][a-1]/matrix[a-1][a-1];
                for(j=0; j<matrix[i].length; j++){
                    matrix[i][j]  = matrix[i][j]- (ratio*matrix[a-1][j]);
                }
            }
          
        }
    }

        for(i=0; i<matrix.length; i++){
            for(j=0; j<matrix[i].length; j++){
                System.out.print(matrix[i][j]);
                if(j!=MatrixOP.getColEff(m)-1){
                    System.out.print(" ");
                }
            } System.out.print("\n");
        }
        return matrix;
    }
}
