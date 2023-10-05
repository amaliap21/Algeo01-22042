package Function;
import ADT_Matrix.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Regresi {
    public static double[][] normalEquation(double[][] m){
        int n = MatrixOP.getRowEff(m); // buat detect jumlah baris biar bisa diproses penjumlahan semua baris
        int row = MatrixOP.getColEff(m); // banyak persamaan sebanyak banyak peubah + 1 (banyak peubah = banyak kolom-1) 
        int col = MatrixOP.getColEff(m)+1; // banyak peubah + 1 konstanta + 1 hasil
        int i, j, k;
        double[][] reg = new double[row][col];

        double[][] newM = new double[n][1];
        for(i = 0; i < n; i++){
            for(j = 0; j < 1; j++){
                newM[i][j] = 1;
            }
        }

        double[][] result = MatrixOP.extendMatrix(newM, m);

        for(i = 0; i < row; i++){
            for(j = 0; j < col; j++){
                double temp = 0;

                for(k = 0; k < n; k++){
                    if(i==0){
                        if(j == 0){
                            temp += 1;
                        }
                        else{
                            temp += result[k][j];
                        }
                    }
                    else if(j == 0){
                        temp += result[k][i];
                    }
                    else{
                        temp += result[k][i]*result[k][j];
                    }
                }
                reg[i][j] = temp;
            }
        }
        return reg;
    }
    

    public static double[][] regresiMatrix(double[][] m){
        int rowM = MatrixOP.getRowEff(m);
        int colM = MatrixOP.getColEff(m);

        double[][] sol = GaussJordan.mxSolGaussJordan(m);
        return sol;
    }


    public static void printReg(double[][] m){
        int n = MatrixOP.getRowEff(m);
        int i;

        System.out.print("f(x) = ");
        for(i = 0; i < n; i++){
            DecimalFormat df = new DecimalFormat("0.000");
            if(i == 0){
                System.out.print(df.format(m[i][0]));
            }
            else{
                if(m[i][0] < 0){
                    double el = Math.abs((m[i][0]));
                    System.out.print(" - " + df.format(el) + "x" + i);
                }
                else{
                    System.out.print(" + " + df.format(m[i][0]) + "x" + i);
                }
            }
        }
    }

    // public static void solRegresiFX(double[][] m){
    //     int n = MatrixOP.getRowEff(m)-1;
    //     double sol = m[0][0];
    //     int i;
    //     double x;
    //     for(i = 0; i < n; i++){
    //         Scanner scan = new Scanner(System.in);
    //         System.out.print("x" + (i+1) + ": ");
    //         x = scan.nextDouble();
    //         sol += m[i+1][0]*x;
    //     }
    //     System.out.println(sol);
    // }

    public static void solRegresiFX(double[][] m, double[][] taksir){
        int n = MatrixOP.getRowEff(m)-1;
        double sol = m[0][0];
        int i;
        DecimalFormat df = new DecimalFormat("0.0000");
        for(i = 1; i < n; i++){
            Scanner scan = new Scanner(System.in);
            sol += taksir[i-1][0]*m[i][0];
        }
        System.out.println("Hasil taksirannya adalah " + df.format(sol));
    }

    public static double[][] inputTaksiran(double[][] m){
        int n = MatrixOP.getRowEff(m)-1;
        int i;
        double[][] x = new double[n][1];
        for(i = 0; i < n; i++){
            Scanner scan = new Scanner(System.in);
            System.out.print("Input x" + (i+1) + ": ");
            x[i][0] = scan.nextDouble();
        }   
        return x;
    }

    public static void printTaksir(double[][] taksir){
        MatrixOutput.printMatrix(taksir);
    }

    public static String stringprintReg(double[][] m){
        int n = MatrixOP.getRowEff(m);
        int i;
        String hasil = "f(x) = ";
        for(i = 0; i < n; i++){
            DecimalFormat df = new DecimalFormat("0.000");
            if(i == 0){
                hasil += df.format(m[i][0]);
            }
            else{
                if(m[i][0] < 0){
                    double el = Math.abs((m[i][0]));
                    hasil += " - " + df.format(el) + "x" + i;
                }
                else{
                    hasil += (" + " + df.format(m[i][0]) + "x" + i);
                }
            }
        }
        return hasil;
    }

    public static double hasilregresi(double[][] m, double[][] taksir){
        int n = MatrixOP.getRowEff(m)-1;
        double sol = m[0][0];
        int i;
        for(i = 1; i < n; i++){
            Scanner scan = new Scanner(System.in);
            sol += taksir[i-1][0]*m[i][0];
        }
        return sol;
    }

    

    // public static double regresiLinear(double[][] fx){
    //     double sol = fx[0];
    //     int i, n = x.length;
        
    //     for(i = 0; i < n; i++){
    //         sol += fx[i+1] * x[i];
    //     }
    //     return sol;
    // }

}
    // public static double regresiLinear(double[][] fx){
    //     double sol = fx[0];
    //     int i, n = x.length;
        
    //     for(i = 0; i < n; i++){
    //         sol += fx[i+1] * x[i];
    //     }
    //     return sol;
    // }