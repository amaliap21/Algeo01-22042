package Function;
import java.io.*;
import java.util.*;
import ADT_Matrix.*;

public class Triangle {
    public static double detUpperTriangular(double[][] m) {
            double[][] m2 = new double[MatrixOP.getRowEff(m)][MatrixOP.getColEff(m)];
            int i, j, k;
            double det = 1;
            double temp;
    
            for (i = 0; i < MatrixOP.getRowEff(m); i++) {
                for (j = 0; j < MatrixOP.getColEff(m); j++) {
                    m2[i][j] = MatrixOP.getElmt(m, i, j);
                }
            }
    
            for (i = 0; i < MatrixOP.getRowEff(m2); i++) {
                if (MatrixOP.getElmt(m2, i, i) == 0) {
                    for (j = i + 1; j < MatrixOP.getRowEff(m2); j++) {
                        if (MatrixOP.getElmt(m2, j, i) != 0) {
                            m2 = MatrixOP.swapRow(m2, i, j);
                            det *= -1;
                            break;
                        }
                    }
                }
    
                if (MatrixOP.getElmt(m2, i, i) != 0) {
                    for (j = i + 1; j < MatrixOP.getRowEff(m2); j++) {
                        temp = MatrixOP.getElmt(m2, j, i) / MatrixOP.getElmt(m2, i, i);
                        for (k = 0; k < MatrixOP.getColEff(m2); k++) {
                            m2[j][k] -= temp * MatrixOP.getElmt(m2, i, k);
                        }
                    }
                }
            }
    
            for (i = 0; i < MatrixOP.getRowEff(m2); i++) {
                det *= MatrixOP.getElmt(m2, i, i);
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