package ADT_Matrix;

// import ADT
import java.util.Scanner;

// Print matrix using displayMatrix() from ADT_Matrix
public class App {
    public static void main(String[] args) throws Exception {
        double[][] m1 = new double[0][0];
        double[][] m2 = new double[0][0];
        double[][] m3 = new double[0][0];

        int pilih;
        Scanner scan;
        boolean program = true;

        while (program) {
            System.out.println("===== PILIHAN MENU =====");
            System.out.println("Pilihan untuk menu:");
            System.out.println("1. Penjumlahan");
            System.out.println("2. Pengurangan");
            System.out.println("3. Perkalian");
            System.out.println("4. Determinan");
            System.out.println("5. Invers");
            System.out.println("6. Exit");
            System.out.println("Choose menu (ketik angkanya saja 1, 2, 3, 4, 5, atau 6): ");
            scan = new Scanner(System.in);
            pilih = scan.nextInt();

            switch (pilih) {
                case 1:
                    System.out.println("===== PENJUMLAHAN MATRIX =====");
                    System.out.println("Matrix 1");
                    m1 = MatrixInput.matrix_user();
                    System.out.println("Matrix 2");
                    m2 = MatrixInput.matrix_user();
                    m3 = MatrixOP.addMatrix(m1, m2);
                    MatrixOP.displayMatrix(m3);
                    break;
                case 2:
                    System.out.println("===== PENGURANGAN MATRIX =====");
                    System.out.println("Matrix 1");
                    m1 = MatrixInput.matrix_user();
                    System.out.println("Matrix 2");
                    m2 = MatrixInput.matrix_user();
                    m3 = MatrixOP.subtractMatrix(m1, m2);
                    MatrixOP.displayMatrix(m3);
                    break;
                case 3:
                    System.out.println("===== PERKALIAN MATRIX =====");
                    System.out.println("Matrix 1");
                    m1 = MatrixInput.matrix_user();
                    System.out.println("Matrix 2");
                    m2 = MatrixInput.matrix_user();
                    m3 = MatrixOP.multiplyMatrix(m1, m2);
                    MatrixOP.displayMatrix(m3);
                    break;
                case 4:
                    System.out.println("===== DETERMINAN MATRIX =====");
                    System.out.println("Matrix 1");
                    m1 = MatrixInput.matrix_user();
                    System.out.println("Determinan dari matrix 1 adalah: " + MatrixOP.determinant(m1));
                    break;
                case 5:
                    System.out.println

                    ("===== INVERS MATRIX =====");
                    System.out.println("Matrix 1");
                    m1 = MatrixInput.matrix_user();
                    MatrixOP.pNegation(m1);
                    MatrixOP.displayMatrix(m3);
                    break;
                case 6:
                    program = false;
                    break;
                default:
                    System.out.println("Input pilihan salah, silakan input ulang.");
                    break;

            }
        }
    }
}