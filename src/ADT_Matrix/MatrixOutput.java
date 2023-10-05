package ADT_Matrix;
import java.util.*;

import Function.Cofactor;
import Function.Inverse;

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

    public static Scanner scan;
    public static FileWriter write;
    public static String path = "";
    public static SimpleDateFormat dateform = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");

    public static String matrixToStr(double[][] m){
        String str = "";
        int i, j;
        for(i = 0; i < MatrixOP.getRowEff(m); i++){
            for(j = 0; j < MatrixOP.getColEff(m); j++){
                str += m[i][j] + " ";
            }
            str += "\n";
        }
        return str;
    }
    
    public static String createPath(){
        String dir = System.getProperty("user.dir");
        dir = dir.substring(dir.lastIndexOf("\\")+1);
        String directory;
        if(dir.equals("bin")){
            directory = "..\\result\\";
        }
        else{
            directory = "..\\result\\";
        }
        return directory;
    }

    public static void createFile(){
        String dir = createPath();
        Date date = new Date();
        File file = new File(dir + dateform.format(date) + ".txt");
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            path = file.getAbsolutePath();
            System.out.println("File berhasil dibuat.");
        } 
        catch (IOException e) {
            System.err.println("Error creating the file: " + e.getMessage());
        }
    }

    public static void SPLtoFile(String[] result){
        int i;
        try{
            createFile();
            write = new FileWriter(path);
            Date date = new Date();
            write.write(dateform.format(date) + "\n");
            if(result[0].equals("Tidak ada solusi")){
                write.write(result[0]);
                write.close();
            }
            else{
                for(i = 0; i < result.length; i++){
                    write.write("x" + (i+1) + " = " + result[i] + "\n");
                }
                write.close();
            }
            System.out.println("Berhasil menulis pada file ini.");
        }
        catch(IOException e){
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    public static void regresiToFile(String res, double[] X, double regres){
        try{
            createFile();
            Date date = new Date();
            write = new FileWriter(path);
            write.write(dateform.format(date) + "\n");
            write.write("Hasil Interpolasi: \n");
            write.write(res);
            write.write("\nNilai X: \n");
            for (int i = 0; i < X.length; i++){
                write.write("X" + (i+1) + " = " + X[i] + " \n");
            }
            write.write("Hasil Regresi : " + regres);
            write.close();
            System.out.println("Berhasil menulis pada file ini.");
        }
        catch(IOException e){
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    public static void interpolasiToFile(double[] res, double x, double y){
        try{
            createFile();
            Date date = new Date();
            write = new FileWriter(path);
            write.write(dateform.format(date) + "\n");
            write.write("Hasil Interpolasi: \n");
            for (int i = 0; i < res.length; i++){
                write.write("a" + i + " = " + res[i] + " \n");
            }
            write.write("Hasil Interpolasi F(" + x + ") = " + y);
            write.close();
            System.out.println("Berhasil menulis pada file ini.");
        }
        catch(IOException e){
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    // public static void inverseToFile(double[][] m){
    //     try{
    //         String matrixStr = matrixToStr(m);
    //         createFile();
    //         Date date = new Date();
    //         write = new FileWriter(path);
    //         write.write(dateform.format(date) + "\n");
    //         write.write("Matriks Invers: \n");
    //         write.write(matrixStr);
    //         write.close();
    //         System.out.println("Berhasil menulis pada file ini.");
    //     }
    //     catch(IOException e){
    //         System.err.println("Error writing to the file: " + e.getMessage());
    //     }
    // }

    public static void inverseToFile(double[][] m){
        try{
            createFile();
            write = new FileWriter(path);
            Date date = new Date();
            write.write(dateform.format(date) + "\n");
            if(MatrixOP.determinant(m) == 0){
                write.write("Matriks tidak memiliki balikan");
                write.close();
            } else {
                String matrixStr = matrixToStr(m);
                write.write("Matriks Invers: \n");
                write.write(matrixStr);
                write.close();
                System.out.println("Berhasil menulis pada file ini.");
            }
        }
        catch(IOException e){
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    public static void determinanToFile(double res){
        try{
            String result = Double.toString(res);
            createFile();
            Date date = new Date();
            write = new FileWriter(path);
            write.write(dateform.format(date) + "\n");
            write.write("Determinan Matriks : \n");
            write.write(result);
            write.close();
            System.out.println("Berhasil menulis pada file ini.");
        }
        catch(IOException e){
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    public static void bicubicToFile(double a, double b, double c){
        try{
            createFile();
            Date date = new Date();
            write = new FileWriter(path);
            write.write(dateform.format(date) + "\n");
            write.write("Hasil Interpolasi Bicubic Spline : \n");
            write.write("F(" + a + ", " + b + ") = " + c);
            write.close();
            System.out.println("Berhasil menulis pada file ini.");
        }
        catch (IOException e){
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }
}