package Function;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import ADT_Matrix.*;

public class InterpolasiPolinom {
    public static double[][] interpolasiMatrix(double[][] m){
        int n = MatrixOP.getRowEff(m);
        double[][] newM = new double[n][n+1];
        
        int i, j;
        for(i = 0; i < n; i++){
            for(j = 0; j < n+1; j++){
                if(j == n){
                    newM[i][j] = m[i][n];
                }
                else{
                    newM[i][j] = Math.pow(m[i][1], j);
                }
            }
        }
        return GaussJordan.mxSolGaussJordan(newM);
    } 

    public static double interpolasiFX(double[][] m, double x){
        int n = MatrixOP.getRowEff(m);
        double sol = 0;

        int i;
        for(i = 0; i < n; i++){
            sol += Math.pow(x, i) * m[i][0];
        }
        return sol;
    }

    public static void printInterpolasi(double[][] m){
        int n = MatrixOP.getRowEff(m);
        int i;

        System.out.print("P(x) = ");
        for(i = 0; i < n; i++){
            DecimalFormat df = new DecimalFormat("0.000");
            if(i == 0){
                System.out.print(df.format(m[i][0]));
            }
            else if(i == 1){
                System.out.print(" + " + df.format(m[i][0]) + "x");
            }
            else{
                System.out.print(" + " + df.format(m[i][0]) + "x^" + i);
            }
        }
    }
}