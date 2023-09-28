package Function;
import java.io.*;
import java.util.*;
import ADT_Matrix.*;

public class Triangle {
    public static void detUpperTriangular(double[][] m) {
        double det = 1;
        int i, j;
        int count = 0;
        
        if(!MatrixOP.isSquare(m)){
            System.out.println("Matriks ini tidak memiliki determinan karena bukan berukuran nxn");
        }
        //     int row = MatrixOP.getRowEff(m);
        //     int col = row;
        //     double[][] m1 = new double[row][row];
        //     for(i = 0; i < row; i++){
        //         for(j = 0; j < col; j ++){
        //             m1[i][j] = m[i][j];
        //         }
        //     }
        //     MatrixOutput.printMatrix(m1);
        //     System.out.println(MatrixOP.getColEff(m1));

        //     for (i = 0; i < row; i++) {
        //         int max = i;
                
        //         for (j = i + 1; j < row; j++) {
        //             if (Math.abs(m1[j][i]) > Math.abs(m1[max][i])) {
        //                 max = j;
        //             }
        //             if(max != i){
        //                 count ++;
        //             }
        //         }

        //         // Swapping baris
        //         double[] temp = m1[i];
        //         m1[i] = m1[max];
        //         m1[max] = temp;

        //         // Ngurangin baris lain dengan baris yang sudah dikurangi
        //         for (j = i + 1; j < row; j++) {
        //             double multiplier = m1[j][i]/m1[j-1][i];
        //             for (int k = i; k < row; k++) {
        //                 m1[j][k] -= multiplier * m1[i][k];
        //                 if (m1[j][k] == -0) {
        //                     m1[j][k] = 0;
        //                 }
        //             }
        //         }
        //     } 
            
        //     MatrixOutput.printMatrix(m1);
        //     double[][] newM = MatrixOP.copyMatrix(m1);
        //     for(i = 0; i < row; i++){
        //         for(j = 0; j < row; j++){
        //             if(i == j){
        //                 det *= newM[i][j]*Math.pow(-1,count);
        //                 System.out.println(det);
        //             }
        //         }
        //     }
        // }

        else{
            double[][] matrix = MatrixOP.copyMatrix(m);
            int rowM = MatrixOP.getRowEff(matrix);
            int colM = MatrixOP.getColEff(matrix);
            for (i = 0; i < rowM; i++) {
                int max = i;
                for (j = i + 1; j < rowM; j++) {
                    if (Math.abs(matrix[j][i]) > Math.abs(matrix[max][i])) {
                        max = j;
                    }
                }
                if(max != i){
                    count ++;
                }

                System.out.println("count = "+ count);
                // Swapping baris
                double[] temp = matrix[i];
                matrix[i] = matrix[max];
                matrix[max] = temp;

                // Ngurangin baris lain dengan baris yang sudah dikurangi
                for (j = i + 1; j < rowM; j++) {
                    double multiplier = matrix[j][i]/matrix[i][i];
                    for (int k = i; k < colM; k++) {
                        matrix[j][k] -= multiplier * matrix[i][k];
                        if (matrix[j][k] == -0) {
                            matrix[j][k] = 0;
                        }
                    }
                }
                MatrixOutput.printMatrix(matrix);
            }
            MatrixOutput.printMatrix(matrix);
            double[][] newM = MatrixOP.copyMatrix(matrix);
            for(i = 0; i < rowM; i++){
                det*=MatrixOP.getElmtDiagonal(newM, i);
                // for(j = 0; j < colM; j++){
                //     if(i == j){
                //         det *= newM[i][j]*Math.pow(-1,count);
                //     }
                // }
            } 
            det = det*Math.pow(-1, count);
            if(det>0 && det<Math.pow(10, -3)){
                det = 0;
            } 
            if(det<0 && det>Math.pow(-10, -3)){
                det = 0;
            }
            System.out.println("Determinannya adalah " + det);
        }
    }
}