package Function;
import java.io.*;
import java.util.*;
import ADT_Matrix.*;

public class Triangle {
    public static void upperTriangular(double[][] m){
        int i = 0, j = 0, k = 0;
        int row = MatrixOP.getRowEff(m);
        int col = MatrixOP.getColEff(m);
        double[][] newM = new double[row][col];

        for(i=0; i<row; i++){
            for(j=0; j<col; j++){
                if(m[i][i] == 0){
                    newM = Gauss.swapRow(m, i, i+1);
                }
            }
        }
                MatrixOutput.printMatrix(newM);
                System.out.println();
            // }
        //     j++;
        // }
        MatrixOutput.printMatrix(newM);
    }
}

    //         for(j = 0; j < col; j++){
    //             m[i+1][j] -= m[i+1][i]*m[i][j];
    //             i++;
    //         }
    //         j++;  
    //     }
    //     MatrixOutput.printMatrix(m);
    // }
    
    // public static double detTriangular(double[][] m){
    //     int i, j;
    //     double det = 1;
    //     int row = MatrixOP.getRowEff(m);
    //     int col = MatrixOP.getColEff(m);
    //     for(i = 0; i < row; i++){
    //         for(j = 0; j < col; j++){
    //             det += m[i][i];
    //         }
    //     }
    //     return det;
    // }