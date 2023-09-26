package Function;

import java.io.*;
import java.util.*;
import ADT_Matrix.*;

public class Inverse {
    public static double[][] inverseMatriks(double[][] m) {
        // buat inverse matriks dengan menggunakan
        if (MatrixOP.determinant(m) == 0) {
            System.out.println("Determinan matriks 0, tidak bisa dibalikkan");
        }

        return (MatrixOP.multiplyByConst(Cofactor.adj(m), (1 / MatrixOP.determinant(m))));
    }
}