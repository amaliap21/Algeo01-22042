# Tugas Besar 1 IF2123 Aljabar Linier dan Geometri

> Sistem Persamaan Linier, Determinan, dan Aplikasinya

## Kelompok 9 - Sembilan Ayam Naga
| NIM | Nama |
| :---: | :---: |
| 13522042 | Amalia Putri |
| 13522053 | Erdianti Wiga Putri Andini |
| 13522060 | Andhita Naura Hariyanto |

### Tentang Projek

Program yang dirancang memiliki beberapa fitur :
- Mencari solusi Sistem Persamaan Linear (SPL) dengan metode Gauss, metode Gauss-Jordan, metode Inverse, dan Kaidah Cramer
- Mencari determinan sebuah matriks dengan metode reduksi baris (menggunakan metode perhitungan setelah pembentukan Upper Triangle serta Lower Triangle) dan metode ekspansi kofaktor
- Menyelesaikan persoalan interpolasi polinom
- Menyelesaikan persoalan interpolasi bicubic
- Menyelesaikan persoalan regresi linear berganda

### Struktur Program

```bash
.
│   README.md
│
├───bin                                     # Bytecode
│   ├───ADT_Matrix
│   │       MatrixInput.class
│   │       MatrixOP.class
│   │       MatrixOutput.class
│   │
│   ├───Function                        # Function package   
│   │       Bicubic.class
│   │       Cofactor.class
│   │       Cramer.class
│   │       Gauss.class
│   │       GaussJordan.class
│   │       InterpolasiPolinom.class
│   │       Inverse.class
│   │       InverseSpl.class
│   │       Output.class
│   │       Regresi.class
│   │       Output.class
│   │       Triangle.class
│   │
│   ├───Main                            # Main package
│           Main.class
│
├───doc                             # Folder yang berisi laporan
├───src                             # Source code
│   ├───ADT_Matrix                  # Function package
│   │       MatrixInput.java
│   │       MatrixOP.java
│   │       MatrixOutput.java
│   │
│   ├───Function                        # Function package   
│   │       Bicubic.java
│   │       Cofactor.java
│   │       Cramer.java
│   │       Gauss.java
│   │       GaussJordan.java
│   │       InterpolasiPolinom.java
│   │       Inverse.java
│   │       InverseSpl.java
│   │       Output.java
│   │       Regresi.java
│   │       Output.java
│   │       Triangle.java
│   │
│   ├───Main                            # Main package
│           Main.java
│
└───test                            # Testing cases
    │   case1a.txt
    │   case1b.txt
    │   case1c.txt
    │   case2a.txt
    │   case2b.txt
    │   case3a.txt
    │   case3b.txt
    │   case4.txt
    │   case5a.txt
    │   case5aa.txt
    │   case5ab.txt
    │   case5ac.txt
    │   case5ad.txt
    │   case5b.txt
    │   case5ba.txt
    │   case5bb.txt
    │   case5bc.txt
    │   case5c.txt
    │   case6.txt
    │   case6a.txt
    │   case7.txt
    │   case7a.txt
    │   case7b.txt
    │   case7c.txt
    │   case7d.txt
    │   case8a.txt
    │   case8b.txt
    │   case8c.txt
    │
    └───result                      # Result file directory
```

### Cara Menjalankan Program

```shell
cd src
javac -d ..\bin ADT_Matrix/*.java Main/*.java
cd ..
cd bin
java Main.Main
```

PENJELASAN

1. Terlebih dahulu pindah ke folder src : `cd src`

2. Jalankan :
`javac -d ..\bin ADT_Matrix/*.java Main/*.java`

3. Kemudian, ubah directory : `cd ..`

4. Kemudian, pindah ke folder bin : `cd bin`

5. Kemudian, jalankan : `java Main.Main`

6. Folder `result` dipastikan keberadaannya di dalam folder `test`. Folder `result` ini digunakan untuk menyimpan hasil perhitungan yang disimpan dalam bentuk file.