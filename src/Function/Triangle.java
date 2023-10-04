package Function;
import java.io.*;
import java.util.*;
import ADT_Matrix.*;

public class Triangle {
    public static double detUpperTriangular(double[][] m) {
        double det = 1;
        int i, j;
        int count = 0;
        
        if(!MatrixOP.isSquare(m)){
            int row = MatrixOP.getRowEff(m);
            int col = row;
            double[][] m1 = new double[row][row];
            for(i = 0; i < row; i++){
                for(j = 0; j < col; j ++){
                    m1[i][j] = m[i][j];
                }
            }
            // MatrixOutput.printMatrix(m1);
            // System.out.println(MatrixOP.getColEff(m1));

            for (i = 0; i < row; i++) {
                int max = i;
                
                for (j = i + 1; j < row; j++) {
                    if (Math.abs(m1[j][i]) > Math.abs(m1[max][i])) {
                        max = j;
                    }
                    if(max != i){
                        count ++;
                    }
                }

                // Swapping baris
                double[] temp = m1[i];
                m1[i] = m1[max];
                m1[max] = temp;

                // Ngurangin baris lain dengan baris yang sudah dikurangi
                for (j = i + 1; j < row; j++) {
                    double multiplier = m1[j][i]/m1[j-1][i];
                    for (int k = i; k < row; k++) {
                        m1[j][k] -= multiplier * m1[i][k];
                        if (m1[j][k] == -0) {
                            m1[j][k] = 0;
                        }
                    }
                }
            } 
            
            MatrixOutput.printMatrix(m1);
            double[][] newM = MatrixOP.copyMatrix(m1);
            for(i = 0; i < row; i++){
                for(j = 0; j < row; j++){
                    if(i == j){
                        det *= newM[i][j]*Math.pow(-1,count);
                        // System.out.println(det);
                    }
                }
            }
        }

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

                // System.out.println("count = "+ count);
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
                // MatrixOutput.printMatrix(matrix);
            }
            // MatrixOutput.printMatrix(matrix);
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
        }
        if(det>0 && det<Math.pow(10, -3)){
            det = 0;
        } 
        if(det<0 && det>Math.pow(-10, -3)){
            det = 0;
        }
        return det;
    }

    public static double detLowerTriangular(double[][] m){
        double det = 1;
        int count = 0;
        double multDiv = 1;
        
        if(!MatrixOP.isSquare(m)){
            int i, j;
            int row = MatrixOP.getRowEff(m);
            int col = row;
            double[][] m1 = new double[row][row];
            for(i = 0; i < row; i++){
                for(j = 0; j < col; j ++){
                    m1[i][j] = m[i][j];
                }
            }
            MatrixOutput.printMatrix(m1);
            System.out.println(MatrixOP.getColEff(m1));

            int rowm = MatrixOP.getRowEff(m);
            int colm = MatrixOP.getColEff(m);

            int a = rowm-1;
            int b = colm-1;

            while (a >=0 && b >= 0) {
                // mencari pivot
                int k = a;
                while (k > row && m[k][b] == 0) {
                    k--;
                }

                if(k!=a){
                    count+=1;
                }

                if (k >= 0) {
                    // swap baris
                    MatrixOP.swapRow(m, a, k);

                    // membuat pivot menjadi 1
                        double pivot = m[a][b];
                        for (int l = b; l >= 0; l--) {
                            if(pivot==0){
                                pivot = 1;
                            } else {
                                m[i][l] /= pivot;
                            }
                        }
                        multDiv *= pivot;

                    // membuat kolom menjadi 0
                    for (int l = a-1; l >= 0; l--) {
                        if (l != a) {
                            double factor = m[l][b];
                            for (int n = b; n >= 0; n--) {
                                m[l][n] -= factor * m[a][n];
                            }
                        }
                    }
                    a--;
                }
                b--;
            }
        } else {
                int row = MatrixOP.getRowEff(m);
                int col = MatrixOP.getColEff(m);

                int a = row-1;
                int b = col-1;

                while (a >=0 && b >= 0) {
                    // mencari pivot
                    int k = a;
                    while (k >=0 && m[k][b] == 0) {
                        k--;
                    }

                    if(k!=a){
                        count+=1;
                    }

                    if (k >= 0) {
                        // swap baris
                        MatrixOP.swapRow(m, a, k);

                        // membuat pivot menjadi 1
                        double pivot = m[a][b];
                        for (int l = b; l >= 0; l--) {
                            if(pivot==0){
                                pivot = 1;
                            } else {
                                m[a][l] /= pivot;
                            }
                        }
                        multDiv *= pivot;

                        // membuat kolom menjadi 0
                        for (int l = a-1; l >= 0; l--) {
                            if (l != a) {
                                double factor = m[l][b];
                                for (int n = b; n >= 0; n--) {
                                    m[l][n] -= factor * m[a][n];
                                }
                            }
                        }
                        a--;
                    }
                    b--;
                }
            }

            MatrixOutput.printMatrix(m);
            double[][] newM = MatrixOP.copyMatrix(m);
            for(int i = 0; i < MatrixOP.getRowEff(newM); i++){
                det*=MatrixOP.getElmtDiagonal(newM, i);
                // for(j = 0; j < colM; j++){
                //     if(i == j){
                //         det *= newM[i][j]*Math.pow(-1,count);
                //     }
                // }
            } 
            det = det*Math.pow(-1, count)*multDiv;

            if(det>0 && det<Math.pow(10, -3)){
            det = 0;
            } 
            if(det<0 && det>Math.pow(-10, -3)){
                det = 0;
            }
            return det;
        }
}