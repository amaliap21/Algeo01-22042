package ADT_Matrix;

public class MatrixOP {
    // final static double MARK = -999;

    // public static void createMatrix(int row, int col){
    //     int i, j;
    //     double[][] m = new double[row][col];
    //     for(i = 0; i < row; i++){
    //         for(j = 0; j < col; j++){
    //             m[i][j] = MARK;
    //         }
    //     }
    // }
    public static int getRowEff(double[][] matrix) {
        return matrix.length;
    }

    public static int getColEff(double[][] matrix) {
        return matrix[0].length;
    }

    public static double getElmt(double[][] matrix, int i, int j) {
        return matrix[i][j];
    }

    public static int getLastIdxRow(double[][] matrix) {
        return getRowEff(matrix) - 1;
    }

    public static int getLastIdxCol(double[][] matrix) {
        return getColEff(matrix) - 1;
    } 

    public static boolean isIdxEff(double[][] matrix, int i, int j) {
        return ((i >= 0 && i <= getLastIdxRow(matrix)) && (j >= 0 && j <= getLastIdxCol(matrix)));
    }

    public static double getElmtDiagonal(double[][] matrix, int i) {
        return getElmt(matrix, i, i);
    }

    public static void displayMatrix(double[][] matrix) {
        int i, j;
        for (i = 0; i < getRowEff(matrix); i++) {
            for (j = 0; j < getColEff(matrix); j++) {
                if (j == getLastIdxCol(matrix)) {
                    System.out.println(getElmt(matrix, i, j));
                } else {
                    System.out.print(getElmt(matrix, i, j) + " ");
                }
            }
        }
    }

    public static double[][] addMatrix(double[][] m1, double[][] m2) {
        double[][] m3 = new double[getRowEff(m1)][getColEff(m1)];
        int i, j;

        if (getColEff(m1) == getColEff(m2) && getRowEff(m1) == getRowEff(m2)) {
            for (i = 0; i < getRowEff(m1); i++) {
                for (j = 0; j < getColEff(m1); j++) {
                    m3[i][j] = getElmt(m1, i, j) + getElmt(m2, i, j);
                }
            }
        }
        return m3;
    }

    public static double[][] subtractMatrix(double[][] m1, double[][] m2) {
        double[][] m3 = new double[getRowEff(m1)][getColEff(m1)];
        int i, j;

        if (getColEff(m1) == getColEff(m2) && getRowEff(m1) == getRowEff(m2)) {
            for (i = 0; i < getRowEff(m1); i++) {
                for (j = 0; j < getColEff(m1); j++) {
                    m3[i][j] = getElmt(m1, i, j) - getElmt(m2, i, j);
                }
            }
        }
        return m3;
    }

    public static double[][] multiplyMatrix(double[][] m1, double[][] m2) {
        double[][] m3 = new double[getRowEff(m1)][getColEff(m2)];
        int i, j, k;

        if (getColEff(m1) == getRowEff(m2)) {
            for (i = 0; i < getRowEff(m1); i++) {
                for (j = 0; j < getColEff(m2); j++) {
                    for (k = 0; k < getColEff(m1); k++) {
                        m3[i][j] += (getElmt(m1, i, k) * getElmt(m2, k, j));
                    }
                }
            }
        }
        return m3;
    }

    public static double[][] multiplyMatrixWithMod(double[][] m1, double[][] m2, int mod) {
        double[][] m3 = new double[getRowEff(m1)][getColEff(m2)];
        int i, j, k;

        if (getColEff(m1) == getRowEff(m2)) {
            for (i = 0; i < getRowEff(m1); i++) {
                for (j = 0; j < getColEff(m2); j++) {
                    for (k = 0; k < getColEff(m1); k++) {
                        m3[i][j] += (getElmt(m1, i, k) * getElmt(m2, k, j));
                    }
                }
            }

            for (i = 0; i < getRowEff(m3); i++) {
                for (j = 0; j < getColEff(m3); j++) {
                    m3[i][j] = m3[i][j] % mod;
                }
            }
        }
        return m3;
    }

    public static double[][] multiplyByConst(double[][] m, double x) {
        double[][] m3 = new double[getRowEff(m)][getColEff(m)];
        int i, j;

        for (i = 0; i < getRowEff(m); i++) {
            for (j = 0; j < getColEff(m); j++) {
                m3[i][j] = getElmt(m, i, j) * x;
            }
        }
        return m3;
    }

    public static void pMultiplyByConst(double[][] m, int x) {
        int i, j;

        for (i = 0; i < getRowEff(m); i++) {
            for (j = 0; j < getColEff(m); j++) {
                m[i][j] = getElmt(m, i, j) * x;
            }
        }
    }

    public static boolean isMatrixEqual(double[][] m1, double[][] m2) {
        int i, j;

        if (getColEff(m1) != getColEff(m2) || getRowEff(m1) != getRowEff(m2)) {
            return false;
        } else if (getColEff(m1) == getColEff(m2) && getRowEff(m1) == getRowEff(m2)) {
            for (i = 0; i < getRowEff(m1); i++) {
                for (j = 0; j < getColEff(m1); j++) {
                    if (getElmt(m1, i, j) != getElmt(m2, i, j)) {
                        return false;
                    }
                    ;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isMatrixNotEqual(double[][] m1, double[][] m2) {
        return !isMatrixEqual(m1, m2);
    }

    public static boolean isMatrixSizeEqual(double[][] m1, double[][] m2) {
        return (getColEff(m1) == getColEff(m2) && getRowEff(m1) == getRowEff(m2));
    }

    public static int countElmt(double[][] m) {
        return getRowEff(m) * getColEff(m);
    }

    public static boolean isSquare(double[][] m) {
         return getColEff(m) == getRowEff(m);
    }

    public static boolean isSymmetric(double[][] m) {
        if (!isSquare(m)) {
            return false;
        } else {
            int i, j;
            for (i = 0; i < getRowEff(m); i++) {
                for (j = 0; j < getColEff(m); j++) {
                    if (getElmt(m, i, j) != getElmt(m, j, i)) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public static boolean isIdentity(double[][] m) {
        int i, j;
        if (!isSquare(m)) {
            return false;
        } else {
            for (i = 0; i < getRowEff(m); i++) {
                for (j = 0; j < getColEff(m); j++) {
                    if (i == j) {
                        if (getElmt(m, i, j) != 1) {
                            return false;
                        }
                    } else {
                        if (getElmt(m, i, j) != 0) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
    }

    public static boolean isSparse(double[][] m) {
        int count = 0, i, j;
        for (i = 0; i < getRowEff(m); i++) {
            for (j = 0; j < getColEff(m); j++) {
                if (getElmt(m, i, j) != 0) {
                    count++;
                }
            }
        }
        return (count <= countElmt(m) * 5 / 100);
    }

    public static double[][] negation(double[][] m) {
        int i, j;
        double[][] m2 = new double[getRowEff(m)][getColEff(m)];

        for (i = 0; i < getRowEff(m); i++) {
            for (j = 0; j < getColEff(m); j++) {
                m2[i][j] = getElmt(m, i, j) * (-1);
            }
        }
        return m2;
    }

    public static void pNegation(double[][] m) {
        int i, j;

        for (i = 0; i < getRowEff(m); i++) {
            for (j = 0; j < getColEff(m); j++) {
                m[i][j] = getElmt(m, i, j) * (-1);
            }
        }
    }

    public static double[][] submatrix(double[][] m, int nRow, int nCol){
        double[][] submatriks = new double[getRowEff(m)-1][getColEff(m)-1];
        int i,j;
    
        for(i=0; i<getRowEff(submatriks); i++){
            for(j=0; j<getColEff(submatriks); j++){
                if (i<nRow && j<nCol){
                    submatriks[i][j] = m[i][j];
                } 
                else if (i<nRow && j >= nCol){
                    submatriks[i][j] = m[i][j+1];
                } 
                else if (i>=nRow && j<nCol){
                    submatriks[i][j] = m[i+1][j];
                } 
                else if (i>=nRow&&j>=nCol){
                    submatriks[i][j] = m[i+1][j+1];
                }
            }
        }
        return submatriks;
    }
    
    public static double determinant(double[][] m){
        double det;
        int tanda, j;
    
        det = 0;
        tanda = 1;
        if (getRowEff(m)==1){
            det = m[0][0];
        } 
        else if (getRowEff(m)==2){
            det = m[0][0]*m[1][1] - m[0][1]*m[1][0];
        } 
        else if (getRowEff(m)>2){    
            for (j = 0; j <getColEff(m); j++){
                det += tanda*m[0][j]*determinant(submatrix(m, 0, j));
                tanda = -tanda;
            }
        } 
        return det;
    }

    public static double[][] transpose(double[][] m) {
        int i, j;
        double temp;
        double[][] m2 = new double[getRowEff(m)][getColEff(m)];

        if (isSquare(m)) {
            for (i = 0; i < getRowEff(m); i++) {
                for (j = 0; j < getColEff(m); j++) {
                    m2[i][j] = getElmt(m, i, j);
                }
            }

            for (i = 0; i < getRowEff(m2); i++) {
                for (j = i + 1; j < getColEff(m2); j++) {
                    temp = getElmt(m2, i, j);
                    m2[i][j] = (getElmt(m2, j, i));
                    m2[j][i] = temp;
                }
            }
        }

        return m2;
    }

    public static void pTranspose(double[][] m) {
        int i, j;
        double temp;

        if (isSquare(m)) {
            for (i = 0; i < getRowEff(m); i++) {
                for (j = i + 1; j < getColEff(m); j++) {
                    temp = getElmt(m, i, j);
                    m[i][j] = (getElmt(m, j, i));
                    m[j][i] = temp;
                }
            }
        }
    }

    public static double[][] IdentityMatrix(double[][] m, int n){
        int i, j;
        if (isSquare(m)) {
            for (i = 0; i < getRowEff(m); i++) {
                for (j = i + 1; j < getColEff(m); j++) {
                    if(i == j){
                        m[i][j] = 1;
                    }
                    else{
                        m[i][j] = 0;
                    }
                }
            }
        }
        return m;
    }

    public static boolean isFullZeroRow(double[][] m, int row){
        int j;
        for(j = 0; j < getColEff(m); j++){
            if(m[row][j] != 0){
                return false;
            }
        }
        return true;
    }

    public static void rowMultiplyByConst(double[][] m, double x, int row) {
        int j;
        for(j = 0; j < getColEff(m); j++){
            m[row][j] *= x;
        }
    }

    public static void colMultiplyByConst(double[][] m, double x, int col) {
        int i;
        for(i = 0; i < getColEff(m); i++){
            m[i][col] *= x;
        }
    }

    public static void setElmt(double[][] m, int row, int col, double val){
        m[row][col] = val;
    }

    public static double[][] copyMatrix(double[][] m){
        double[][] newM = new double[getRowEff(m)][getColEff(m)];
        int i, j;
        for(i = 0; i < getRowEff(m); i++){
            for(j = 0; j < getColEff(m); j++){
                newM[i][j] = m[i][j];
            }
        }
        return newM;
    }

    public static int getIdxColEl(double [][] m, double val){
        int i, j, col=0;
        for(i=0; i<getRowEff(m); i++){
            for(j=0; j<getColEff(m); j++){
                if(getElmt(m, i, j) == val){
                    col = j;
                }
            }
        }
        return col;
    } 
    public static int getIdxRowEl(double [][] m, double val){
        int i, j, row=0;
        for(i=0; i<getRowEff(m); i++){
            for(j=0; j<getColEff(m); j++){
                if(getElmt(m, i, j) == val){
                    row = i;
                }
            }
        }
        return row;
    } 
}