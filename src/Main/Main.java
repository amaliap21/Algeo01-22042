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
            } 
            else if (pilih > 7 || pilih < 1) {
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
                    } else if (pilih == 2){
                        matrix = MatrixInput.matrixFile();
                    } else{
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
                            // double[][] fOBE = Gauss.forwardOBE(matrix);
                            Gauss.xsolGauss(matrix);
                            System.out.println();
                            // Gauss.fileOfResult(matrix);
                            System.out.println();
                            sprog1 = false;
                            break;

                        case 2:
                            System.out.println("===== METODE ELIMINASI GAUSS-JORDAN =====");
                            GaussJordan.matriksGaussJordan(matrix);
                            System.out.println();
                            GaussJordan.solGaussJordan(matrix);
                            System.out.println();
                            sprog1 = false;
                            break;

                        case 3:
                            System.out.println("===== METODE MATRIKS BALIKAN =====");
                            // Inverse.matriksInverseGJ(matrix);
                            // System.out.println();
                            // Inverse.matriksInverse(matrix);
                            // System.out.println();
                            InverseSpl.solInverse(matrix);
                            System.out.println();
                            sprog1 = false;
                            break;

                        case 4:
                            System.out.println("===== KAIDAH CRAMER =====");
                            if(MatrixOP.getRowEff(matrix) >= MatrixOP.getColEff(matrix)){
                                System.out.println("Solusi tidak dapat dicari menggunakan metode Cramer karena ukuran matriks A tidak n x n.");
                            }
                            else if(MatrixOP.getColEff(matrix) != MatrixOP.getRowEff(matrix) + 1){
                                System.out.println("Solusi tidak dapat dicari menggunakan metode Cramer karena ukuran matriks A tidak n x n.");
                            }
                            else if (MatrixOP.solTidakAda(matrix)){
                                System.out.println("Solusi tidak ada.");
                            }
                            else if (MatrixOP.solBanyak(matrix)){
                                System.out.println("Solusi banyak. Namun tidak dapat dicari dengan metode Cramer.");
                            }
                            else{
                                Cramer.solCramer(matrix);
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
                            // double [][] upper = Triangle.upperTriangular(m);
                            if(!MatrixOP.isSquare(matrix)){
                                System.out.println("Matriks tidak memiliki determinan karena tidak berukuran n x n.");
                                System.out.println();
                                printFile = Output.userPrintFile();
                                if(printFile){
                                    System.out.print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                    scan.nextLine();
                                    fileName = scan.nextLine();
                                    Output.delFile(fileName);
                                    Output.printFile(fileName, "======= HASIL DETERMINAN METODE ELIMINASI UPPER-TRIANGULAR ======");
                                    Output.printFile(fileName, "Matriks tidak memiliki determinan karena tidak berukuran n x n.");
                                    System.out.println("Berhasil create dan write pada file ini.");
                                }
                                else{
                                    System.out.println("======= HASIL DETERMINAN METODE ELIMINASI UPPER-TRIANGULAR ======");
                                    if(!MatrixOP.isSquare(matrix)){
                                        System.out.println("Matriks tidak memiliki determinan karena tidak berukuran n x n.");
                                    }
                                }
                            }
                            else{
                                detU = Triangle.detUpperTriangular(matrix);
                                // MatrixOutput.printMatrix(upper);
                                System.out.println();
                                df = new DecimalFormat("0.000");
                                System.out.println("Determinannya adalah " + df.format(detU));
                                System.out.println();
                                printFile = Output.userPrintFile();
                                if(printFile){
                                    System.out.print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                    scan.nextLine();
                                    fileName = scan.nextLine();
                                    Output.delFile(fileName);
                                    resString = "Determinan : " + Double.toString(detU);
                                    Output.printFile(fileName, "======= HASIL DETERMINAN METODE ELIMINASI UPPER-TRIANGULAR ======");
                                    Output.printFile(fileName, resString);
                                    System.out.println("Berhasil create dan write pada file ini.");
                                }
                                else{
                                    System.out.println("======= HASIL DETERMINAN METODE ELIMINASI UPPER-TRIANGULAR ======");
                                    System.out.println("Determinan : "+ detU);
                                    }
                                }
                                sprog2 = false;
                                break;

                        case 2:
                            System.out.println("==== METODE ELIMINASI LOWER-TRIANGULAR ====");
                            detL = Triangle.detLowerTriangular(matrix);
                            // MatrixOutput.printMatrix(upper);
                            // System.out.println();
                            df = new DecimalFormat("0.000");
                            System.out.println("Determinannya adalah " + df.format(detL));
                            System.out.println();
                            printFile = Output.userPrintFile();
                            if(printFile){
                                System.out.print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                scan.nextLine();
                                fileName = scan.nextLine();
                                Output.delFile(fileName);
                                resString = "Determinan : " + Double.toString(detL);
                                Output.printFile(fileName, "======= HASIL DETERMINAN METODE KOFAKTOR ======");
                                Output.printFile(fileName, resString);
                                System.out.println("Berhasil create dan write pada file ini.");
                            }
                            else{
                                System.out.println("======= HASIL DETERMINAN METODE KOFAKTOR ======");
                                System.out.println("Determinan : "+ detL);
                            }
                            sprog2 = false;
                            break;

                        case 3:
                            System.out.println("==== METODE KOFAKTOR ====");
                            System.out.println("Pilih indeks baris/kolom yang ingin dihitung: ");
                            int index = scan.nextInt();
                            double[][] cof = Cofactor.createMatrixCofactor(matrix);
                            System.out.println();
                            System.out.println("Matrix cofactornya: ");
                            MatrixOutput.printMatrix(cof);
                            System.out.println();
                            Cofactor.detByCofactor(cof, matrix, index);
                            // double detC = Cofactor.valDetCofactor(cof, matrix, 2);
                            // System.out.println("Determinan matriks ini adalah " + detC);
                            System.out.println();
                            // double detC = Cofactor.valDetCofactor(cof, matrix, 0); 
                            // Output.determinanToFile(matrix, detC);
                            detC = Cofactor.valDetCofactor(cof, matrix, 0);
                            printFile = Output.userPrintFile();
                            if(printFile){
                                System.out.print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                scan.nextLine();
                                fileName = scan.nextLine();
                                Output.delFile(fileName);
                                resString = "Determinan : " + Double.toString(detC);
                                Output.printFile(fileName, "======= HASIL DETERMINAN METODE KOFAKTOR ======");
                                Output.printFile(fileName, resString);
                            }
                            else{
                                System.out.println("======= HASIL DETERMINAN METODE KOFAKTOR ======");
                                System.out.println("Determinan : "+ detC);
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
                            System.out.println("===== METODE ELIMINASI GAUSS-JORDAN =====");
                            Inverse.matriksInverseGJ(matrix);
                            System.out.println();
                            invGJ = Inverse.balikanGJReturn(matrix);
                            printFile = Output.userPrintFile();
                            if(printFile){
                                System.out.print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                scan.nextLine();
                                fileName = scan.nextLine();
                                Output.delFile(fileName);
                                Output.printMatrixFile(fileName, invGJ);
                                Output.printFile(fileName, "======= HASIL INVERSE METODE ELIMINASI GAUSS-JORDAN ======");
                            }
                            // else{
                            //     System.out.println("======= HASIL INVERSE METODE ELIMINASI GAUSS-JORDAN ======");
                            //     Output.printMatrix(invGJ);;
                            // }
                            sprog3 = false;
                            break;

                        case 2:
                            System.out.println("===== METODE KOFAKTOR =====");
                            Inverse.matriksInverse(matrix);
                            System.out.println();
                            invCof = Inverse.balikanGJReturn(matrix);
                            printFile = Output.userPrintFile();
                            if(printFile){
                                System.out.print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                                scan.nextLine();
                                fileName = scan.nextLine();
                                Output.delFile(fileName);
                                Output.printMatrixFile(fileName, invCof);
                                Output.printFile(fileName, "======= HASIL INVERSE METODE ELIMINASI GAUSS-JORDAN ======");
                            }
                            // else{
                            //     System.out.println("======= HASIL INVERSE METODE ELIMINASI GAUSS-JORDAN ======");
                            //     Output.printMatrix(invCof);;
                            // }
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
                        for(int i = 0; i < MatrixOP.getRowEff(mx); i++){
                            for(int j = 0; j < MatrixOP.getColEff(mx); j++){
                                mx[i][j] = scan.nextDouble();
                            }
                        }
                        System.out.println();
                        System.out.println("Matrix:");
                        MatrixOutput.printMatrix(mx);
                        System.out.println();
                        double [][] newMx = InterpolasiPolinom.interpolasiMatrix(mx);
                        // System.out.println();
                        // MatrixOutput.printMatrix(newMx);
                        System.out.println();
                        System.out.print("x yang ingin dicari nilai taksirannya: ");
                        double x = scan.nextDouble();
                        double sol = InterpolasiPolinom.interpolasiFX(newMx, x);
                        df = new DecimalFormat("0.0000");
                        System.out.println();
                        InterpolasiPolinom.printInterpolasi(newMx);
                        System.out.println();
                        System.out.println("f(" + x + ") = " + df.format(sol));
                        System.out.println();
                        // invGJ = Inverse.balikanGJReturn(matrix);
                        printFile = Output.userPrintFile();
                        if(printFile){
                            System.out.print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                            scan.nextLine();
                            fileName = scan.nextLine();
                            Output.delFile(fileName);
                            Output.printFile(fileName, "====== FUNGSI INTERPOLASI POLINOM ======");
                            Output.printFile(fileName, "f(" + x + ") = " + df.format(sol));
                        }
                        else{
                            System.out.println();
                            System.out.println("======= FUNGSI INTERPOLASI POLINOM ======");
                            System.out.println("f(" + x + ") = " + df.format(sol));
                        }
                    } 
                    else {
                        scan = new Scanner(System.in);
                        System.out.println("Nama file lengkap dengan type file (e.g.: case1a.txt): ");
                        String namaFile = scan.nextLine();
                        String pathFile = MatrixInput.getPathInput(namaFile);
                        matrix = MatrixInput.PRBMatrixFile(pathFile);
                        double taksiran = MatrixInput.polinomTaksiranFile(pathFile);
                        System.out.println();
                        System.out.println("Matrix:");
                        MatrixOutput.printMatrix(matrix);
                        System.out.println();
                        System.out.println(taksiran);
                        // System.out.print("x yang ingin dicari nilai taksirannya: ");
                        // double x = scan.nextDouble();
                        double sol = InterpolasiPolinom.interpolasiFX(InterpolasiPolinom.interpolasiMatrix(matrix), taksiran);
                        df = new DecimalFormat("0.0000");
                        System.out.println();
                        InterpolasiPolinom.printInterpolasi(matrix);
                        System.out.println();
                        System.out.println("f(" + taksiran + ") = " + df.format(sol));
                        System.out.println();
                        printFile = Output.userPrintFile();
                        if(printFile){
                            System.out.print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                            scan.nextLine();
                            fileName = scan.nextLine();
                            Output.delFile(fileName);
                            Output.printFile(fileName, "====== FUNGSI INTERPOLASI POLINOM ======");
                            Output.printFile(fileName, "f(" + taksiran + ") = " + df.format(sol));
                        }
                        else{
                            System.out.println();
                            System.out.println("======= FUNGSI INTERPOLASI POLINOM ======");
                            System.out.println("f(" + taksiran + ") = " + df.format(sol));
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
                    System.out.println("Bic:");
                    MatrixOutput.printMatrix(bic);
                    double[][] bicA = Bicubic.matriksBicubicA(bic);
                    System.out.println();
                    System.out.println("Bic A:");
                    MatrixOutput.printMatrix(bicA);
                    System.out.println();
                    double result = Bicubic.hasilBicubic(bicA, taksir);
                    System.out.println("f(" + taksir[0][0] + " , " + taksir[0][1] + ") = " + result);
                    printFile = Output.userPrintFile();
                    if(printFile){
                        System.out.print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                        scan.nextLine();
                        fileName = scan.nextLine();
                        Output.delFile(fileName);
                        Output.printFile(fileName, "====== FUNGSI INTERPOLASI BICUBIC ======");
                        Output.printFile(fileName,"f(" + taksir[0][0] + " , " + taksir[0][1] + ") = " + result);
                    }
                    else{
                        System.out.println();
                        System.out.println("======= FUNGSI INTERPOLASI BICUBIC ======");
                        System.out.println("f(" + taksir[0][0] + " , " + taksir[0][1] + ") = " + result);
                    }
                    break;

                case 6:
                    pilih = MatrixInput.choose();
            
                    if (pilih == 1) {
                        System.out.print("Jumlah peubah (n): ");
                        int n = scan.nextInt();
                        System.out.print("Jumlah sampel (m): ");
                        int m = scan.nextInt();
                        double[][] mx = new double[m][n+1];
                        for(int i = 0; i < m; i++){
                            for(int j = 0; j < n+1; j++){
                                mx[i][j] = scan.nextDouble();
                            }
                        }
                        System.out.println();
                        System.out.println("Matrix:");
                        MatrixOutput.printMatrix(mx);
                        System.out.println();
                        System.out.println("Matriks persamaan regresi linearnya adalah");
                        double[][] newM = Regresi.normalEquation(mx);
                        // System.out.print("x yang ingin dicari nilai taksirannya: ");
                        // double x = scan.nextDouble();
                        // double sol = Regresi.regresiFX(newM, x);
                        // DecimalFormat df = new DecimalFormat("0.0000");
                        // System.out.println();
                        // System.out.println("f(" + x + ") = " + df.format(sol));
                        // Regresi.printRegresi(newM);
                        MatrixOutput.printMatrix(newM);
                        double[][] regSPL = Regresi.regresiMatrix(newM);
                        System.out.println();
                        // MatrixOutput.printMatrix(regSPL);
                        // System.out.println();
                        Regresi.printReg(regSPL);
                        System.out.println();
                        taksir = Regresi.inputTaksiran(regSPL);
                        /*double sol = */ Regresi.solRegresiFX(regSPL, taksir);
                        System.out.println();
                        printFile = Output.userPrintFile();
                        if(printFile){
                            System.out.print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                            scan.nextLine();
                            fileName = scan.nextLine();
                            Output.delFile(fileName);
                            Output.printFile(fileName, "====== HASIL REGRESI ======");
                            Output.printFile(fileName, Regresi.stringprintReg(regSPL));
                            Output.printFile(fileName, "Hasil taksirannya adalah " + Regresi.hasilregresi(regSPL, taksir));
                        }
                        else{
                            System.out.println();
                            System.out.println("======= HASIL REGRESI ======");
                            Regresi.printReg(regSPL);
                            // taksir = Regresi.inputTaksiran(regSPL);
                            Regresi.solRegresiFX(regSPL, taksir);
                            System.out.println();
                        }

                        // Regresi.solRegresiFX(regSPL);
                        // double[] x = Regresi.inputTaksiran(newM);
                        // System.out.println(x);
                        // int count = MatrixOP.getRowEff(regSPL)-1;
                        // double sol = regSPL[0][0];
                        // int i;
                        // double x;
                        // for(i = 0; i < count; i++){
                        //     System.out.print("x" + (i+1) + ": ");
                        //     x = scan.nextDouble();
                        //     sol += regSPL[i+1][0]*x;
                        // }
                        // System.out.println("Hasil taksiran regresi ini adalah " + sol);
                    }
                    else {
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
                        // System.out.print("x yang ingin dicari nilai taksirannya: ");
                        // double x = scan.nextDouble();
                        // double sol = Regresi.regresiFX(newM, x);
                        // DecimalFormat df = new DecimalFormat("0.0000");
                        // System.out.println();
                        // System.out.println("f(" + x + ") = " + df.format(sol));
                        double[][] regSPL = Regresi.regresiMatrix(newM);
                        // System.out.println();
                        // MatrixOutput.printMatrix(regSPL);
                        System.out.println();
                        Regresi.printReg(regSPL);
                        System.out.println();
                        taksir = MatrixInput.regresiTaksiranFile(pathFile);
                        Regresi.solRegresiFX(regSPL, taksir);
                        System.out.println();
                        printFile = Output.userPrintFile();
                        if(printFile){
                            System.out.print("Masukkan nama file lengkap dengan format txt (e.g.: SPL1a.txt): ");
                            // scan.nextLine();
                            fileName = scan.nextLine();
                            Output.delFile(fileName);
                            Output.printFile(fileName, "====== HASIL REGRESI ======");
                            Output.printFile(fileName, Regresi.stringprintReg(regSPL));
                            Output.printFile(fileName, "Hasil taksirannya adalah " + Regresi.hasilregresi(regSPL, taksir));
                        }
                        else{
                            System.out.println();
                            System.out.println("======= HASIL REGRESI ======");
                            Regresi.printReg(regSPL);
                            // taksir = Regresi.inputTaksiran(regSPL);
                            System.out.println();
                            Regresi.solRegresiFX(regSPL, taksir);
                            System.out.println();
                        }
                        // double[][] x = Regresi.inputTaksiran(newM);
                        // // MatrixOutput.printMatrix(x);
                        // // System.out.println();
                        // double sol = Regresi.solRegresiFX(newM, x);
                        // System.out.println("Hasil taksiran regresi ini adalah " + sol);
                    //     int count = MatrixOP.getRowEff(regSPL)-1;
                    //     double sol = regSPL[0][0];
                    //     int i;
                    //     double x;
                    //     for(i = 0; i < count; i++){
                    //         System.out.print("x" + (i+1) + ": ");
                    //         x = scan.nextDouble();
                    //         sol += regSPL[i+1][0]*x;
                    //     }
                    //     System.out.println("Hasilnya adalah " + sol);
                    // }

                    break;
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
}

/*
 * case 2:
 * System.out.println("===== DETERMINAN =====");
 * pilih = MatrixInput.choose();
 * 
 * if (pilih == 1) {
 * m = MatrixInput.matrixUser();
 * } else {
 * m = MatrixInput.matrixFile();
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
// m = MatrixInput.matrixUser();
// } else {
// m = MatrixInput.matrixFile();
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