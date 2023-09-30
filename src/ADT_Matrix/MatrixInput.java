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

    public static double[][] matrix_file() {
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

    public static double[][] matrix_user() {
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
    
    
    public static double[][] polinom_matrix_file() {
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
        pathFile = getPathInput(namaFile);
        // System.out.println(pathFile);
        try {
            // Read file
            file = new File(pathFile);
            isiFile = new Scanner(file);
            row = 0;
            col = 0;
            while (isiFile.hasNextLine()) {
                col = (isiFile.nextLine()).split(" ").length;
            }
            isiFile.close();

            // System.out.println("Jumlah baris: " + row);
            // System.out.println("Jumlah kolom: " + col);

            // Read matrix
            double[][] matrix = new double[lineCount(pathFile)-1][col];
            matrixScan = new Scanner(file);
            while (matrixScan.hasNextLine()) {
                for (i = 0; i < lineCount(pathFile)-2; i++) {
                    for (j = 0; j < col; j++) {
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
    
    // public static double[][] polinom_matrix_file() {
    //     // String fileName = "matrix.txt";
    //     Scanner scan = new Scanner(System.in);
    //     System.out.println("Nama file lengkap dengan type file (e.g.: case1a.txt): ");
    //     String namaFile = scan.nextLine();
    //     // scan.close();

    //     // Create + print path file
    //     String pathFile = getPathInput(namaFile);
    //     try {
    //         // Open the file for reading
    //         FileReader fileReader = new FileReader(namaFile);
    //         BufferedReader bufferedReader = new BufferedReader(fileReader);

    //         // Initialize variables for matrix dimensions and data
    //         int numRows = 0;
    //         int numCols = 0;

    //         // First, count the number of rows and columns in the matrix
    //         String line;
    //         while ((line = bufferedReader.readLine()) != null) {
    //             numRows++;
    //             String[] values = line.trim().split("\\s+");
    //             numCols = values.length;
    //         }

    //         // Create a two-dimensional array to store the matrix
    //         double[][] matrix = new double[lineCount(pathFile) - 1][numCols];

    //         // Reset the reader to the beginning of the file
    //         bufferedReader.close();
    //         fileReader = new FileReader(namaFile);
    //         bufferedReader = new BufferedReader(fileReader);

    //         // Populate the matrix with data from the file
    //         while ((line = bufferedReader.readLine()) != null) {
    //             String[] values = line.trim().split("\\s+");
    //             for(int row = 0; row < lineCount(pathFile)-1; row++){
    //                 for (int col = 0; col < numCols; col++) {
    //                     matrix[row][col] = Integer.parseInt(values[col]);
    //                 }
    //             }
    //         }
    //         return matrix;

    //         // Close the BufferedReader
    //         // bufferedReader.close();

    //         // // Print the matrix
    //         // for (int i = 0; i < numRows - 1; i++) {
    //         //     for (int j = 0; j < numCols; j++) {
    //         //         System.out.print(matrix[i][j] + " ");
    //         //     }
    //         //     System.out.println();
    //         // }
    //     } catch (IOException e) {
    //         System.err.println("Error reading the file: " + e.getMessage());
    //         exit();
    //         double[][] m = new double[1][1];
    //         m[0][0] = 0;
    //         return m;
    //     }
    // }
}
