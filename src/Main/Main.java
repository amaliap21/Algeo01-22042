package Main;

import java.text.DecimalFormat;
// import ADT
import java.util.*;
import java.io.*;

import ADT_Matrix.*;
// import Function.Gauss;
// import Function.Cofactor;
// import Function.Cramer;
// import Function.GaussJordan;
// import Function.Inverse;
// import Function.InverseSpl;
import Function.*;

public class Main {

    public static void main(String[] args) throws Exception {
        double[][] matrix;
        boolean program = true;
        Scanner scan;
        int pilih;
        String save;
        scan = new Scanner(System.in);
        DecimalFormat df;
        boolean sprog1 = false;
        boolean sprog2 = false;
        boolean sprog3 = false;
        boolean printFile;
        String fileName, resString;
        double detC, detU, detL;
        double[][] invGJ, invCof;

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

            if (pilih == 7) {
                System.out.println("Keluar dari program. . .");
                System.out.println();
                program = false;
            } else if (pilih > 7 || pilih < 1) {
                System.out.println("Input pilihan salah, silakan input ulang.");
                System.out.println();
            }

            switch (pilih) {
                case 1:
                    sprog1 = true;
                    System.out.println("===== SISTEM PERSAMAAN LINEAR =====");

                    pilih = MatrixInput.choose();

                    if (pilih == 1) {
                        matrix = MatrixInput.matrixUser();
                    } else if (pilih == 2) {
                        matrix = MatrixInput.matrixFile();
                    } else {
                        matrix = MatrixInput.matrixHilbert();
                    }

                    System.out.println();
                    System.out.println("Matrix:");
                    MatrixOutput.printMatrix(matrix);
                    System.out.println();

                    System.out.println("===== PILIH METODE/KAIDAH =====");
                    System.out.println("1. Metode eliminasi Gauss");
                    System.out.println("2. Metode eliminasi Gauss-Jordan");
                    System.out.println("3. Metode matriks balikan");
                    System.out.println("4. Kaidah Cramer");

                    scan.nextLine();
                    System.out.print("Memilih metode penyelesaian: ");
                    pilih = scan.nextInt();
                    System.out.println();

                    switch (pilih) {
                        case 1:
                            System.out.println("===== METODE ELIMINASI GAUSS =====");
                            Gauss.matriksGauss(matrix);
                            System.out.println();
                            sprog1 = false;

                            if (MatrixOP.solTidakAda(Gauss.forwardOBE(matrix))) {
                                System.out.println("Matriks tidak memiliki solusi SPL.");
                                System.out.println();

                                printFile = Output.userPrintFile();
                                if (printFile) {
                                    System.out
                                            .print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                    scan.nextLine();
                                    fileName = scan.nextLine();
                                    Output.delFile(fileName);
                                    Output.printFile(fileName,
                                            "======= SOLUSI SPL DENGAN METODE ELIMINASI GAUSS ======");
                                    Output.printFile(fileName, "Matriks tidak memiliki solusi SPL.");
                                    System.out.println("Berhasil create dan write pada file ini.");
                                    System.out.println();
                                } else {
                                    System.out.println("======= SOLUSI SPL DENGAN METODE ELIMINASI GAUSS ======");
                                    System.out.println("Matriks tidak memiliki solusi SPL.");
                                    System.out.println();
                                }
                            } else if (MatrixOP.solBanyak(Gauss.forwardOBE(matrix))) {
                                Gauss.parametriksolution(matrix);
                                System.out.println();

                                printFile = Output.userPrintFile();
                                if (printFile) {
                                    System.out
                                            .print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                    scan.nextLine();
                                    fileName = scan.nextLine();
                                    Output.delFile(fileName);
                                    Output.printFile(fileName,
                                            "======= SOLUSI SPL DENGAN METODE ELIMINASI GAUSS ======");
                                    Output.printFile(fileName, Gauss.strparametriksolution(matrix));
                                    System.out.println("Berhasil create dan write pada file ini.");
                                    System.out.println();
                                } else {
                                    System.out.println("======= SOLUSI SPL DENGAN METODE ELIMINASI GAUSS ======");
                                    Gauss.parametriksolution(matrix);
                                    System.out.println();
                                }
                            } else {
                                Gauss.displayuniqueSolGauss(matrix);
                                System.out.println();

                                printFile = Output.userPrintFile();
                                if (printFile) {
                                    System.out
                                            .print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                    scan.nextLine();
                                    fileName = scan.nextLine();
                                    Output.delFile(fileName);
                                    Output.printFile(fileName,
                                            "======= SOLUSI SPL DENGAN METODE ELIMINASI GAUSS ======");
                                    Output.printFile(fileName, Gauss.strUniqueSol(matrix));
                                    System.out.println("Berhasil create dan write pada file ini.");
                                    System.out.println();
                                } else {
                                    System.out.println("======= SOLUSI SPL DENGAN METODE ELIMINASI GAUSS ======");
                                    Gauss.displayuniqueSolGauss(matrix);
                                    System.out.println();
                                }
                            }
                            break;

                        case 2:
                            System.out.println("===== METODE ELIMINASI GAUSS-JORDAN =====");
                            GaussJordan.matriksGaussJordan(matrix);
                            System.out.println();
                            sprog1 = false;

                            if (MatrixOP.solTidakAda(GaussJordan.gaussJordan(matrix))) {
                                System.out.println("Matriks tidak memiliki solusi.");
                                System.out.println();
                                printFile = Output.userPrintFile();
                                if (printFile) {
                                    System.out
                                            .print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                    scan.nextLine();
                                    fileName = scan.nextLine();
                                    Output.delFile(fileName);
                                    Output.printFile(fileName,
                                            "======= SOLUSI SPL DENGAN METODE ELIMINASI GAUSS JORDAN ======");
                                    Output.printFile(fileName, "Matriks tidak memiliki solusi SPL.");
                                    System.out.println("Berhasil create dan write pada file ini.");
                                    System.out.println();
                                } else {
                                    System.out
                                            .println("======= SOLUSI SPL DENGAN METODE ELIMINASI GAUSS JORDAN ======");
                                    System.out.println("Matriks tidak memiliki solusi SPL.");
                                    System.out.println();
                                }
                            } else if (MatrixOP.solBanyak(GaussJordan.gaussJordan(matrix))) {
                                Gauss.parametriksolution(matrix);
                                System.out.println();
                                printFile = Output.userPrintFile();
                                if (printFile) {
                                    System.out
                                            .print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                    scan.nextLine();
                                    fileName = scan.nextLine();
                                    Output.delFile(fileName);
                                    Output.printFile(fileName,
                                            "======= SOLUSI SPL DENGAN METODE ELIMINASI GAUSS JORDAN ======");
                                    Output.printFile(fileName, Gauss.strparametriksolution(matrix));
                                    System.out.println("Berhasil create dan write pada file ini.");
                                    System.out.println();
                                } else {
                                    System.out
                                            .println("======= SOLUSI SPL DENGAN METODE ELIMINASI GAUSS JORDAN ======");
                                    if (MatrixOP.solBanyak(matrix)) {
                                        Gauss.parametriksolution(matrix);
                                        System.out.println();
                                    }
                                }
                            } else {
                                GaussJordan.solGaussJordan(matrix);
                                System.out.println();

                                printFile = Output.userPrintFile();
                                System.out.println();
                                if (printFile) {
                                    System.out
                                            .print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                    scan.nextLine();
                                    fileName = scan.nextLine();
                                    Output.delFile(fileName);
                                    Output.printFile(fileName,
                                            "======= SOLUSI SPL DENGAN METODE ELIMINASI GAUSS JORDAN ======");
                                    Output.printFile(fileName, GaussJordan.strResultUniqueSol(matrix));
                                    System.out.println("Berhasil create dan write pada file ini.");
                                    System.out.println();
                                } else {
                                    System.out
                                            .println("======= SOLUSI SPL DENGAN METODE ELIMINASI GAUSS JORDAN ======");
                                    GaussJordan.solGaussJordan(matrix);
                                    System.out.println();
                                }
                            }
                            break;

                        case 3:
                            System.out.println("===== METODE MATRIKS BALIKAN =====");
                            if ((MatrixOP.solBanyak(GaussJordan.gaussJordan(matrix))) || InverseSpl.detEqual0(matrix)
                                    || (MatrixOP.getRowEff(matrix) >= MatrixOP.getColEff(matrix))) {
                                System.out.println(
                                        "SPL tidak bisa diselesaikan karena antara determinan bernilai 0 atau tidak berbentuk persegi.");
                                System.out.println();

                                printFile = Output.userPrintFile();
                                System.out.println();
                                if (printFile) {
                                    System.out
                                            .print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                    scan.nextLine();
                                    fileName = scan.nextLine();
                                    Output.delFile(fileName);
                                    Output.printFile(fileName,
                                            "======= SOLUSI SPL DENGAN METODE MATRIKS BALIKAN ======");
                                    Output.printFile(fileName, "Matriks tidak memiliki solusi SPL.");
                                    System.out.println("Berhasil create dan write pada file ini.");
                                    System.out.println();
                                } else {
                                    System.out.println("======= SOLUSI SPL DENGAN METODE MATRIKS BALIKAN ======");
                                    System.out.println("Matriks tidak memiliki solusi SPL.");
                                    System.out.println();
                                }
                            } else {
                                InverseSpl.solInverse(matrix);
                                System.out.println();
                                printFile = Output.userPrintFile();
                                System.out.println();
                                if (printFile) {
                                    System.out
                                            .print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                    scan.nextLine();
                                    fileName = scan.nextLine();
                                    Output.delFile(fileName);
                                    Output.printFile(fileName,
                                            "======= SOLUSI SPL DENGAN METODE MATRIKS BALIKAN ======");
                                    Output.printFile(fileName, InverseSpl.strSol(matrix));
                                    System.out.println("Berhasil create dan write pada file ini.");
                                    System.out.println();
                                } else {
                                    System.out.println("======= SOLUSI SPL DENGAN METODE MATRIKS BALIKAN ======");
                                    InverseSpl.solInverse(matrix);
                                    System.out.println();
                                }
                            }
                            sprog1 = false;
                            break;

                        case 4:
                            System.out.println("===== KAIDAH CRAMER =====");
                            if (MatrixOP.getRowEff(matrix) >= MatrixOP.getColEff(matrix)) {
                                System.out.println(
                                        "Solusi tidak dapat dicari menggunakan metode Cramer karena ukuran matriks A tidak n x n.");
                                printFile = Output.userPrintFile();
                                if (printFile) {
                                    System.out.println();
                                    System.out
                                            .print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                    fileName = scan.nextLine();
                                    Output.delFile(fileName);
                                    Output.printFile(fileName, "======= SOLUSI SPL DENGAN METODE KAIDAH CRAMER ======");
                                    Output.printFile(fileName,
                                            "Solusi tidak dapat dicari menggunakan metode Cramer karena ukuran matriks A tidak n x n.");
                                    System.out.println("Berhasil create dan write pada file ini.");
                                    System.out.println();
                                } else {
                                    System.out.println();
                                    System.out.println("======= SOLUSI SPL DENGAN METODE KAIDAH CRAMER ======");
                                    System.out.println(
                                            "Solusi tidak dapat dicari menggunakan metode Cramer karena ukuran matriks A tidak n x n.");
                                    System.out.println();
                                }
                            } else if (MatrixOP.getColEff(matrix) != MatrixOP.getRowEff(matrix) + 1) {
                                System.out.println(
                                        "Solusi tidak dapat dicari menggunakan metode Cramer karena ukuran matriks A tidak n x n.");
                                printFile = Output.userPrintFile();
                                if (printFile) {
                                    System.out.println();
                                    System.out
                                            .print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                    scan.nextLine();
                                    fileName = scan.nextLine();
                                    Output.delFile(fileName);
                                    Output.printFile(fileName, "======= SOLUSI SPL DENGAN METODE KAIDAH CRAMER ======");
                                    Output.printFile(fileName,
                                            "Solusi tidak dapat dicari menggunakan metode Cramer karena ukuran matriks A tidak n x n.");
                                    System.out.println("Berhasil create dan write pada file ini.");
                                    System.out.println();
                                } else {
                                    System.out.println();
                                    System.out.println("======= SOLUSI SPL DENGAN METODE KAIDAH CRAMER ======");
                                    System.out.println(
                                            "Solusi tidak dapat dicari menggunakan metode Cramer karena ukuran matriks A tidak n x n.");
                                    System.out.println();
                                }
                            } else if (MatrixOP.determinant(Cramer.matriksACramer(matrix)) == 0) {
                                System.out.println(
                                        "Matriks ini tidak dapat diselesaikan dengan metode Cramer karena determinan matriksnya adalah 0.");
                                printFile = Output.userPrintFile();
                                if (printFile) {
                                    System.out.println();
                                    System.out
                                            .print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                    scan.nextLine();
                                    fileName = scan.nextLine();
                                    Output.delFile(fileName);
                                    Output.printFile(fileName, "======= SOLUSI SPL DENGAN METODE KAIDAH CRAMER ======");
                                    Output.printFile(fileName,
                                            "Matriks ini tidak dapat diselesaikan dengan metode Cramer karena determinan matriksnya adalah 0.");
                                    System.out.println("Berhasil create dan write pada file ini.");
                                    System.out.println();
                                } else {
                                    System.out.println();
                                    System.out.println("======= SOLUSI SPL DENGAN METODE KAIDAH CRAMER ======");
                                    System.out.println(
                                            "Matriks ini tidak dapat diselesaikan dengan metode Cramer karena determinan matriksnya adalah 0.");
                                    System.out.println();
                                }
                            } else if (MatrixOP.solTidakAda(matrix)) {
                                System.out.println(
                                        "Solusi tidak ada / matriks ini tidak dapat diselesaikan dengan metode Cramer.");
                                printFile = Output.userPrintFile();
                                if (printFile) {
                                    System.out.println();
                                    System.out
                                            .print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                    scan.nextLine();
                                    fileName = scan.nextLine();
                                    Output.delFile(fileName);
                                    Output.printFile(fileName, "======= SOLUSI SPL DENGAN METODE KAIDAH CRAMER ======");
                                    Output.printFile(fileName,
                                            "Solusi tidak ada / matriks ini tidak dapat diselesaikan dengan metode Cramer.");
                                    System.out.println("Berhasil create dan write pada file ini.");
                                    System.out.println();
                                } else {
                                    System.out.println();
                                    System.out.println("======= SOLUSI SPL DENGAN METODE KAIDAH CRAMER ======");
                                    System.out.println(
                                            "Solusi tidak ada / matriks ini tidak dapat diselesaikan dengan metode Cramer.");
                                    System.out.println();
                                }
                            } else if (MatrixOP.solBanyak(matrix)) {
                                System.out.println("Solusi banyak. Namun tidak dapat dicari dengan metode Cramer.");
                                printFile = Output.userPrintFile();
                                if (printFile) {
                                    System.out.println();
                                    System.out
                                            .print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                    scan.nextLine();
                                    fileName = scan.nextLine();
                                    Output.delFile(fileName);
                                    Output.printFile(fileName, "======= SOLUSI SPL DENGAN METODE KAIDAH CRAMER ======");
                                    Output.printFile(fileName,
                                            "Solusi banyak. Namun tidak dapat dicari dengan metode Cramer.");
                                    System.out.println("Berhasil create dan write pada file ini.");
                                    System.out.println();
                                } else {
                                    System.out.println();
                                    System.out.println("======= SOLUSI SPL DENGAN METODE KAIDAH CRAMER ======");
                                    System.out.println("Solusi banyak. Namun tidak dapat dicari dengan metode Cramer.");
                                    System.out.println();
                                }
                            } else {
                                if (MatrixOP.getRowEff(matrix) < 8) {
                                    Cramer.SPLCramer(matrix);
                                    System.out.println();
                                    printFile = Output.userPrintFile();
                                    if (printFile) {
                                        System.out.println();
                                        System.out.print(
                                                "Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                        scan.nextLine();
                                        fileName = scan.nextLine();
                                        Output.delFile(fileName);
                                        Output.printFile(fileName,
                                                "======= SOLUSI SPL DENGAN METODE KAIDAH CRAMER ======");
                                        Output.printFile(fileName, Cramer.strSol(matrix));
                                        System.out.println("Berhasil create dan write pada file ini.");
                                        System.out.println();
                                    } else {
                                        System.out.println();
                                        System.out.println("======= SOLUSI SPL DENGAN METODE KAIDAH CRAMER ======");
                                        Cramer.SPLCramer(matrix);
                                        System.out.println();
                                    }
                                } else {
                                    Cramer.solCramer(matrix);
                                    System.out.println();
                                    printFile = Output.userPrintFile();
                                    if (printFile) {
                                        System.out.println();
                                        System.out.print(
                                                "Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                        scan.nextLine();
                                        fileName = scan.nextLine();
                                        Output.delFile(fileName);
                                        Output.printFile(fileName,
                                                "======= SOLUSI SPL DENGAN METODE KAIDAH CRAMER ======");
                                        Output.printFile(fileName, Cramer.strSol(matrix));
                                        System.out.println("Berhasil create dan write pada file ini.");
                                        System.out.println();
                                    } else {
                                        System.out.println("======= SOLUSI SPL DENGAN METODE KAIDAH CRAMER ======");
                                        Cramer.solCramer(matrix);
                                        System.out.println();
                                    }
                                }
                            }
                            System.out.println();
                            sprog1 = false;
                            break;

                        default:
                            System.out.println("Input pilihan salah, silakan input ulang.");
                            System.out.println();
                            sprog1 = false;
                            break;
                    }
                    break;

                case 2:
                    sprog2 = true;
                    System.out.println("===== DETERMINAN =====");

                    pilih = MatrixInput.choose();

                    if (pilih == 1) {
                        matrix = MatrixInput.matrixUser();
                    } else {
                        matrix = MatrixInput.matrixFile();
                    }

                    System.out.println();
                    System.out.println("Matrix:");
                    MatrixOutput.printMatrix(matrix);
                    System.out.println();

                    System.out.println("===== PILIHAN METODE/KAIDAH =====");
                    System.out.println("1. Metode eliminasi upper-triangular");
                    System.out.println("2. Metode eliminasi lower-triangular");
                    System.out.println("3. Metode kofaktor");

                    System.out.print("Memilih metode penyelesaian: ");
                    pilih = scan.nextInt();
                    System.out.println();

                    switch (pilih) {
                        case 1:
                            System.out.println("==== METODE ELIMINASI UPPER-TRIANGULAR ====");
                            if (!MatrixOP.isSquare(matrix)) {
                                System.out.println("Matriks tidak memiliki determinan karena tidak berukuran n x n.");
                                System.out.println();
                                printFile = Output.userPrintFile();
                                if (printFile) {
                                    System.out.println();
                                    System.out
                                            .print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                    scan.nextLine();
                                    fileName = scan.nextLine();
                                    Output.delFile(fileName);
                                    Output.printFile(fileName,
                                            "======= HASIL DETERMINAN METODE ELIMINASI UPPER-TRIANGULAR ======");
                                    Output.printFile(fileName,
                                            "Matriks tidak memiliki determinan karena tidak berukuran n x n.");
                                    System.out.println("Berhasil create dan write pada file ini.");
                                    System.out.println();
                                } else {
                                    System.out.println();
                                    System.out.println(
                                            "======= HASIL DETERMINAN METODE ELIMINASI UPPER-TRIANGULAR ======");
                                    if (!MatrixOP.isSquare(matrix)) {
                                        System.out.println(
                                                "Matriks tidak memiliki determinan karena tidak berukuran n x n.");
                                        System.out.println();
                                    }
                                }
                            } else {
                                detU = Triangle.detUpperTriangular(matrix);
                                df = new DecimalFormat("0.000");
                                System.out.println("Determinannya adalah " + df.format(detU));
                                System.out.println();
                                printFile = Output.userPrintFile();
                                if (printFile) {
                                    System.out.println();
                                    System.out
                                            .print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                    scan.nextLine();
                                    fileName = scan.nextLine();
                                    Output.delFile(fileName);
                                    resString = "Determinan : " + Double.toString(detU);
                                    Output.printFile(fileName,
                                            "======= HASIL DETERMINAN METODE ELIMINASI UPPER-TRIANGULAR ======");
                                    Output.printFile(fileName, resString);
                                    System.out.println("Berhasil create dan write pada file ini.");
                                    System.out.println();
                                } else {
                                    System.out.println();
                                    System.out.println(
                                            "======= HASIL DETERMINAN METODE ELIMINASI UPPER-TRIANGULAR ======");
                                    System.out.println("Determinan matriks ini adalah " + detU);
                                    System.out.println();
                                }
                            }
                            sprog2 = false;
                            break;

                        case 2:
                            System.out.println("==== METODE ELIMINASI LOWER-TRIANGULAR ====");
                            if (!MatrixOP.isSquare(matrix)) {
                                System.out.println("Matriks tidak memiliki determinan karena tidak berukuran n x n.");
                                System.out.println();
                                printFile = Output.userPrintFile();
                                if (printFile) {
                                    System.out.println();
                                    System.out
                                            .print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                    scan.nextLine();
                                    fileName = scan.nextLine();
                                    Output.delFile(fileName);
                                    Output.printFile(fileName,
                                            "======= HASIL DETERMINAN METODE ELIMINASI LOWER-TRIANGULAR ======");
                                    Output.printFile(fileName,
                                            "Matriks tidak memiliki determinan karena tidak berukuran n x n.");
                                    System.out.println("Berhasil create dan write pada file ini.");
                                    System.out.println();
                                } else {
                                    System.out.println();
                                    System.out.println(
                                            "======= HASIL DETERMINAN METODE ELIMINASI LOWER-TRIANGULAR ======");
                                    if (!MatrixOP.isSquare(matrix)) {
                                        System.out.println(
                                                "Matriks tidak memiliki determinan karena tidak berukuran n x n.");
                                        System.out.println();
                                    }
                                }
                            } else {
                                detL = Triangle.detLowerTriangular(matrix);
                                // MatrixOutput.printMatrix(upper);
                                // System.out.println();
                                df = new DecimalFormat("0.000");
                                System.out.println("Determinannya adalah " + df.format(detL));
                                System.out.println();
                                printFile = Output.userPrintFile();
                                if (printFile) {
                                    System.out.println();
                                    System.out
                                            .print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                    scan.nextLine();
                                    fileName = scan.nextLine();
                                    Output.delFile(fileName);
                                    resString = "Determinan matriks ini adalah " + Double.toString(detL);
                                    Output.printFile(fileName,
                                            "======= HASIL DETERMINAN METODE ELIMINASI LOWER-TRIANGULAR ======");
                                    Output.printFile(fileName, resString);
                                    System.out.println("Berhasil create dan write pada file ini.");
                                    System.out.println();
                                } else {
                                    System.out.println();
                                    System.out.println(
                                            "======= HASIL DETERMINAN METODE ELIMINASI LOWER-TRIANGULAR ======");
                                    System.out.println("Determinan matriks ini adalah " + detL);
                                    System.out.println();
                                }
                            }
                            sprog2 = false;
                            break;

                        case 3:
                            System.out.println("==== METODE KOFAKTOR ====");
                            if (!MatrixOP.isSquare(matrix)) {
                                System.out.println("Matriks tidak memiliki determinan karena tidak berukuran n x n.");
                                System.out.println();
                                printFile = Output.userPrintFile();
                                if (printFile) {
                                    System.out
                                            .print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                    scan.nextLine();
                                    fileName = scan.nextLine();
                                    Output.delFile(fileName);
                                    Output.printFile(fileName, "======= HASIL DETERMINAN METODE KOFAKTOR ======");
                                    Output.printFile(fileName,
                                            "Matriks tidak memiliki determinan karena tidak berukuran n x n.");
                                    System.out.println("Berhasil create dan write pada file ini.");
                                    System.out.println();
                                } else {
                                    System.out.println();
                                    System.out.println("======= HASIL DETERMINAN METODE KOFAKTOR ======");
                                    if (!MatrixOP.isSquare(matrix)) {
                                        System.out.println(
                                                "Matriks tidak memiliki determinan karena tidak berukuran n x n.");
                                        System.out.println();
                                    }
                                }
                            } else {
                                System.out.println("Pilih indeks baris/kolom yang ingin dihitung: ");
                                int index = scan.nextInt();
                                double[][] cof = Cofactor.createMatrixCofactor(matrix);
                                System.out.println();
                                System.out.println("Matrix cofactornya: ");
                                MatrixOutput.printMatrix(cof);
                                System.out.println();
                                Cofactor.detByCofactor(cof, matrix, index);
                                System.out.println();
                                detC = Cofactor.valDetCofactor(cof, matrix, 0);
                                printFile = Output.userPrintFile();
                                if (printFile) {
                                    System.out.println();
                                    System.out
                                            .print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                    scan.nextLine();
                                    fileName = scan.nextLine();
                                    Output.delFile(fileName);
                                    resString = "Determinan matriks ini adalah " + Double.toString(detC);
                                    Output.printFile(fileName, "======= HASIL DETERMINAN METODE KOFAKTOR ======");
                                    Output.printFile(fileName, resString);
                                    System.out.println("Berhasil create dan write pada file ini.");
                                    System.out.println();
                                } else {
                                    System.out.println();
                                    System.out.println("======= HASIL DETERMINAN METODE KOFAKTOR ======");
                                    System.out.println("Determinan matriks ini adalah " + detC);
                                    System.out.println();
                                }
                            }
                            sprog2 = false;
                            break;

                        default:
                            System.out.println("Input pilihan salah, silakan input ulang.");
                            System.out.println();
                            break;
                    }
                    break;

                case 3:
                    sprog3 = true;
                    System.out.println("===== MATRIKS BALIKAN =====");

                    pilih = MatrixInput.choose();

                    if (pilih == 1) {
                        matrix = MatrixInput.matrixUser();
                    } else {
                        matrix = MatrixInput.matrixFile();
                    }

                    System.out.println();
                    System.out.println("Matrix:");
                    MatrixOutput.printMatrix(matrix);
                    System.out.println();

                    System.out.println("===== PILIH METODE =====");
                    System.out.println("1. Metode eliminasi Gauss-Jordan");
                    System.out.println("2. Metode kofaktor");

                    System.out.print("Memilih metode penyelesaian: ");
                    pilih = scan.nextInt();
                    System.out.println();

                    switch (pilih) {
                        case 1:
                            System.out.println("===== INVERSE METODE ELIMINASI GAUSS-JORDAN =====");
                            // Inverse.matriksInverseGJ(matrix);
                            if (MatrixOP.isSquare(matrix) && MatrixOP.determinant(matrix) != 0) {
                                invGJ = Inverse.balikanGJReturn(matrix);
                                MatrixOutput.printMatrix(invGJ);
                                System.out.println();
                                printFile = Output.userPrintFile();
                                if (printFile) {
                                    System.out
                                            .print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                    scan.nextLine();
                                    fileName = scan.nextLine();
                                    Output.delFile(fileName);
                                    Output.printFile(fileName,
                                            "======= HASIL INVERSE METODE ELIMINASI GAUSS-JORDAN ======");
                                    Output.printMatrixFile(fileName, invGJ);
                                    System.out.println("Berhasil create dan write pada file ini.");
                                    System.out.println();
                                } else {
                                    System.out.println();
                                    System.out.println("======= HASIL INVERSE METODE ELIMINASI GAUSS-JORDAN ======");
                                    Output.printMatrix(invGJ);
                                    System.out.println();
                                }
                            } else if (MatrixOP.determinant(matrix) == 0) {
                                System.out.println("Matriks tidak bisa diinverse karena determinannya adalah 0.");
                                System.out.println();
                                printFile = Output.userPrintFile();
                                if (printFile) {
                                    System.out
                                            .print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                    scan.nextLine();
                                    fileName = scan.nextLine();
                                    Output.delFile(fileName);
                                    Output.printFile(fileName,
                                            "======= HASIL INVERSE METODE ELIMINASI GAUSS-JORDAN ======");
                                    Output.printFile(fileName,
                                            "Matriks tidak bisa diinverse karena determinannya adalah 0.");
                                    System.out.println("Berhasil create dan write pada file ini.");
                                    System.out.println();
                                } else {
                                    System.out.println();
                                    System.out.println("======= HASIL INVERSE METODE ELIMINASI GAUSS-JORDAN ======");
                                    System.out.println("Matriks tidak bisa diinverse karena determinannya adalah 0.");
                                    System.out.println();
                                }
                            } else {
                                System.out.println(
                                        "Matriks tidak bisa diinverse karena karena matriks tidak berukuran n x n.");
                                System.out.println();
                                printFile = Output.userPrintFile();
                                if (printFile) {
                                    System.out
                                            .print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                    scan.nextLine();
                                    fileName = scan.nextLine();
                                    Output.delFile(fileName);
                                    Output.printFile(fileName,
                                            "======= HASIL INVERSE METODE ELIMINASI GAUSS-JORDAN ======");
                                    Output.printFile(fileName,
                                            "Matriks tidak bisa diinverse karena matriks tidak berukuran n x n.");
                                    System.out.println("Berhasil create dan write pada file ini.");
                                    System.out.println();
                                } else {
                                    System.out.println();
                                    System.out.println("======= HASIL INVERSE METODE ELIMINASI GAUSS-JORDAN ======");
                                    System.out.println(
                                            "Matriks tidak bisa diinverse karena karena matriks tidak berukuran n x n.");
                                    System.out.println();
                                }
                            }
                            sprog3 = false;
                            break;

                        case 2:
                            System.out.println("===== INVERSE METODE KOFAKTOR =====");
                            if (MatrixOP.isSquare(matrix) && MatrixOP.determinant(matrix) != 0) {
                                Inverse.matriksInverse(matrix);
                                System.out.println();
                                invCof = Inverse.balikanGJReturn(matrix);
                                printFile = Output.userPrintFile();
                                System.out.println();
                                if (printFile) {
                                    System.out
                                            .print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                    scan.nextLine();
                                    fileName = scan.nextLine();
                                    Output.delFile(fileName);
                                    Output.printFile(fileName, "======= HASIL INVERSE METODE KOFAKTOR ======");
                                    Output.printMatrixFile(fileName, invCof);
                                    System.out.println("Berhasil create dan write pada file ini.");
                                    System.out.println();
                                } else {
                                    System.out.println();
                                    System.out.println("======= HASIL INVERSE METODE KOFAKTOR ======");
                                    Output.printMatrix(invCof);
                                    System.out.println();
                                }
                            } else if (MatrixOP.determinant(matrix) == 0) {
                                System.out.println("Matriks tidak bisa diinverse karena determinannya adalah 0.");
                                System.out.println();
                                printFile = Output.userPrintFile();
                                System.out.println();
                                if (printFile) {
                                    System.out
                                            .print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                    scan.nextLine();
                                    fileName = scan.nextLine();
                                    Output.delFile(fileName);
                                    Output.printFile(fileName, "======= HASIL INVERSE METODE KOFAKTOR ======");
                                    Output.printFile(fileName,
                                            "Matriks tidak bisa diinverse karena determinannya adalah 0.");
                                    System.out.println("Berhasil create dan write pada file ini.");
                                    System.out.println();
                                } else {
                                    System.out.println();
                                    System.out.println("======= HASIL INVERSE METODE KOFAKTOR ======");
                                    System.out.println("Matriks tidak bisa diinverse karena determinannya adalah 0.");
                                    System.out.println();
                                }
                            } else {
                                System.out.println(
                                        "Matriks tidak bisa diinverse karena karena matriks tidak berukuran n x n.");
                                System.out.println();
                                printFile = Output.userPrintFile();
                                System.out.println();
                                if (printFile) {
                                    System.out
                                            .print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                    scan.nextLine();
                                    fileName = scan.nextLine();
                                    Output.delFile(fileName);
                                    Output.printFile(fileName, "======= HASIL INVERSE METODE KOFAKTOR ======");
                                    Output.printFile(fileName,
                                            "Matriks tidak bisa diinverse karena matriks tidak berukuran n x n.");
                                    System.out.println("Berhasil create dan write pada file ini.");
                                    System.out.println();
                                } else {
                                    System.out.println();
                                    System.out.println("======= HASIL INVERSE METODE KOFAKTOR ======");
                                    System.out.println(
                                            "Matriks tidak bisa diinverse karena karena matriks tidak berukuran n x n.");
                                    System.out.println();
                                }
                            }
                            sprog3 = false;
                            break;

                        default:
                            System.out.println("Input pilihan salah, silakan input ulang.");
                            System.out.println();
                            break;
                    }
                    break;

                case 4:
                    System.out.println("===== INTERPOLASI POLINOM =====");
                    pilih = MatrixInput.choose();

                    if (pilih == 1) {
                        System.out.print("Jumlah sampel (n): ");
                        int n = scan.nextInt();
                        double[][] mx = new double[n][2];
                        for (int i = 0; i < MatrixOP.getRowEff(mx); i++) {
                            for (int j = 0; j < MatrixOP.getColEff(mx); j++) {
                                mx[i][j] = scan.nextDouble();
                            }
                        }
                        System.out.println();
                        System.out.println("Matrix:");
                        MatrixOutput.printMatrix(mx);
                        System.out.println();
                        double[][] newMx = InterpolasiPolinom.interpolasiMatrix(mx);
                        System.out.print("x yang ingin dicari nilai taksirannya: ");
                        double x = scan.nextDouble();
                        double sol = InterpolasiPolinom.interpolasiFX(newMx, x);
                        df = new DecimalFormat("0.0000");
                        System.out.println();
                        InterpolasiPolinom.printInterpolasi(newMx);
                        System.out.println();
                        System.out.println("P(" + x + ") = " + df.format(sol));
                        System.out.println();
                        printFile = Output.userPrintFile();
                        if (printFile) {
                            System.out.print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                            fileName = scan.nextLine();
                            Output.delFile(fileName);
                            Output.printFile(fileName, "====== FUNGSI INTERPOLASI POLINOM ======");
                            Output.printFile(fileName, (InterpolasiPolinom.stringprintPol(newMx)));
                            Output.printFile(fileName, "P(" + x + ") = " + df.format(sol));
                            System.out.println("Berhasil create dan write pada file ini.");
                            System.out.println();
                        } else {
                            System.out.println();
                            System.out.println("======= FUNGSI INTERPOLASI POLINOM ======");
                            InterpolasiPolinom.printInterpolasi(newMx);
                            System.out.println();
                            System.out.println("P(" + x + ") = " + df.format(sol));
                            System.out.println();
                        }
                    } else if (pilih == 2) {
                        scan = new Scanner(System.in);
                        System.out.println();
                        System.out.println("Jenis input ingin dari file atau dari user?");
                        System.out.println("Jika dari user silakan ketik 1, jika dari file silakan ketik 2");
                        int jenisInput = scan.nextInt();
                        if (jenisInput == 1) {
                            matrix = MatrixInput.matrixFile();
                            System.out.println("Matrix:");
                            MatrixOutput.printMatrix(matrix);
                            System.out.println();
                            double[][] newMx = InterpolasiPolinom.interpolasiMatrix(matrix);
                            System.out.print("x yang ingin dicari nilai taksirannya: ");
                            double x = scan.nextDouble();
                            double sol = InterpolasiPolinom.interpolasiFX(newMx, x);
                            df = new DecimalFormat("0.0000");
                            System.out.println();
                            InterpolasiPolinom.printInterpolasi(newMx);
                            System.out.println();
                            System.out.println("P(" + x + ") = " + df.format(sol));
                            System.out.println();
                            printFile = Output.userPrintFile();
                            if (printFile) {
                                System.out.print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                scan.nextLine();
                                fileName = scan.nextLine();
                                Output.delFile(fileName);
                                Output.printFile(fileName, "====== FUNGSI INTERPOLASI POLINOM ======");
                                Output.printFile(fileName, (InterpolasiPolinom.stringprintPol(newMx)));
                                Output.printFile(fileName, "P(" + x + ") = " + df.format(sol));
                                System.out.println("Berhasil create dan write pada file ini.");
                                System.out.println();
                            } else {
                                System.out.println();
                                System.out.println("======= FUNGSI INTERPOLASI POLINOM ======");
                                InterpolasiPolinom.printInterpolasi(newMx);
                                System.out.println();
                                System.out.println("P(" + x + ") = " + df.format(sol));
                                System.out.println();
                            }
                        } else if (jenisInput == 2) {
                            System.out.println("Nama file lengkap dengan type file (e.g.: case1a.txt): ");
                            scan.nextLine();
                            String namaFile = scan.nextLine();
                            String pathFile = MatrixInput.getPathInput(namaFile);
                            matrix = MatrixInput.PRBMatrixFile(pathFile);
                            double taksiran = MatrixInput.polinomTaksiranFile(pathFile);
                            System.out.println();
                            System.out.println("Matrix:");
                            MatrixOutput.printMatrix(matrix);
                            System.out.println();
                            System.out.print("Nilai x yang ditaksir: ");
                            System.out.println(taksiran);
                            double[][] newM = InterpolasiPolinom.interpolasiMatrix(matrix);
                            double sol = InterpolasiPolinom.interpolasiFX(newM, taksiran);
                            df = new DecimalFormat("0.0000");
                            System.out.println();
                            InterpolasiPolinom.printInterpolasi(newM);
                            System.out.println();
                            System.out.println("P(" + taksiran + ") = " + df.format(sol));
                            System.out.println();
                            printFile = Output.userPrintFile();
                            if (printFile) {
                                System.out.print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                // scan.nextLine();
                                fileName = scan.nextLine();
                                Output.delFile(fileName);
                                Output.printFile(fileName, "====== FUNGSI INTERPOLASI POLINOM ======");
                                Output.printFile(fileName, (InterpolasiPolinom.stringprintPol(newM)));
                                Output.printFile(fileName, "P(" + taksiran + ") = " + df.format(sol));
                                System.out.println("");
                                System.out.println("Berhasil create dan write pada file ini.");
                                System.out.println();
                            } else {
                                System.out.println();
                                System.out.println("======= FUNGSI INTERPOLASI POLINOM ======");
                                InterpolasiPolinom.printInterpolasi(newM);
                                System.out.println();
                                System.out.println("P(" + taksiran + ") = " + df.format(sol));
                                System.out.println();
                            }
                        }
                    }
                    break;

                case 5:
                    System.out.println("===== INTERPOLASI BICUBIC SPLINE =====");
                    scan = new Scanner(System.in);
                    System.out.println("Nama file lengkap dengan type file (e.g.: case1a.txt): ");
                    String namaFile = scan.nextLine();
                    String pathFile = MatrixInput.getPathInput(namaFile);
                    matrix = MatrixInput.PRBMatrixFile(pathFile);
                    double[][] bic = MatrixInput.convBicubic(matrix);
                    double[][] taksir = MatrixInput.bicubicTaksiranFile(pathFile);
                    System.out.println("Matrix:");
                    MatrixOutput.printMatrix(matrix);
                    System.out.println();
                    System.out.println("Taksiran: ");
                    MatrixOutput.printMatrix(taksir);
                    System.out.println();
                    double[][] bicA = Bicubic.matriksBicubicA(bic);
                    double result = Bicubic.hasilBicubic(bicA, taksir);
                    System.out.println("f(" + taksir[0][0] + " , " + taksir[0][1] + ") = " + result);
                    printFile = Output.userPrintFile();
                    if (printFile) {
                        System.out.print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                        fileName = scan.nextLine();
                        Output.delFile(fileName);
                        Output.printFile(fileName, "====== FUNGSI INTERPOLASI BICUBIC ======");
                        Output.printFile(fileName, "f(" + taksir[0][0] + " , " + taksir[0][1] + ") = " + result);
                        System.out.println("Berhasil create dan write pada file ini.");
                        System.out.println();
                    } else {
                        System.out.println();
                        System.out.println("======= FUNGSI INTERPOLASI BICUBIC ======");
                        System.out.println("f(" + taksir[0][0] + " , " + taksir[0][1] + ") = " + result);
                        System.out.println();
                    }
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
                        System.out.println("Matriks persamaan regresi linearnya adalah");
                        double[][] newM = Regresi.normalEquation(mx);
                        df = new DecimalFormat("0.0000");
                        MatrixOutput.printMatrix(newM);
                        double[][] regSPL = Regresi.regresiMatrix(newM);
                        System.out.println();
                        Regresi.printReg(regSPL);
                        System.out.println();
                        taksir = Regresi.inputTaksiran(regSPL);
                        Regresi.solRegresiFX(regSPL, taksir);
                        System.out.println();
                        printFile = Output.userPrintFile();
                        if (printFile) {
                            System.out.print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                            scan.nextLine();
                            fileName = scan.nextLine();
                            Output.delFile(fileName);
                            Output.printFile(fileName, "====== HASIL REGRESI ======");
                            Output.printFile(fileName, Regresi.stringprintReg(regSPL));
                            double res = Regresi.hasilregresi(regSPL, taksir);
                            Output.printFile(fileName, "Hasil taksirannya adalah " + df.format(res));
                            System.out.println("Berhasil create dan write pada file ini.");
                            System.out.println();
                        } else {
                            System.out.println();
                            System.out.println("======= HASIL REGRESI ======");
                            Regresi.printReg(regSPL);
                            Regresi.solRegresiFX(regSPL, taksir);
                            System.out.println();
                        }

                    } else {
                        scan = new Scanner(System.in);
                        System.out.println("Nama file lengkap dengan type file (e.g.: case1a.txt): ");
                        namaFile = scan.nextLine();
                        pathFile = MatrixInput.getPathInput(namaFile);
                        matrix = MatrixInput.PRBMatrixFile(pathFile);
                        System.out.println();
                        System.out.println("Matrix:");
                        MatrixOutput.printMatrix(matrix);
                        System.out.println();
                        System.out.println("Matriks persamaan regresi linearnya adalah");
                        double[][] newM = Regresi.normalEquation(matrix);
                        MatrixOutput.printMatrix(newM);
                        double[][] regSPL = Regresi.regresiMatrix(newM);
                        System.out.println();
                        Regresi.printReg(regSPL);
                        System.out.println();
                        taksir = MatrixInput.regresiTaksiranFile(pathFile);
                        Regresi.solRegresiFX(regSPL, taksir);
                        System.out.println();
                        df = new DecimalFormat("0.0000");
                        printFile = Output.userPrintFile();
                        if (printFile) {
                            System.out.print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                            fileName = scan.nextLine();
                            Output.delFile(fileName);
                            Output.printFile(fileName, "====== HASIL REGRESI ======");
                            Output.printFile(fileName, Regresi.stringprintReg(regSPL));
                            double res = Regresi.hasilregresi(regSPL, taksir);
                            Output.printFile(fileName, "Hasil taksirannya adalah " + df.format(res));
                            System.out.println("Berhasil create dan write pada file ini.");
                            System.out.println();
                        } else {
                            System.out.println();
                            System.out.println("======= HASIL REGRESI ======");
                            Regresi.printReg(regSPL);
                            System.out.println();
                            Regresi.solRegresiFX(regSPL, taksir);
                            System.out.println();
                        }

                        break;
                    }
            }

        }
    }
}