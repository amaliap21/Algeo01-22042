package Function;
import ADT_Matrix.*;
import java.util.*;
import java.io.*;
import java.text.*;

public class Output {
    public static Scanner scan = new Scanner(System.in);
    public static File file;
    public static String fileName;

    public static void createFile(){
        try{
            fileName = scan.nextLine();
            file = new File(fileName);
            if(file.createNewFile()){
                System.out.println("File berhasil dibuat: " + file.getName());
            }
            else{
                System.out.println("File already exists.");
            }
        }
        catch(IOException e){
            System.err.println("Error creating the file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String createPath(String fileName){
        String pathFile;
        String path;

        path = System.getProperty("user.dir");
        // dir = dir.substring(dir.lastIndexOf("\\")+1);
        // String directory;
        // if(dir.equals("bin")){
        //     directory = "..\\test\\result\\";
        // }
        // else{
        //     directory = "test\\result\\";
        // }   
        if (path.contains("src")) {
            path = path.replaceAll("src", "");
            pathFile = path + "test\\result\\" + fileName;
        } else {
            pathFile = path + "\\test\\result\\" + fileName;
        }
        return pathFile;
    }

    public static String matrixToStr(double[][] m){
        String str = "";
        int i, j;
        for(i = 0; i < MatrixOP.getRowEff(m); i++){
            for(j = 0; j < MatrixOP.getColEff(m); j++){
                if(j == MatrixOP.getColEff(m)){
                    str += m[i][j];
                }
                else{
                    str += m[i][j] + " ";
                }
            }
            str += "\n";
        }
        return str;
    }

    public static void determinanToFile(double[][] m, double det){
        try{
            System.out.println("Masukkan nama file baru dengan type txt (e.g.: SPL1a.txt): ");
            String fileName = scan.nextLine();
            BufferedWriter wr = new BufferedWriter(new FileWriter(createPath(fileName)));
            wr.write("Determinan matriks\n");
            wr.write(matrixToStr(m));
            wr.write("\n");
            wr.write("adalah " + det);
            wr.close();
            System.out.println("Berhasil menulis pada file ini.");
        }
        catch(IOException e){
            System.err.println("Error writing to the file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void delFile(String fileName){
        String path = System.getProperty("user.dir");
        path = path.replaceAll("src","");
        String filePath = path + "test\\output\\"+ fileName;

        File file = new File(filePath);

        if (file.exists()){
            file.delete();
        }
    }

    public static void printFile(String fileName, String str){

        // Menentukan filePath tempat file output disimpan
        String filePath = createPath(fileName);
        // System.out.println(filePath);

        File file = new File(filePath);

        try {
            
            if(!file.exists()){
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file, true);
            BufferedWriter buffWriter = new BufferedWriter(writer);
            PrintWriter wr = new PrintWriter(buffWriter);

            wr.println(str);

            wr.close();
            buffWriter.close();
            writer.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static boolean userPrintFile(){
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
            if (userInput == 1){
                text = false;
                program = false;
            } 
            else if (userInput == 2){
                text = true;
                program = false;
            }
            else {
                System.out.println("Input "+ userInput +" tidak valid. Silahkan masukan input yang valid.");
            }

        }
        return text;
    }

    public static void printMatrixFile(String fileName, double[][] m){
        // Menentukan filepath tempat file output disimpan
        String filePath = createPath(fileName);

        File file = new File(filePath);

        if (file.exists()){
            file.delete();
        }

        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            BufferedWriter buffWriter = new BufferedWriter(writer);

            for (int i = 0; i < MatrixOP.getRowEff(m); i++){
                for (int j = 0; j < MatrixOP.getColEff(m); j++){
                    if ( j == MatrixOP.getColEff(m)-1) {
                        buffWriter.write(String.format("%.3f", MatrixOP.getElmt(m, i, j)));
                    } else {
                        buffWriter.write(String.format("%.3f", MatrixOP.getElmt(m, i, j)));
                        buffWriter.write(" ");
                    }
                }
                if( i <= MatrixOP.getRowEff(m) - 1){
                    buffWriter.newLine();
                }
            }
            buffWriter.close();
            writer.close();
        } 
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void printMatrix(double[][] m){
        for (int i = 0; i <MatrixOP.getRowEff(m); i++){
            System.out.print("[");
            for (int j = 0; j < MatrixOP.getColEff(m); j++){
                if ( j == MatrixOP.getColEff(m)-1) {
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