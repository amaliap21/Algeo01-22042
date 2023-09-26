package ADT_Matrix;

import java.io.*;
import java.util.*;

public class MatrixInput {
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
        pathFile = getPathInput(namaFile);
        System.out.println(pathFile);

        try {
            // Read file
            file = new File(pathFile);
            isiFile = new Scanner(file);
            row = 0;
            col = 0;
            while (isiFile.hasNextLine()) {
                col = (isiFile.nextLine()).split(" ").length;
                row++;
            }
            // isiFile.close();

            System.out.println("Jumlah baris: " + row);
            System.out.println("Jumlah kolom: " + col);

            // Read matrix
            double[][] matrix = new double[row][col];
            matrixScan = new Scanner(file);
            while (matrixScan.hasNextLine()) {
                for (i = 0; i < row; i++) {
                    for (j = 0; j < col; j++) {
                        matrix[i][j] = matrixScan.nextDouble();
                    }
                }
            }
            // matrixScan.close();
            return matrix;
        }

        catch (FileNotFoundException e) {
            double[][] matrix = new double[0][0];
            return matrix;
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
}