package ADT_Matrix;

import java.io.*;
import java.util.*;
import menu.*;

public class MatrixOutput{
    public static void long[][] matrix(){
        // Iterate through rows
        for (int i = 0; i < matrix.length; i++) {
            // Iterate through columns within each row
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " "); // Print each element
            }
            System.out.println(); // Move to the next row
        }
    }
}
