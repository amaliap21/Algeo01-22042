package Function;
import ADT_Matrix.*;

import java.util.Scanner;

public class Gauss {
    public static void main(String[] args) {
        int baris, kolom;

        Scanner scanner = new Scanner(System.in);
        System.out.println("baris = ");
        baris = scanner.nextInt();

        System.out.println("kolom = ");
        kolom = scanner.nextInt();  

        float[][] matrix = new float[baris][kolom];

        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[i].length; j++){
                System.out.print("matrix["+i+"]["+j+"] = ");
                matrix[i][j] = scanner.nextInt();
            }
        }

        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[i].length; j++){
                System.out.print(matrix[i][j]);
                if(j!=kolom-1){
                    System.out.print(" ");
                }
            } System.out.print("\n");
        }
    
    // *** MULAI OPERASI BARIS ELEMENTER ***

        float ratio1 = matrix[0][0];
        for (int i=0; i<matrix[0].length; i++){
            matrix[0][i] = matrix[0][i]/ratio1;
        }


    for(int a=1; a<matrix.length; a++){   
        for(int i=a; i<matrix.length; i++){
            if (matrix[i][a-1] != 0){
                float ratio = matrix[i][a-1]/matrix[a-1][a-1];
                for(int j=0; j<matrix[i].length; j++){
                    matrix[i][j]  = matrix[i][j]- (ratio*matrix[a-1][j]);
                }
            }
          
        }
    }

        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[i].length; j++){
                System.out.print(matrix[i][j]);
                if(j!=kolom-1){
                    System.out.print(" ");
                }
            } System.out.print("\n");
        }
    }

}
