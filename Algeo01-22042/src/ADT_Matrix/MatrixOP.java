package ADT_Matrix;

import java.io.*;
import java.util.*;

public class MatrixOP{
    public static int getRowEff(double[][] matrix){
        return matrix.length;
    }

    public static int getColEff(double[][] matrix){
        return matrix[0].length;
    }

    public static double getElmt(double[][] matrix, int i, int j) {
        return matrix[i][j];
    }

    public static int getLastIdxRow(double[][] m){
        return getRowEff(m) - 1;
    }

    public static int getLastIdxCol(double[][] m){
        return getColEff(m) - 1;
    }

    public static boolean isIdxEff(double[][] m, int i, int j){
        return ((i >= 0 && i <= getLastIdxRow(m)) && (j >= 0 && j <= getLastIdxCol(m)));
    }

    public static double getElmtDiagonal(double[][] m, int i){
        return getElmt(m, i, i);
    }

    /* ********** KELOMPOK OPERASI ARITMATIKA TERHADAP TYPE ********** */
    public static double[][] addMatrix(double[][] m1, double[][] m2){
        double[][] m3;
        int i, j;
        if(getColEff(m1) == getColEff(m2) && getRowEff(m1) == getRowEff(m2)){
            createMatrix(ROW_EFF(m1), COL_EFF(m1), &m3);
            for(i = 0; i < ROW_EFF(m1); i++){
                for(j = 0; j < COL_EFF(m1); j++){
                    ELMT(m3, i, j) = ELMT(m1, i, j) + ELMT(m2, i, j);
                
                }
            }
        }
        return m3;
    }
    /* Prekondisi : m1 berukuran sama dengan m2 */
    /* Mengirim hasil penjumlahan matriks: m1 + m2 */

    Matrix subtractMatrix(Matrix m1, Matrix m2){
        Matrix m3;
        int i, j;
        if(COL_EFF(m1) == COL_EFF(m2) && ROW_EFF(m1) == ROW_EFF(m2)){
            createMatrix(ROW_EFF(m1), COL_EFF(m1), &m3);
            for(i = 0; i < ROW_EFF(m1); i++){
                for(j = 0; j < COL_EFF(m1); j++){
                    ELMT(m3, i, j) = ELMT(m1, i, j) - ELMT(m2, i, j);
                
                }
            }
        }
        return m3;
    }
    /* Prekondisi : m1 berukuran sama dengan m2 */
    /* Mengirim hasil pengurangan matriks: salinan m1 – m2 */

    Matrix multiplyMatrix(Matrix m1, Matrix m2){
        Matrix m3;
        int i, j, k;
        if(COL_EFF(m1) == ROW_EFF(m2)){
            createMatrix(ROW_EFF(m1), COL_EFF(m2), &m3);
            for(i = 0; i < ROW_EFF(m1); i++){
                for(j = 0; j < COL_EFF(m2); j++){
                    for(k = 0; k < COL_EFF(m1); k++){
                        ELMT(m3, i, j) += (ELMT(m1, i, k)*ELMT(m2, k, j)) ;
                    }
                }
            }
        }
        return m3;
    }
    /* Prekondisi : Ukuran kolom efektif m1 = ukuran baris efektif m2 */
    /* Mengirim hasil perkalian matriks: salinan m1 * m2 */

    Matrix multiplyMatrixWithMod(Matrix m1,Matrix m2,int mod){
        Matrix m3;
        int i, j, k;
        if(COL_EFF(m1) == ROW_EFF(m2)){
            createMatrix(ROW_EFF(m1), COL_EFF(m2), &m3);
            for(i = 0; i < ROW_EFF(m1); i++){
                for(j = 0; j < COL_EFF(m2); j++){
                    for(k = 0; k < COL_EFF(m1); k++){
                        ELMT(m3, i, j) += (ELMT(m1, i, k)*ELMT(m2, k, j));
                    }
                }
            }

            for(i = 0; i < ROW_EFF(m3); i++){
                for(j = 0; j < COL_EFF(m3); j++){
                    ELMT(m3, i, j) = ELMT(m3, i, j) % mod;
                }
            }
        }
        return m3;
    }
    /* Prekondisi : Ukuran kolom efektif m1 = ukuran baris efektif m2 */
    /* Mengirim hasil perkalian matriks: salinan (m1 * m2)%mod, artinya setiap elemen matrix hasil perkalian m1 * m2 dilakukan modulo terhadap mod */

    Matrix multiplyByConst(Matrix m, ElType x){
        Matrix mOut;
        int i, j;
        createMatrix(ROW_EFF(m), COL_EFF(m), &mOut);
        for(i = 0; i < ROW_EFF(m); i++){
            for(j = 0; j < COL_EFF(m); j++){
                ELMT(mOut, i, j) = x * ELMT(m, i, j);
            }
        }
        return mOut;
    }
    /* Mengirim hasil perkalian setiap elemen m dengan x */

    void pMultiplyByConst(Matrix *m, ElType k){
        int i, j;
        for(i = 0; i < ROW_EFF(*m); i++){
            for(j = 0; j < COL_EFF(*m); j++){
                ELMT(*m, i, j) *= k;
            }
        }
    }
    /* I.S. m terdefinisi, k terdefinisi */
    /* F.S. Mengalikan setiap elemen m dengan k */

    /* ********** KELOMPOK OPERASI RELASIONAL TERHADAP Matrix ********** */
    boolean isMatrixEqual(Matrix m1, Matrix m2){
        int i, j;
        if(COL_EFF(m1) != COL_EFF(m2) || ROW_EFF(m1) != ROW_EFF(m2)){
            return false;
        }
        else if (COL_EFF(m1) == COL_EFF(m2) && ROW_EFF(m1) == ROW_EFF(m2)){
            for(i = 0; i < ROW_EFF(m1); i++){
                for(j = 0; j < COL_EFF(m1); j++){
                    if(ELMT(m1, i, j) != ELMT(m2, i, j)){
                        return false;
                    };
                }
            }
            return true;
        }
    }
    /* Mengirimkan true jika m1 = m2, yaitu count(m1) = count(m2) dan */
    /* untuk setiap i,j yang merupakan Index baris dan kolom m1(i,j) = m2(i,j) */
    /* Juga merupakan strong eq karena getLastIdxCol(m1) = getLastIdxCol(m2) */

    boolean isMatrixNotEqual(Matrix m1, Matrix m2){
        return !isMatrixEqual(m1, m2);
    }
    /* Mengirimkan true jika m1 tidak sama dengan m2 */

    boolean isMatrixSizeEqual(Matrix m1, Matrix m2){
        return (COL_EFF(m1) == COL_EFF(m2) && ROW_EFF(m1) == ROW_EFF(m2));
    }
    /* Mengirimkan true jika ukuran efektif matriks m1 sama dengan ukuran efektif m2 */
    /* yaitu RowEff(m1) = RowEff (m2) dan ColEff (m1) = ColEff (m2) */

    /* ********** Operasi lain ********** */
    int countElmt(Matrix m){
        return ROW_EFF(m) * COL_EFF(m);
    }
    /* Mengirimkan banyaknya elemen m */

    /* ********** KELOMPOK TEST TERHADAP Matrix ********** */
    boolean isSquare(Matrix m){
        return COL_EFF(m) == ROW_EFF(m);
    }
    /* Mengirimkan true jika m adalah matriks dg ukuran baris dan kolom sama */

    boolean isSymmetric(Matrix m){
        if (!isSquare(m)) {
            return false;
        }
        else{
            int i, j;
            for(i = 0; i < ROW_EFF(m); i++){
                for(j = 0; j < COL_EFF(m); j++){
                    if(ELMT(m, i, j) != ELMT(m, j, i)){
                        return false;
                    }
                }
            }
            return true;
        }
    }
    /* Mengirimkan true jika m adalah matriks simetri : isSquare(m) 
    dan untuk setiap elemen m, m(i,j)=m(j,i) */

    boolean isIdentity(Matrix m){
        int i, j;
        if (!isSquare(m)){
            return false;
        }
        else{
            for(i = 0; i < ROW_EFF(m); i++){
                for(j = 0; j < COL_EFF(m); j++){
                    if(i == j){
                        if(ELMT(m, i, j) != 1){
                            return false;
                        }
                    }
                    else{
                        if(ELMT(m, i, j) != 0){
                            return false;
                        }
                    }
                }
            }
            return true;
        }
    }
    /* Mengirimkan true jika m adalah matriks satuan: isSquare(m) dan 
    setiap elemen diagonal m bernilai 1 dan elemen yang bukan diagonal bernilai 0 */

    boolean isSparse(Matrix m){
        int count = 0, i, j;
        for(i = 0; i < ROW_EFF(m); i++){
            for(j = 0; j < COL_EFF(m); j++){
                if(ELMT(m, i, j) != 0){
                    count ++;
                }
            }
        }
        return (count <= countElmt(m) * 5 / 100);
    }
    /* Mengirimkan true jika m adalah matriks sparse: matriks “jarang” dengan definisi: 
    hanya maksimal 5% dari memori matriks yang efektif bukan bernilai 0 */

    Matrix negation(Matrix m){
        Matrix m3;
        int i, j;
        createMatrix(ROW_EFF(m), COL_EFF(m), &m3);
        for(i = 0; i < ROW_EFF(m); i++){
            for(j = 0; j < COL_EFF(m); j++){
                ELMT(m3, i, j) = (ELMT(m, i, j)) * (-1);
            }
        }
        return m3;
    }
    /* Menghasilkan salinan m dengan setiap elemen dinegasikan (dikalikan -1) */

    void pNegation(Matrix *m){
        int i, j;
        for(i = 0; i < ROW_EFF(*m); i++){
            for(j = 0; j < COL_EFF(*m); j++){
                ELMT(*m, i, j) *= (-1);
            }
        }
    }
    /* I.S. m terdefinisi */
    /* F.S. m di-invers, yaitu setiap elemennya dinegasikan (dikalikan -1) */

    float determinant(Matrix m){
        if (!isSquare(m)){
            return 0;
        }
        int n = getLastIdxRow(m) + 1;
        int tmpRow[11];
        int i, j, k, idx, temp1, temp2;
        int det = 1;
        int co = 1;
        for(i = 0; i < n; i++){
            idx = i;
            while(ELMT(m,idx,i) == 0 && idx < n){
                idx++;
            }
            if (idx == n){
                return 0;
            }
            if (i != idx){
                for(j = 0; j < n; j++){
                    temp1 = ELMT(m,i,j);
                    ELMT(m,i,j) = ELMT(m,idx,j);
                    ELMT(m,idx,j) = temp1;
                }
                det *= -1;
            }
            for(j = 0;j<n;j++){
                tmpRow[j] = ELMT(m,i,j);
            }
            for (j=i+1; j<n; j++){
                temp1 = tmpRow[i];
                temp2 = ELMT(m,j,i);
                for(k=0;k<n;k++){
                    ELMT(m,j,k) = ((temp1 * ELMT(m,j,k)) - (temp2 * tmpRow[k]));
                }
                co *= temp1;
            }
        }
        for(i=0;i<n;i++){
            det *= ELMT(m,i,i);
        }
        return (det/co);
    }
    /* Prekondisi: isSquare(m) */
    /* Menghitung nilai determinan m */

    Matrix transpose(Matrix m){
        /* I.S. m terdefinisi dan IsSquare(m) */
        /* F.S. menghasilkan salinan transpose dari m, yaitu setiap elemen m(i,j) ditukar nilainya dengan elemen m(j,i) */
        Matrix mf;
        createMatrix(ROW_EFF(m), COL_EFF(m), &mf);
        int i, j;
        for (i = 0; i < ROW_EFF(m); i++) {
            for (j = 0; j < COL_EFF(m); j++) {
                ELMT(mf, i , j) = ELMT(m, j, i);
            }
        }
        return mf;
    }
    /* I.S. m terdefinisi dan IsSquare(m) */
    /* F.S. menghasilkan salinan transpose dari m, yaitu setiap elemen m(i,j) ditukar nilainya dengan elemen m(j,i) */

    void pTranspose(Matrix *m){
        int i, j;
        ElType temp;
        if(isSquare(*m)){
            for(i = 0; i < ROW_EFF(*m); i++){
                for(j = i+1; j < COL_EFF(*m); j++){
                    temp = ELMT(*m, i, j);
                    ELMT(*m, i, j) = (ELMT(*m, j, i));
                    ELMT(*m, j, i) = temp;
                }
            }
        }
    }
    /* I.S. m terdefinisi dan IsSquare(m) */
    /* F.S. m "di-transpose", yaitu setiap elemen m(i,j) ditukar nilainya dengan elemen m(j,i) */
}