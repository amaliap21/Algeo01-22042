package ADT_Matrix;

public class MatrixOP {
    int getRowEff(double[][] matrix) {
        return matrix.length;
    }

    int getColEff(double[][] matrix) {
        return matrix[0].length;
    }

    double getElmt(double[][] matrix, int i, int j) {
        return matrix[i][j];
    }

    int getLastIdxRow(double[][] matrix) {
        return getRowEff(matrix) - 1;
    }

    int getLastIdxCol(double[][] matrix) {
        return getColEff(matrix) - 1;
    } 

    boolean isIdxEff(double[][] matrix, int i, int j) {
        return ((i >= 0 && i <= getLastIdxRow(matrix)) && (j >= 0 && j <= getLastIdxCol(matrix)));
    }

    double getElmtDiagonal(double[][] matrix, int i) {
        return getElmt(matrix, i, i);
    }

    void displayMatrix(double[][] matrix) {
        int i, j;
        for (i = 0; i < getRowEff(matrix); i++) {
            for (j = 0; j < getColEff(matrix); j++) {
                if (j == getLastIdxCol(matrix)) {
                    System.out.print(getElmt(matrix, i, j));
                } else {
                    System.out.print(getElmt(matrix, i, j) + " ");
                }
            }
        }
    }

    double[][] addMatrix(double[][] m1, double[][] m2) {
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

    double[][] subtractMatrix(double[][] m1, double[][] m2) {
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

    double[][] multiplyMatrix(double[][] m1, double[][] m2) {
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

    double[][] multiplyMatrixWithMod(double[][] m1, double[][] m2, int mod) {
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

    double[][] multiplyByConst(double[][] m, double x) {
        double[][] m3 = new double[getRowEff(m)][getColEff(m)];
        int i, j;

        for (i = 0; i < getRowEff(m); i++) {
            for (j = 0; j < getColEff(m); j++) {
                m3[i][j] = getElmt(m, i, j) * x;
            }
        }
        return m3;
    }

    void pMultiplyByConst(double[][] m, int x) {
        int i, j;

        for (i = 0; i < getRowEff(m); i++) {
            for (j = 0; j < getColEff(m); j++) {
                m[i][j] = getElmt(m, i, j) * x;
            }
        }
    }

    boolean isMatrixEqual(double[][] m1, double[][] m2) {
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

    boolean isMatrixNotEqual(double[][] m1, double[][] m2) {
        return !isMatrixEqual(m1, m2);
    }

    boolean isMatrixSizeEqual(double[][] m1, double[][] m2) {
        return (getColEff(m1) == getColEff(m2) && getRowEff(m1) == getRowEff(m2));
    }

    int countElmt(double[][] m) {
        return getRowEff(m) * getColEff(m);
    }

    boolean isSquare(double[][] m) {
         return getColEff(m) == getRowEff(m);
    }

    boolean isSymmetric(double[][] m) {
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

    boolean isIdentity(double[][] m) {
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

    boolean isSparse(double[][] m) {
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

    double[][] negation(double[][] m) {
        int i, j;
        double[][] m2 = new double[getRowEff(m)][getColEff(m)];

        for (i = 0; i < getRowEff(m); i++) {
            for (j = 0; j < getColEff(m); j++) {
                m2[i][j] = getElmt(m, i, j) * (-1);
            }
        }
        return m2;
    }

    void pNegation(double[][] m) {
        int i, j;

        for (i = 0; i < getRowEff(m); i++) {
            for (j = 0; j < getColEff(m); j++) {
                m[i][j] = getElmt(m, i, j) * (-1);
            }
        }
    }

    float determinant(double[][] m) {
        int i, j, k;
        double[][] m2 = new double[getRowEff(m)][getColEff(m)];
        double det = 1, temp;

        for (i = 0; i < getRowEff(m); i++) {
            for (j = 0; j < getColEff(m); j++) {
                m2[i][j] = getElmt(m, i, j);
            }
        }

        for (i = 0; i < getRowEff(m2); i++) {
            for (j = i + 1; j < getRowEff(m2); j++) {
                temp = getElmt(m2, j, i) / getElmt(m2, i, i);
                for (k = 0; k < getColEff(m2); k++) {
                    m2[j][k] = getElmt(m2, j, k) - (getElmt(m2, i, k) * temp);
                }
            }
        }

        for (i = 0; i < getRowEff(m2); i++) {
            det *= getElmt(m2, i, i);
        }

        return (float) det;
    }

    double[][] transpose(double[][] m) {
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

    void pTranspose(double[][] m) {
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

    double[][] IdentityMatrix(double[][] m, int n){
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

    boolean isFullZeroRow(double[][] m, int row){
        int j;
        for(j = 0; j < getColEff(m); j++){
            if(m[row][j] != 0){
                return false;
            }
        }
        return true;
    }

    void rowMultiplyByConst(double[][] m, double x, int row) {
        int j;
        for(j = 0; j < getColEff(m); j++){
            m[row][j] *= x;
        }
    }

    void colMultiplyByConst(double[][] m, double x, int col) {
        int i;
        for(i = 0; i < getColEff(m); i++){
            m[i][col] *= x;
        }
    }
}