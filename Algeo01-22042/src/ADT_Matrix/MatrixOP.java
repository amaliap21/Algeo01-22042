package ADT_Matrix;

import java.io.*;
import java.util.*;

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

    /* ********** KELOMPOK OPERASI ARITMATIKA TERHADAP TYPE ********** */
    double[][] addMatrix(double[][] m1, double[][] m2) {
        /* Prekondisi : m1 berukuran sama dengan m2 */
        /* Mengirim hasil penjumlahan matriks: m1 + m2 */

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
        /* Prekondisi : m1 berukuran sama dengan m2 */
        /* Mengirim hasil penjumlahan matriks: m1 - m2 */

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
        /* Prekondisi : Ukuran kolom efektif m1 = ukuran baris efektif m2 */
        /* Mengirim hasil perkalian matriks: salinan m1 * m2 */

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
        /* Prekondisi : Ukuran kolom efektif m1 = ukuran baris efektif m2 */
        /*
         * Mengirim hasil perkalian matriks: salinan (m1 * m2)%mod, artinya setiap
         * elemen matrix hasil perkalian m1 * m2 dilakukan modulo terhadap mod
         */

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
        /* Mengirim hasil perkalian setiap elemen m dengan x */

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
        /* I.S. m terdefinisi, k terdefinisi */
        /* F.S. Mengalikan setiap elemen m dengan k */

        int i, j;

        for (i = 0; i < getRowEff(m); i++) {
            for (j = 0; j < getColEff(m); j++) {
                m[i][j] = getElmt(m, i, j) * x;
            }
        }
    }

    /* ********** KELOMPOK OPERASI RELASIONAL TERHADAP Matrix ********** */
    boolean isMatrixEqual(double[][] m1, double[][] m2) {
        /* Mengirimkan true jika m1 = m2, yaitu count(m1) = count(m2) dan */
        /* untuk setiap i,j yang merupakan Index baris dan kolom m1(i,j) = m2(i,j) */
        /* Juga merupakan strong eq karena getLastIdxCol(m1) = getLastIdxCol(m2) */

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
    }

    boolean isMatrixNotEqual(double[][] m1, double[][] m2) {
        /* Mengirimkan true jika m1 tidak sama dengan m2 */

        return !isMatrixEqual(m1, m2);
    }

    boolean isMatrixSizeEqual(double[][] m1, double[][] m2) {
        /*
         * Mengirimkan true jika ukuran efektif matriks m1 sama dengan ukuran efektif m2
         */
        /* yaitu RowEff(m1) = RowEff (m2) dan ColEff (m1) = ColEff (m2) */

        return (getColEff(m1) == getColEff(m2) && getRowEff(m1) == getRowEff(m2));
    }

    /* ********** Operasi lain ********** */
    int countElmt(double[][] m) {
        /* Mengirimkan banyaknya elemen m */

        return getRowEff(m) * getColEff(m);
    }

    /* ********** KELOMPOK TEST TERHADAP Matrix ********** */
    boolean isSquare(double[][] m) {
        /* Mengirimkan true jika m adalah matriks dg ukuran baris dan kolom sama */

        return getColEff(m) == getRowEff(m);
    }

    boolean isSymmetric(double[][] m) {
        /*
         * Mengirimkan true jika m adalah matriks simetri : isSquare(m)
         * dan untuk setiap elemen m, m(i,j)=m(j,i)
         */

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
        /*
         * Mengirimkan true jika m adalah matriks satuan: isSquare(m) dan
         * setiap elemen diagonal m bernilai 1 dan elemen yang bukan diagonal bernilai 0
         */

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
        /*
         * Mengirimkan true jika m adalah matriks sparse: matriks “jarang” dengan
         * definisi:
         * hanya maksimal 5% dari memori matriks yang efektif bukan bernilai 0
         */

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
        /* Menghasilkan salinan m dengan setiap elemen dinegasikan (dikalikan -1) */

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
        /* I.S. m terdefinisi */
        /* F.S. m di-invers, yaitu setiap elemennya dinegasikan (dikalikan -1) */

        int i, j;

        for (i = 0; i < getRowEff(m); i++) {
            for (j = 0; j < getColEff(m); j++) {
                m[i][j] = getElmt(m, i, j) * (-1);
            }
        }
    }

    float determinant(double[][] m) {
        /* Prekondisi: isSquare(m) */
        /* Menghitung nilai determinan m */

        double[][] temp;
        int i = 0, j = 0, k = 0, indexRowMtemp, indexColMtemp;
        float det = 0;

        if (getRowEff(m) == 1) {
            
        } else if (getRowEff(m) == 2) {
            return getElmt(m, 0, 0) * getElmt(m, 1, 1) - (getElmt(m, 0, 1) * getElmt(m, 1, 0));
        } else {

            for (k = 0; k <= getLastIdxCol(m); k++) {
                indexRowMtemp = 0;
                for (i = 1; i <= getLastIdxRow(m); i++) {
                    indexColMtemp = 0;
                    for (j = 0; j <= getLastIdxRow(m); j++) {
                        if (j != k) {
                            getElmt(mtemp, indexRowMtemp, indexColMtemp) = getElmt(m, i, j);
                            indexColMtemp++;
                        }
                    }
                    indexRowMtemp++;
                }

                if (k % 2 == 0) {
                    det += getElmt(m, 0, k) * determinant(mtemp);
                } else {
                    det -= getElmt(m, 0, k) * determinant(mtemp);
                }
            }

            return det;
        }

    }
    /* Prekondisi: isSquare(m) */
    /* Menghitung nilai determinan m */

    double[][] transpose(double[][] m){
        /* I.S. m terdefinisi dan IsSquare(m) */
        /* F.S. menghasilkan salinan transpose dari m, yaitu setiap elemen m(i,j) ditukar nilainya dengan elemen m(j,i) */
        double[][] mf;
        createMatrix(getRowEff(m), getColEff(m), &mf);
        int i, j;
        for (i = 0; i < getRowEff(m); i++) {
            for (j = 0; j < getColEff(m); j++) {
                getElmt(mf, i , j) = getElmt(m, j, i);
            }
        }
        return mf;
    }
    /* I.S. m terdefinisi dan IsSquare(m) */
    /*
     * F.S. menghasilkan salinan transpose dari m, yaitu setiap elemen m(i,j)
     * ditukar nilainya dengan elemen m(j,i)
     */

    void pTranspose(double[][] *m){
        int i, j;
        ElType temp;
        if(isSquare(*m)){
            for(i = 0; i < getRowEff(*m); i++){
                for(j = i+1; j < getColEff(*m); j++){
                    temp = getElmt(*m, i, j);
                    getElmt(*m, i, j) = (getElmt(*m, j, i));
                    getElmt(*m, j, i) = temp;
                }
            }
        }
    }
    /* I.S. m terdefinisi dan IsSquare(m) */
    /* F.S. m "di-transpose", yaitu setiap elemen m(i,j) ditukar nilainya dengan elemen m(j,i) */
}