package ADT_Matrix;
import java.io.*;
import java.util.*;
import menu.*;

public class matrix_input{
    public static int choose(){
        Scanner choice;
        int pilih;
        boolean prog = true;
        choice = new(System.in);

        while(program){
            System.out.println("===== PILIHAN READ MATRIX =====");
            System.out.println("Pilihan untuk to read matrix:");
            System.out.println("1. Input manual");
            System.out.println("2. Baca dari file .txt (Make sure file .txt sudah ada di folder yang sesuai.)");
            System.out.println("Choose how to read matrix (ketik angkanya saja 1 atau 2): ")
            pilih = choice.nextInt();

            if(pilih == 1 || pilih == 2){
                prog = false;
            }
            else{
                System.out.println("Input pilihan salah, silakan input ulang.")
            }
        }
        return pilih;
    }

    public static long[][] matrix_file() throws Exception{
        Scanner scan, isiFile, matrixScan;
        String namaFile, pathFile;
        File file;
        int row, col, i, j;

        // Scan nama file
        scan = new Scanner(System.in);
        System.out.println("Nama file lengkap dengan type file (e.g.: case1a.txt): ");
        namaFile = scan.nextLine();

        // Create + print path file
        pathFile = getPathInput(namaFile);
        System.out.println(pathFile);

        try{
            // Read file
            file = new File(pathFile);
            isiFile = new Scanner(file);
            row = 0; col = 0;
            while(isiFile.hasNextLine()){
                row = (isiFile.nextLine()).split(" ").length;
                col ++;
            }
            isiFile.close();
            
            System.out.println("Jumlah baris: " + row);
            System.out.println("Jumlah kolom: " + col);

            // Read matrix
            long[][] matrix = new long[row][col];
            matrixScan = new Scanner(file);
            while(matrixScan.hasNextLine()){
                for (i = 0; i < m; i++){
                    for (j = 0; j < n; j++){
                        matrix[i][j] = matrixScan.nextLong();
                    }
                }
            }
            matrixScan.close();
            return matrix;
        }

        catch(FileNotFoundException e){
            long[][] matrix = new long[0][0];
            return matrix;
        }
    }

    public static String getPathInput(String namaFile){
        String pathFile;
        String path;

        path = System.getProperty("user.dir");
        if(path.contains("src")){
            path = path.replaceAll("src", "");
            pathFile = path + "test\\" + namaFile;
        }
        else{
            pathFile = path + "\\test\\" + namaFile;
        }

        return pathFile;
    }


    public static long[][] matrix_user(){
        int m, n, i, j;
        Scanner scan = null;
        try {
            // Input m dan m
            scan = new Scanner(System.in);
            System.out.println("Jumlah aris matrix : ");
            m = scan.nextInt();
            System.out.println("Jumlah kolom matrix : ");
            n = scan.nextInt();
  
            // Declare matrix
            long[][] matrix = new long[m][n];
  
            // Input elemen matrix
            System.out.println("Input elemen-elemen matrix");
            for (i = 0; i < m; i++){
                for (j = 0; j < n; j++){
                    matrix[i][j] = scan.nextLong();
                }
            }
            return matrix;
        }
        catch (Exception e) {}
    }
}
