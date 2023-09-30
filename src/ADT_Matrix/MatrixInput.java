package ADT_Matrix;

import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

public class MatrixInput {
    // public static Scanner sc;
    public static int choose() {
        Scanner choice;
        int pilih = 0;
        boolean program = true;
        choice = new Scanner(System.in);

        while (program) {
            System.out.println("===== PILIHAN READ MATRIX =====");
            // System.out.println("Pilihan untuk to read matrix:");
            System.out.println("1. Input manual");
            System.out.println("2. Baca dari file .txt (Pastikan bahwa .txt sudah ada di folder yang sesuai)");
            System.out.print("Cara input matriks: ");
            pilih = choice.nextInt();

            if (pilih == 1 || pilih == 2) {
                program = false;
            } else {
                System.out.println("Input pilihan salah, silakan input ulang.");
            }
        }

        // choice.close();
        return pilih;
    }

    public static void exit(){
        System.exit(0);
    }

    public static int lineCount(String path){
        try{
            File file =  new File(path);
            Scanner isiFile = new Scanner(file);
            int row = 0, col =0;
            while(isiFile.hasNextLine()){
                col = (isiFile.nextLine()).split(" ").length;
                row ++;
            }
            isiFile.close();
            return row;
        }
        catch (FileNotFoundException e) {
            return 0;
        }
        catch(Exception e){
            return 0;
        }        
    }

    public static int columnCount(String path){
        try{
            File file =  new File(path);
            Scanner isiFile = new Scanner(file);
            int row = 0, col =0;
            col = (isiFile.nextLine()).split(" ").length;
            isiFile.close();
            return col;
        }
        catch (FileNotFoundException e) {
            return 0;
        }
        catch(Exception e){
            return 0;
        }        
    }

    public static double[][] matrixFile() {
        Scanner scan, isiFile, matrixScan;
        String namaFile, pathFile;
        File file;
        int row, col, i, j;

        // Scan nama file
        scan = new Scanner(System.in);
        System.out.println("Nama file lengkap dengan type file (e.g.: case1a.txt): ");
        namaFile = scan.nextLine();
        // scan.close();

        // Create + print path file
        // pathFile = "..\\test\\" + namaFile;
        pathFile = getPathInput(namaFile);
        // System.out.println(pathFile);

        try {
            // Read file
            file = new File(pathFile);
            isiFile = new Scanner(file);
            row = 0;
            col = 0;
            while (isiFile.hasNextLine()) {
                row = (isiFile.nextLine()).split(" ").length;
                col ++;
                // col = (isiFile.nextLine()).split(" ").length;
                // row++;
            }
            isiFile.close();

            // System.out.println("Jumlah baris: " + row);
            // System.out.println("Jumlah kolom: " + col);

            // Read matrix
            double[][] matrix = new double[col][row];
            matrixScan = new Scanner(file);
            while (matrixScan.hasNextLine()) {
                for (i = 0; i < col; i++) {
                    for (j = 0; j < row; j++) {
                        matrix[i][j] = matrixScan.nextDouble();
                    }
                }
            }
            matrixScan.close();
            return matrix;
        }
        catch (FileNotFoundException e) {
            System.out.println("Error!");
            System.out.println("File tidak ditemukan.");
            exit();
            double[][] m = new double[1][1];
            m[0][0] = 0;
            return m;
        }
        catch(Exception e){
            System.out.println(e);
            exit();
            double[][] m = new double[1][1];
            m[0][0] = 0;
            return m;
        }
    }

    public static String getPathInput(String namaFile) {
        String pathFile;
        String path;

        path = System.getProperty("user.dir");
        if (path.contains("src")) {
            path = path.replaceAll("src", "");
            pathFile = path + "test\\" + namaFile;
        } else {
            pathFile = path + "\\test\\" + namaFile;
        }

        return pathFile;
    }

    public static double[][] matrixUser() {
        int m, n, i, j;
        Scanner scan = null;

        try {
            // Input m dan m
            scan = new Scanner(System.in);
            System.out.print("Jumlah baris matrix: ");
            m = scan.nextInt();
            System.out.print("Jumlah kolom matrix: ");
            n = scan.nextInt();

            // Declare matrix
            double[][] matrix = new double[m][n];

            // Input elemen matrix
            System.out.println("Input elemen-elemen matrix: ");
            for (i = 0; i < m; i++) {
                for (j = 0; j < n; j++) {
                    matrix[i][j] = scan.nextDouble();
                    scan.nextLine();
                }
            }
            return matrix;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        // finally {
        // if (scan != null) {
        // scan.close();
        // }
        // }
    }

    public static double[][] matrixHilbert(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Ordo matrix : ");
        int ordo = scan.nextInt();
        double[][] hilbert = new double[ordo][ordo+1];
        int i, j;
        for(i = 0; i < ordo; i++){
            for(j = 0; j < ordo; j++){
                hilbert[i][j] = 1/(i+j+1);
            }
        }
        for(i = 0; i < ordo; i++){
            if(i == 0){
                hilbert[i][ordo] = 1;
            }
            else{
                hilbert[i][ordo] = 0;
            }
        }
        return hilbert;
    }
    
    
    public static double[][] PRBMatrixFile(String path) {
        Scanner scan, isiFile, matrixScan;
        String namaFile, pathFile;
        File file;
        int row, col, i, j;

        // // Scan nama file
        // scan = new Scanner(System.in);
        // System.out.println("Nama file lengkap dengan type file (e.g.: case1a.txt): ");
        // namaFile = scan.nextLine();
        // // scan.close();
        // // Create + print path file
        // pathFile = getPathInput(namaFile);
        // System.out.println(pathFile);
        // System.out.println(lineCount(pathFile));
        // System.out.println(columnCount(pathFile));
        try {
            // Read file
            file = new File(path);

            // System.out.println("Jumlah baris: " + row);
            // System.out.println("Jumlah kolom: " + col);

            // Read matrix
            double[][] matrix = new double[lineCount(path)-1][columnCount(path)];
            matrixScan = new Scanner(file);
            i = 0;
            while (matrixScan.hasNextLine() && i<lineCount(path)-1) {
                for (j = 0; j < columnCount(path); j++) {
                    matrix[i][j] = matrixScan.nextDouble();
                }
                i++;
            }
            // matrixScan.close();
            return matrix;
        }
        catch (FileNotFoundException e) {
            System.out.println("Error!");
            System.out.println("File tidak ditemukan.");
            exit();
            double[][] m = new double[1][1];
            m[0][0] = 0;
            return m;
        }
        catch(Exception e){
            System.out.println(e);
            exit();
            double[][] m = new double[1][1];
            m[0][0] = 0;
            return m;
        }
    }

    public static double polinomTaksiranFile(String path){
        try{
            double result;
            double[][] matrix = new double[lineCount(path)-1][columnCount(path)];
            int i = 0, j;
            File file = new File(path);
            Scanner matrixScan = new Scanner(file);
            while (matrixScan.hasNextLine() && i<lineCount(path)-1) {
                for (j = 0; j < columnCount(path); j++) {
                    matrix[i][j] = matrixScan.nextDouble();
                }
                i++;
            }
            // matrixScan.close();
            result = matrixScan.nextDouble();
            return result;
        }
        catch (FileNotFoundException e) {
            System.out.println("Error!");
            System.out.println("File tidak ditemukan.");
            exit();
            double[][] m = new double[1][1];
            m[0][0] = 0;
            return 0;
        }
        catch(Exception e){
            System.out.println(e);
            exit();
            double[][] m = new double[1][1];
            m[0][0] = 0;
            return 0;
        }
    }

    public static double[][] regresiTaksiranFile(String path){
        try{
            double[][] result = new double[columnCount(path)-1][1];
            double[][] matrix = new double[lineCount(path)-1][columnCount(path)];
            int i = 0, j;
            File file = new File(path);
            Scanner matrixScan = new Scanner(file);
            while (matrixScan.hasNextLine() && i<lineCount(path)-1) {
                for (j = 0; j < columnCount(path); j++) {
                    matrix[i][j] = matrixScan.nextDouble();
                }
                i++;
            }
            // matrixScan.close();
            for(i=0; i<columnCount(path)-1; i++){
                result[i][0] = matrixScan.nextDouble();
            }
            return result;
        }
        catch (FileNotFoundException e) {
            System.out.println("Error!");
            System.out.println("File tidak ditemukan.");
            exit();
            double[][] m = new double[1][1];
            m[0][0] = 0;
            return m;
        }
        catch(Exception e){
            System.out.println(e);
            exit();
            double[][] m = new double[1][1];
            m[0][0] = 0;
            return m;
        }
    }

    public static double[][] convBicubic(double[][] m){
        double[][] bic = new double[16][1];
        int row = MatrixOP.getRowEff(m);
        int col = MatrixOP.getColEff(m);
        int i, j, k = 0;
        while(k < 16){
            for(i = 0; i < row; i++){
                for(j = 0; j < col; j++){
                    bic[k][0] = m[i][j];
                    k++;
                }
            }
        }
        return bic;
    }

    public static double[][] bicubicTaksiranFile(String path){
        try{
            double[][] result = new double[1][2];
            double[][] matrix = new double[lineCount(path)-1][columnCount(path)];
            int i = 0, j;
            File file = new File(path);
            Scanner matrixScan = new Scanner(file);
            while (matrixScan.hasNextLine() && i<lineCount(path)-1) {
                for (j = 0; j < columnCount(path); j++) {
                    matrix[i][j] = matrixScan.nextDouble();
                }
                i++;
            }
            // matrixScan.close();
            for(i=0; i<2; i++){
                result[0][i] = matrixScan.nextDouble();
            }
            return result;
        }
        catch (FileNotFoundException e) {
            System.out.println("Error!");
            System.out.println("File tidak ditemukan.");
            exit();
            double[][] m = new double[1][1];
            m[0][0] = 0;
            return m;
        }
        catch(Exception e){
            System.out.println(e);
            exit();
            double[][] m = new double[1][1];
            m[0][0] = 0;
            return m;
        }
    }
}


    // public static double polinom_taksiran_file(String pathFile) {
    //     Scanner scan, isiFile, matrixScan;
    //     String namaFile;
    //     File file;
    //     int row, col, i, j;

    //     // // Scan nama file
    //     // scan = new Scanner(System.in);
    //     // System.out.println("Nama file lengkap dengan type file (e.g.: case1a.txt): ");
    //     // namaFile = scan.nextLine();
    //     // // scan.close();

    //     // // Create + print path file
    //     // pathFile = getPathInput(namaFile);
    //     // // System.out.println(pathFile);

    //     try {
    //         // Read file
    //         file = new File(pathFile);
    //         isiFile = new Scanner(file);
    //         row = 0;
    //         int curRow = 0;
    //         col = 0;
    //         while (isiFile.hasNextLine()) {
    //             col = (isiFile.nextLine()).split(" ").length;
    //             row++;
    //         }
    //         while (isiFile.hasNextLine()) {
    //             curRow++;
    //             if (curRow == row-1){
    //                 String temp = new Scanner
    //             }
    //         }
    //         // isiFile.close();

    //         // System.out.println("Jumlah baris: " + row);
    //         // System.out.println("Jumlah kolom: " + col);

    //         // Read matrix
    //         double[][] matrix = new double[row-1][col];
    //         matrixScan = new Scanner(file);
    //         while (matrixScan.hasNextLine()) {
    //             for (i = 0; i < row; i++) {
    //                 for (j = 0; j < col; j++) {
    //                     matrix[i][j] = matrixScan.nextDouble();
    //                 }
    //             }
    //         }
    //         isiFile.close();
    //         matrixScan.close();
    //         Scanner taksiranScan = new Scanner(file);
    //         double x = taksiranScan.nextDouble();
    //         return x;
    //     }

    //     catch (FileNotFoundException e) {
    //         double x = 0;
    //         return x;
    //     }
    // }