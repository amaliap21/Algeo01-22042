package ADT_Matrix;
import java.util.*;
import java.io.*;
import java.text.*;

public class MatrixOutput {
    public static void printMatrix(double[][] matrix) {
        int i, j;
        DecimalFormat df = new DecimalFormat("0.000");
        for (i = 0; i < matrix.length; i++) {
            for (j = 0; j < matrix[0].length; j++) {
                System.out.print(df.format(matrix[i][j]) + "\t");
            }
            System.out.println();
        }
    }

    

    // public static Scanner scan;
    // public static FileWriter pf;
    // public static String path = "";
    // public static String directory = "test\\result\\";
    // public static String out;
    // public static double det;
    // public static double[][] matrix;
    // public static String function;
    // public static double intRes;
    // public static double intGuess;
    // public static double regRes;
    // public static double[] regGuess;
    // public static SimpleDateFormat dateform = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");

    // public void output(double n){
    //     det = n;
    // }

    // public void output(double[][] m){
    //     matrix = m;
    // }

    // public void output(String s){
    //     out = s;
    // }

    // public void output(String s, double x, double taksiran){
    //     function = s;
    //     intRes = x;
    //     intGuess = taksiran;
    // }

    // public void output(String s, double x, double[] taksiran){
    //     function = s;
    //     regRes = x;
    //     regGuess = taksiran;
    // }

    // public static String matrixToStr(double[][] m){
    //     String str = "";
    //     int i, j;
    //     for(i = 0; i < MatrixOP.getRowEff(m); i++){
    //         for(j = 0; j < MatrixOP.getColEff(m); j++){
    //             if(j == MatrixOP.getColEff(m)){
    //                 str += m[i][j];
    //             }
    //             else{
    //                 str += m[i][j] + " ";
    //             }
    //         }
    //         str += "\n";
    //     }
    //     return str;
    // }
    
    // public static void createPath(){
    //     String dir = System.getProperty("user.dir");
    //     dir = dir.substring(dir.lastIndexOf("\\")+1);
    //     String directory;
    //     if(dir.equals("bin")){
    //         directory = "..\\test\\result\\";
    //     }
    //     else{
    //         directory = "test\\result\\";
    //     }
    //     // return directory;
    // }

    // public static void createFile(){
    //     createPath();
    //     Date date = new Date();
    //     File file = new File(directory + dateform.format(date) + ".txt");
    //     try {
    //         if(!file.exists()){
    //             file.createNewFile();
    //         }
    //         path = file.getAbsolutePath();
    //         System.out.println("File berhasil dibuat.");
    //     } 
    //     catch (IOException e) {
    //         System.err.println("Error creating the file: " + e.getMessage());
    //     }
    // }

    // public static void SPLtoFile(String[] result){
    //     int i;
    //     try{
    //         createFile();
    //         pf = new FileWriter(path);
    //         Date date = new Date();
    //         pf.write(dateform.format(date) + "\n");
    //         if(result[0].equals("Tidak ada solusi")){
    //             pf.write(result[0]);
    //             pf.close();
    //         }
    //         else{
    //             for(i = 0; i < result.length; i++){
    //                 pf.write("x" + (i+1) + " = " + result[i] + "\n");
    //             }
    //             pf.close();
    //         }
    //         System.out.println("Berhasil menulis pada file ini.");
    //     }
    //     catch(IOException e){
    //         System.err.println("Error writing to the file: " + e.getMessage());
    //     }
    // }

    // public static void regresiToFile(String res, double[] X, double regres){
    //     try{
    //         createFile();
    //         Date date = new Date();
    //         pf = new FileWriter(path);
    //         pf.write(dateform.format(date) + "\n");
    //         pf.write("Hasil Interpolasi: \n");
    //         pf.write(res);
    //         pf.write("\nNilai X: \n");
    //         for (int i = 0; i < X.length; i++){
    //             pf.write("X" + (i+1) + " = " + X[i] + " \n");
    //         }
    //         pf.write("Hasil Regresi : " + regres);
    //         pf.close();
    //         System.out.println("Berhasil menulis pada file ini.");
    //     }
    //     catch(IOException e){
    //         System.err.println("Error writing to the file: " + e.getMessage());
    //     }
    // }

    // public static void interpolasiToFile(double[] res, double x, double y){
    //     try{
    //         createFile();
    //         Date date = new Date();
    //         pf = new FileWriter(path);
    //         pf.write(dateform.format(date) + "\n");
    //         pf.write("Hasil Interpolasi: \n");
    //         for (int i = 0; i < res.length; i++){
    //             pf.write("a" + i + " = " + res[i] + " \n");
    //         }
    //         pf.write("Hasil Interpolasi F(" + x + ") = " + y);
    //         pf.close();
    //         System.out.println("Berhasil menulis pada file ini.");
    //     }
    //     catch(IOException e){
    //         System.err.println("Error writing to the file: " + e.getMessage());
    //     }
    // }

    // public static void inverseToFile(){
    //     try{
    //         String matrixStr = matrixToStr();
    //         createFile();
    //         Date date = new Date();
    //         pf = new FileWriter(path);
    //         pf.write(dateform.format(date) + "\n");
    //         pf.write("Matriks Invers: \n");
    //         pf.write(matrixStr);
    //         pf.close();
    //         System.out.println("Berhasil menulis pada file ini.");
    //     }
    //     catch(IOException e){
    //         System.err.println("Error writing to the file: " + e.getMessage());
    //     }
    // }

    // public static void determinanToFile(){
    //     try{
            
    //         String result = String.valueOf(det);
    //         createFile();
    //         Date date = new Date();
    //         pf = new FileWriter(path);
    //         pf.write(dateform.format(date) + "\n");
    //         pf.write("Determinan Matriks : \n");
    //         pf.write(result);
    //         pf.close();
    //         System.out.println("Berhasil menulis pada file ini.");
    //     }
    //     catch(IOException e){
    //         System.err.println("Error writing to the file: " + e.getMessage());
    //     }
    // }

    // public static void bicubicToFile(double a, double b, double c){
    //     try{
    //         createFile();
    //         Date date = new Date();
    //         pf = new FileWriter(path);
    //         pf.write(dateform.format(date) + "\n");
    //         pf.write("Hasil Interpolasi Bicubic Spline : \n");
    //         pf.write("F(" + a + ", " + b + ") = " + c);
    //         pf.close();
    //         System.out.println("Berhasil menulis pada file ini.");
    //     }
    //     catch (IOException e){
    //         System.err.println("Error writing to the file: " + e.getMessage());
    //     }

    // }
}