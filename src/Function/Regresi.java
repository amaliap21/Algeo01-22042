package Function;
import ADT_Matrix.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Regresi {
    public static double[][] regresiMatrix(double[][] m){
        int rowM = MatrixOP.getRowEff(m);
        int colM = MatrixOP.getColEff(m);

        double[][] sol = GaussJordan.mxSolGaussJordan(m);
        return sol;
    }

    public static void printRegresi(double[][] m){
        int n = MatrixOP.getRowEff(m);
        int i;

        System.out.print("f(x) = ");
        for(i = 0; i < n; i++){
            DecimalFormat df = new DecimalFormat("0.000");
            if(i == 0){
                System.out.print(df.format(m[i][0]) + "x" + (i+1));    
            }
            else{
                if(m[i][0] < 0){
                    double el = Math.abs((m[i][0]));
                    System.out.print(" - " + df.format(el) + "x" + (i+1));
                }
                else{
                    System.out.print(" + " + df.format(m[i][0]) + "x" + (i+1));
                }
            }
        }
    }
}