package Function;

import java.text.DecimalFormat;
import ADT_Matrix.*;

public class Cramer {
    public static double[][] matriksACramer(double[][] m) {
        int rowA = MatrixOP.getRowEff(m);
        int colA = rowA;

        double[][] matriksA = new double[rowA][colA];

        for (int i = 0; i < rowA; i++) {
            for (int j = 0; j < colA; j++) {
                matriksA[i][j] = m[i][j];
            }
        }
        return matriksA;
    }

    public static double[][] matriksBCramer(double[][] m) {
        int rowA = MatrixOP.getRowEff(m);
        int colA = MatrixOP.getColEff(m);

        double[][] matriksB = new double[rowA][1];

        for (int i = 0; i < rowA; i++) {
            matriksB[i][0] = m[i][colA - 1];
        }
        return matriksB;
    }

    public static double[][] matriksXCramer(double[][] matriksACramer, double[][] matriksBCramer) {
        int rowA = MatrixOP.getRowEff(matriksACramer);
        int colA = MatrixOP.getColEff(matriksACramer);

        double[][] matriksX = new double[rowA][1];

        double detA = Triangle.detUpperTriangular(matriksACramer);
        double detX;

        for (int i = 0; i < rowA; i++) {
            double[][] matriksA1 = new double[rowA][colA];
            for (int j = 0; j < rowA; j++) {
                for (int k = 0; k < colA; k++) {
                    if (k == i) {
                        matriksA1[j][k] = matriksBCramer[j][0];
                    } else {
                        matriksA1[j][k] = matriksACramer[j][k];
                    }
                }
            }
            detX = Triangle.detUpperTriangular(matriksA1);
            matriksX[i][0] = detX / detA;
        }
        return matriksX;
    }

    public static void solCramer(double[][] m) {
        double[][] solusi = matriksXCramer(matriksACramer(m), matriksBCramer(m));

        System.out.println("Solusi SPL:");
        for (int i = 0; i < MatrixOP.getRowEff(m); i++) {
            System.out.print("x" + (i + 1) + " = ");
            DecimalFormat df = new DecimalFormat("0.000");
            System.out.println(df.format(solusi[i][0]));
        }
    }
}