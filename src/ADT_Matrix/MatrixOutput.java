package ADT_Matrix;
public class MatrixOutput{
    public static String printMatrix (double[][] matrix){
        int i,j ;
        String str = "";
        for (i = 0; i < matrix.length; i++) {
            for (j = 0; j < matrix[0].length; j++) {
                str = str + matrix[i][j] + " "; // Print each element
            }
            str = str + "\n";
        }
        return str;
    }

    public static void displayMatrix(double[][] m3) {
    }
}
