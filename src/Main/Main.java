package Main;

// import ADT
import java.util.*;

import ADT_Matrix.*;
import Function.*;

public class Main {
    int pilih;
    public static void main(String[] args) throws Exception {
        double[][] m;
        boolean program = true;
        Scanner scan;
        int pilih;
        scan = new Scanner(System.in);

        
        while (program) {
            System.out.println("===== PILIHAN MENU =====");
            System.out.println("1. Sistem Persamaan Linear");
            System.out.println("2. Determinan");
            System.out.println("3. Matriks Balikan");
            System.out.println("4. Interpolasi Polinom");
            System.out.println("5. Interpolasi Bicubic Spline");
            System.out.println("6. Regresi Linear Berganda");
            System.out.println("7. Keluar");
            System.out.print("Memilih menu: ");
            pilih = scan.nextInt();
            System.out.println();
            
            switch (pilih) {
                case 1:
                    System.out.println("===== SISTEM PERSAMAAN LINEAR =====");

                    pilih = MatrixInput.choose();
                    
                    if (pilih == 1) {
                        m = MatrixInput.matrix_user();
                    } else {
                        m = MatrixInput.matrix_file();
                    }

                    System.out.println();
                    System.out.println("Matrix:");
                    MatrixOutput.printMatrix(m);
                    System.out.println();
                    
                    System.out.println("===== PILIHAN MENU =====");
                    System.out.println("1. Metode eliminasi Gauss");
                    System.out.println("2. Metode eliminasi Gauss-Jordan");
                    System.out.println("3. Metode matriks balikan");
                    System.out.println("4. Kaidah Cramer");

                    System.out.print("Memilih metode penyelesaian: ");
                    pilih = scan.nextInt();
                    System.out.println();

                    switch (pilih) {
                        case 1:
                            System.out.println("===== METODE ELIMINASI GAUSS =====");
                            // Gauss.gauss(m);
                            System.out.println();
                            break;

                        case 2:
                            System.out.println("===== METODE ELIMINASI GAUSS-JORDAN =====");
                            GaussJordan.matriksGaussJordan(m);
                            System.out.println();
                            GaussJordan.solGaussJordan(m);
                            System.out.println();
                            break;

                        case 3:
                            System.out.println("===== METODE MATRIKS BALIKAN =====");
                            Inverse.matriksInverseGJ(m);
                            System.out.println();
                            Inverse.matriksInverse(m);
                            System.out.println();
                            InverseSpl.solInverse(m);
                            System.out.println();
                            break;

                        case 4:
                            System.out.println("===== KAIDAH CRAMER =====");
                            Cramer.solCramer(m);
                            System.out.println();
                            break;

                        default:
                        System.out.println("Input pilihan salah, silakan input ulang.");
                        break;
                    }
                    break;

                case 2:
                    System.out.println("===== DETERMINAN =====");
                    
                    pilih = MatrixInput.choose();

                    if (pilih == 1) {
                        m = MatrixInput.matrix_user();
                    } else {
                        m = MatrixInput.matrix_file();
                    }

                    System.out.println();
                    System.out.println("Matrix:");
                    MatrixOutput.printMatrix(m);
                    System.out.println();
                    
                    System.out.println("===== PILIHAN MENU =====");
                    System.out.println("1. Metode eliminasi upper-triangular");
                    System.out.println("2. Metode eliminasi lower-triangular");
                    // System.out.println("3. Metode matriks balikan");
                    System.out.println("3. Metode kofaktor");

                    System.out.print("Memilih metode penyelesaian: ");
                    pilih = scan.nextInt();
                    System.out.println();

                    switch (pilih) {
                        case 1 :
                            System.out.println("==== METODE ELIMINASI UPPER-TRIANGULAR ====");
                            Triangle.upperTriangular(m);
                            // System.out.println(Triangle.detTriangular(m));
                            break;

                        case 2 :
                            System.out.println("==== METODE ELIMINASI LOWER-TRIANGULAR ====");
                            break;

                        // case 3 :
                        //     System.out.println("==== METODE MATRIKS BALIKAN ====");
                            

                        case 3 :
                            System.out.println("==== METODE KOFAKTOR ====");
                            System.out.println("Pilih indeks baris/kolom yang ingin dihitung: ");
                            int index = scan.nextInt();
                            MatrixOutput.printMatrix(Cofactor.createMatrixCofactor(m));
                            double det = Cofactor.detByCofactor(Cofactor.createMatrixCofactor(m), m, index);
                            System.out.println("Determinan matriks ini adalah " + det);
                            break;

                        default:
                        System.out.println("Input pilihan salah, silakan input ulang.");
                        break;
                    }
                    break;
            }

            if (pilih == 7) {
                program = false;
            }
            
        }
        
        // // getcof = Cofactor.getCofactor(m, 0, 0);
        // // cof = Cofactor.createMatrixCofactor(m);
        // // Scanner input = new Scanner(System.in);
        // // System.out.print("Indeks yang diambil: ");
        // // n = input.nextInt();
        // detcof = Cofactor.detByCofactor(cof, m, n);
        // System.out.println();
        // // MatrixOutput.printMatrix(cof);
        // // System.out.println();
        // // System.out.println("Determinannya adalah " + detcof);
        // // System.out.println();
        // // MatrixOutput.printMatrix(getcof);
        // Cramer.solCramer(m);
        // System.out.println();
        // getInverse = Inverse.inverseMatriks(m);
        // // getcof = Cofactor.getCofactor(m, 0, 0);
        // // cof = Cofactor.createMatrixCofactor(m);
        // // detcof = Cofactor.detByCofactor(cof, m, 2);
        // // System.out.println();
        // // MatrixOutput.printMatrix(cof);
        // // System.out.println();
        // // System.out.println(detcof);
        // System.out.println();
        // InverseSpl.solusiInverse(m);
        // System.out.println();
        
        // System.out.println();
        // MatrixOutput.printMatrix(m);
        // System.out.println();
        // System.out.println(Cofactor.detByCofactor(Cofactor.createMatrixCofactor(m), m, 0));
        // MatrixOutput.printMatrix(Gauss.swapRow(m, 1, 2));

        // double [][] sol = gaussSPL
        

        
        // MatrixOutput.printMatrix(getInverse);
    }
}

/*
 * case 2:
 * System.out.println("===== DETERMINAN =====");
 * pilih = MatrixInput.choose();
 * 
 * if (pilih == 1) {
 * m = MatrixInput.matrix_user();
 * } else {
 * m = MatrixInput.matrix_file();
 * }
 * 
 * System.out.println("Matrix:");
 * MatrixOutput.printMatrix(m);
 * System.out.println();
 * 
 * System.out.println("===== PILIHAN MENU =====");
 * System.out.println("1. Metode eliminasi Gauss");
 * System.out.println("2. Metode eliminasi Gauss-Jordan");
 * System.out.println("3. Metode matriks balikan");
 * 
 * System.out.println("Choose menu (ketik angkanya saja 1, 2, 3, 4, atau 5): ");
 * pilih = scan.nextInt();
 * 
 * switch (pilih) {
 * case 1:
 * System.out.println("===== METODE ELIMINASI GAUSS =====");
 * Gauss.gauss(m);
 * break;
 * case 2:
 * System.out.println("===== METODE ELIMINASI GAUSS-JORDAN =====");
 * GaussJordan.gaussJordan(m);
 * break;
 * case 3:
 * System.out.println("===== METODE MATRIKS BALIKAN =====");
 * InverseSpl.solInverse(m);
 * break;
 * default:
 * System.out.println("Input pilihan salah, silakan input ulang.");
 * break;
 * }
 * break;
 */

// double[][] m, cof, getcof;
// double detcof;
// String[] solcram;
// double[][] m, cof, getcof;
// double detcof;
// double[][] getInverse;
// if (pilih == 1) {
// m = MatrixInput.matrix_user();
// } else {
// m = MatrixInput.matrix_file();
// }
// System.out.println();
// MatrixOutput.printMatrix(m);
// System.out.println();

// getcof = Cofactor.getCofactor(m, 0, 0);
// cof = Cofactor.createMatrixCofactor(m);
// Scanner input = new Scanner(System.in);
// System.out.print("Indeks yang diambil: ");
// n = input.nextInt();
// detcof = Cofactor.detByCofactor(cof, m, n);
// System.out.println();
// MatrixOutput.printMatrix(cof);
// System.out.println();
// System.out.println("Determinannya adalah " + detcof);
// System.out.println();
// MatrixOutput.printMatrix(getcof);
// Cramer.solCramer(m);
// System.out.println();
// getInverse = Inverse.inverseMatriks(m);
// getcof = Cofactor.getCofactor(m, 0, 0);
// cof = Cofactor.createMatrixCofactor(m);
// detcof = Cofactor.detByCofactor(cof, m, 2);
// System.out.println();
// MatrixOutput.printMatrix(cof);
// System.out.println();
// System.out.println(detcof);
// System.out.println();
// InverseSpl.solusiInverse(m);
