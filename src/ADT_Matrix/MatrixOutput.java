package ADT_Matrix;

public class MatrixOutput{
    public static void printMatrix (double[][] matrix){
        int i,j ;
        for (i = 0; i < matrix.length; i++) {
            for (j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " "); // Print each element
            }
            System.out.println();
        }
    }

    public static void displayMatrix(double[][] m3) {
    }
}
