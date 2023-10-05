package Function;

import ADT_Matrix.*;
import java.util.*;
import java.io.*;
import java.text.*;

public class Output {
    public static Scanner scan = new Scanner(System.in);
    public static File file;
    public static String fileName;

    public static String createPath(String fileName) {
        String pathFile;
        String path;

        path = System.getProperty("user.dir");
        if (path.contains("bin")) {
            path = path.replaceAll("bin", "");
            pathFile = path + "test\\result\\" + fileName;
        } else {
            pathFile = path + "\\test\\result\\" + fileName;
        }
        return pathFile;
    }

    public static void delFile(String fileName) {
        String path = System.getProperty("user.dir");
        path = path.replaceAll("bin", "");
        String filePath = path + "test\\result\\" + fileName;

        File file = new File(filePath);

        if (file.exists()) {
            file.delete();
        }
    }

    public static void printFile(String fileName, String str) {

        // Menentukan filePath tempat file output disimpan
        String filePath = createPath(fileName);
        // System.out.println(filePath);

        File file = new File(filePath);

        try {

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file, true);
            BufferedWriter buffWriter = new BufferedWriter(writer);
            PrintWriter wr = new PrintWriter(buffWriter);

            wr.println(str);

            wr.close();
            buffWriter.close();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean userPrintFile() {
        boolean program = true;
        boolean text = false;
        int userInput;

        while (program) {
            System.out.println("======  SAVE FILE  ======");
            System.out.println("Apakah Anda ingin menyimpan solusi ini ke dalam suatu file?");
            System.out.println("1. Tidak");
            System.out.println("2. Ya");
            System.out.print("Masukkan angkanya saja (1-2): ");

            userInput = scan.nextInt();
            System.out.println();

            if (userInput == 1) {
                text = false;
                program = false;
            } else if (userInput == 2) {
                text = true;
                program = false;
            } else {
                System.out.println("Input " + userInput + " tidak valid. Silahkan masukan input yang valid.");
            }

        }
        return text;
    }

    public static void printMatrixFile(String fileName, double[][] m) {
        // Menentukan filepath tempat file output disimpan
        String filePath = createPath(fileName);

        File file = new File(filePath);

        if (file.exists()) {
            file.delete();
        }

        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            BufferedWriter buffWriter = new BufferedWriter(writer);

            for (int i = 0; i < MatrixOP.getRowEff(m); i++) {
                for (int j = 0; j < MatrixOP.getColEff(m); j++) {
                    if (j == MatrixOP.getColEff(m) - 1) {
                        buffWriter.write(String.format("%.3f", MatrixOP.getElmt(m, i, j)));
                    } else {
                        buffWriter.write(String.format("%.3f", MatrixOP.getElmt(m, i, j)));
                        buffWriter.write(" ");
                    }
                }
                if (i <= MatrixOP.getRowEff(m) - 1) {
                    buffWriter.newLine();
                }
            }
            buffWriter.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printMatrix(double[][] m) {
        for (int i = 0; i < MatrixOP.getRowEff(m); i++) {
            System.out.print("[");
            for (int j = 0; j < MatrixOP.getColEff(m); j++) {
                if (j == MatrixOP.getColEff(m) - 1) {
                    System.out.printf("%.3f", MatrixOP.getElmt(m, i, j));
                } else {
                    System.out.printf("%.3f", MatrixOP.getElmt(m, i, j));
                    System.out.print(",");
                }
            }
            System.out.println("]");
        }
    }
}