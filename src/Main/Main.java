package Main;

import java.text.DecimalFormat;
// import ADT
import java.util.*;

import ADT_Matrix.*;
// import Function.Gauss;
// import Function.Cofactor;
// import Function.Cramer;
// import Function.GaussJordan;
// import Function.Inverse;
// import Function.InverseSpl;
import Function.*;

public class Main {
    int pilih;

    public static void main(String[] args) throws Exception {
        double[][] matrix;
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
                case 5:
                    System.out.println("===== INTERPOLASI BICUBIC SPLINE =====");
                    double[][] mFungsi = MatrixInput.matrix_user();
                    System.out.println();

                    System.out.println("Matrix Fungsi:");
                    MatrixOutput.printMatrix(mFungsi);
                    System.out.println();

                    // double[][] mAb = MatrixInput.matrix_user();
                    // System.out.println();

                    // System.out.println("Matrix A,B pada f(a,b):");
                    // MatrixOutput.printMatrix(mAb);
                    // System.out.println();

                    // MatrixOutput.printMatrix(Inverse.balikanGJReturn(mFungsi));

                    // MatrixOutput.printMatrix(Bicubic.matriksBicubicX());
                    // System.out.println();

                    MatrixOutput.printMatrix(Inverse.balikanGJReturn(mFungsi));
                    System.out.println();

                    // System.out.print("f(a,b): ");
                    // System.out.println(Bicubic.hasilBicubic(Bicubic.matriksBicubicA(mFungsi),
                    // mAb));

                    break;

                case 6:
                    pilih = MatrixInput.choose();

                    if (pilih == 1) {
                        System.out.print("Jumlah peubah (n): ");
                        int n = scan.nextInt();
                        System.out.print("Jumlah sampel (m): ");
                        int m = scan.nextInt();
                        double[][] mx = new double[m][n + 1];
                        for (int i = 0; i < m; i++) {
                            for (int j = 0; j < n + 1; j++) {
                                mx[i][j] = scan.nextDouble();
                            }
                        }
                        System.out.println();
                        System.out.println("Matrix:");
                        MatrixOutput.printMatrix(mx);
                        System.out.println();
                        double[][] newM = Regresi.regresiMatrix(mx);
                        Regresi.printRegresi(newM);
                        System.out.println();
                    } else {
                        matrix = MatrixInput.matrix_file();
                        System.out.println();
                        System.out.println("Matrix:");
                        MatrixOutput.printMatrix(matrix);
                        System.out.println();
                        double[][] newM = Regresi.regresiMatrix(matrix);
                        Regresi.printRegresi(newM);
                        System.out.println();
                    }

                    break;
            }

            if (pilih == 7) {
                System.out.println("Keluar dari program. . .");
                program = false;
            } else if (pilih > 7 || pilih < 1) {
                System.out.println("Input pilihan salah, silakan input ulang.");
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
        // System.out.println(Cofactor.detByCofactor(Cofactor.createMatrixCofactor(m),
        // m, 0));
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

/*
 * case 1:
 * System.out.println("===== SISTEM PERSAMAAN LINEAR =====");
 * 
 * pilih = MatrixInput.choose();
 * 
 * if (pilih == 1) {
 * matrix = MatrixInput.matrix_user();
 * } else {
 * matrix = MatrixInput.matrix_file();
 * }
 * 
 * System.out.println();
 * System.out.println("Matrix:");
 * MatrixOutput.printMatrix(matrix);
 * System.out.println();
 * 
 * System.out.println("===== PILIH METODE/KAIDAH =====");
 * System.out.println("1. Metode eliminasi Gauss");
 * System.out.println("2. Metode eliminasi Gauss-Jordan");
 * System.out.println("3. Metode matriks balikan");
 * System.out.println("4. Kaidah Cramer");
 * 
 * System.out.print("Memilih metode penyelesaian: ");
 * pilih = scan.nextInt();
 * System.out.println();
 * 
 * switch (pilih) {
 * case 1:
 * System.out.println("===== METODE ELIMINASI GAUSS =====");
 * Gauss.matriksGauss(matrix);
 * System.out.println();
 * break;
 * 
 * case 2:
 * System.out.println("===== METODE ELIMINASI GAUSS-JORDAN =====");
 * GaussJordan.matriksGaussJordan(matrix);
 * System.out.println();
 * GaussJordan.solGaussJordan(matrix);
 * System.out.println();
 * break;
 * 
 * case 3:
 * System.out.println("===== METODE MATRIKS BALIKAN =====");
 * Inverse.matriksInverseGJ(matrix);
 * System.out.println();
 * MatrixOutput.printMatrix(Inverse.balikanGJ(matrix));
 * Inverse.matriksInverse(matrix);
 * System.out.println();
 * InverseSpl.solInverse(matrix);
 * System.out.println();
 * break;
 * 
 * case 4:
 * System.out.println("===== KAIDAH CRAMER =====");
 * Cramer.solCramer(matrix);
 * System.out.println();
 * break;
 * default:
 * System.out.println("Input pilihan salah, silakan input ulang.");
 * break;
 * }
 * break;
 * 
 * case 2:
 * System.out.println("===== DETERMINAN =====");
 * 
 * pilih = MatrixInput.choose();
 * 
 * if (pilih == 1) {
 * matrix = MatrixInput.matrix_user();
 * } else {
 * matrix = MatrixInput.matrix_file();
 * }
 * 
 * System.out.println();
 * System.out.println("Matrix:");
 * MatrixOutput.printMatrix(matrix);
 * System.out.println();
 * 
 * System.out.println("===== PILIHAN METODE/KAIDAH =====");
 * System.out.println("1. Metode eliminasi upper-triangular");
 * System.out.println("2. Metode eliminasi lower-triangular");
 * // System.out.println("3. Metode matriks balikan");
 * System.out.println("3. Metode kofaktor");
 * 
 * System.out.print("Memilih metode penyelesaian: ");
 * pilih = scan.nextInt();
 * System.out.println();
 * 
 * switch (pilih) {
 * case 1:
 * System.out.println("==== METODE ELIMINASI UPPER-TRIANGULAR ====");
 * // double [][] upper = Triangle.upperTriangular(m);
 * double detU = Triangle.detUpperTriangular(matrix);
 * // MatrixOutput.printMatrix(upper);
 * System.out.println();
 * System.out.println("Determinannya adalah " + detU);
 * System.out.println();
 * break;
 * 
 * case 2:
 * System.out.println("==== METODE ELIMINASI LOWER-TRIANGULAR ====");
 * break;
 * 
 * // case 3 :
 * // System.out.println("==== METODE MATRIKS BALIKAN ====");
 * 
 * case 3:
 * System.out.println("==== METODE KOFAKTOR ====");
 * System.out.println("Pilih indeks baris/kolom yang ingin dihitung: ");
 * int index = scan.nextInt();
 * MatrixOutput.printMatrix(Cofactor.createMatrixCofactor(matrix));
 * double detC = Cofactor.detByCofactor(Cofactor.createMatrixCofactor(matrix),
 * matrix, index);
 * System.out.println("Determinan matriks ini adalah " + detC);
 * break;
 * 
 * default:
 * System.out.println("Input pilihan salah, silakan input ulang.");
 * break;
 * 
 * }
 * break;
 * case 3:
 * System.out.println("===== MATRIKS BALIKAN =====");
 * 
 * pilih = MatrixInput.choose();
 * 
 * if (pilih == 1) {
 * matrix = MatrixInput.matrix_user();
 * } else {
 * matrix = MatrixInput.matrix_file();
 * }
 * 
 * System.out.println();
 * System.out.println("Matrix:");
 * MatrixOutput.printMatrix(matrix);
 * System.out.println();
 * 
 * System.out.println("===== PILIH METODE =====");
 * System.out.println("1. Metode eliminasi Gauss-Jordan");
 * System.out.println("2. Metode eliminasi kofaktor");
 * 
 * System.out.print("Memilih metode penyelesaian: ");
 * pilih = scan.nextInt();
 * System.out.println();
 * 
 * switch (pilih) {
 * case 1:
 * System.out.println("===== METODE ELIMINASI GAUSS-JORDAN =====");
 * Inverse.matriksInverseGJ(matrix);
 * System.out.println();
 * break;
 * case 2:
 * System.out.println("===== METODE KOFAKTOR =====");
 * Inverse.matriksInverse(matrix);
 * System.out.println();
 * break;
 * default:
 * System.out.println("Input pilihan salah, silakan input ulang.");
 * break;
 * }
 * break;
 * 
 * case 4:
 * System.out.println("===== INTERPOLASI POLINOM =====");
 * pilih = MatrixInput.choose();
 * 
 * if (pilih == 1) {
 * System.out.print("Jumlah sampel (n): ");
 * int n = scan.nextInt();
 * double[][] mx = new double[n][2];
 * for (int i = 0; i < MatrixOP.getRowEff(mx); i++) {
 * for (int j = 0; j < MatrixOP.getColEff(mx); j++) {
 * mx[i][j] = scan.nextDouble();
 * }
 * }
 * System.out.println();
 * System.out.println("Matrix:");
 * MatrixOutput.printMatrix(mx);
 * System.out.println();
 * double[][] newMx = InterpolasiPolinom.interpolasiMatrix(mx);
 * // System.out.println();
 * // MatrixOutput.printMatrix(newMx);
 * System.out.println();
 * System.out.print("x yang ingin dicari nilai fungsinya: ");
 * double x = scan.nextDouble();
 * double sol = InterpolasiPolinom.interpolasiFX(newMx, x);
 * DecimalFormat df = new DecimalFormat("0.0000");
 * System.out.println();
 * System.out.println("f(" + x + ") = " + df.format(sol));
 * System.out.println();
 * } else {
 * matrix = MatrixInput.matrix_file();
 * System.out.println();
 * System.out.println("Matrix:");
 * MatrixOutput.printMatrix(matrix);
 * System.out.println();
 * System.out.print("x yang ingin dicari nilai fungsinya: ");
 * double x = scan.nextDouble();
 * double sol =
 * InterpolasiPolinom.interpolasiFX(InterpolasiPolinom.interpolasiMatrix(matrix)
 * , x);
 * DecimalFormat df = new DecimalFormat("0.0000");
 * System.out.println();
 * System.out.println("f(" + x + ") = " + df.format(sol));
 * System.out.println();
 * }
 * break;
 */