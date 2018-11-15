package p4;

import java.text.*;
import java.util.*;

/**
 * @author Kevin López Cala
 */
public class prodMat {

    private int[][] mat, vec;
    private int N, J;

    public prodMat(int tam, int tav) {
        this.N = tam;
        this.J = tav;
        mat = new int[N][N];
        vec = new int[J][J];
    }

    public void intro_mat() {
        Scanner tecla = new Scanner(System.in);
        System.out.print("Introduzca los valores de la matriz1 ");

        for (int i = 0; i < mat.length; ++i)
            for (int j = 0; j < mat.length; ++j)
                mat[i][j] = tecla.nextInt();

    }

    public void intro_vec() {
        Scanner tecla = new Scanner(System.in);
        System.out.print("Introduzca los valores de la matriz2 ");

        for (int i = 0; i < vec.length; ++i)
            for (int j = 0; j < vec.length; ++j)
                vec[i][j] = tecla.nextInt();
    }

    public void intro_matr() {
        Random rm = new Random();

        for (int i = 0; i < mat.length; ++i) {
            for (int j = 0; j < mat.length; ++j) {
                mat[i][j] = rm.nextInt(10);
            }
        }

        for (int i = 0; i < mat.length; ++i)
            for (int j = 0; j < mat.length; ++j)
                System.out.print(mat[i][j] + " ");
        System.out.println("\n");
    }

    public void intro_vecz() {
        Random rm = new Random();

        for (int i = 0; i < vec.length; ++i) {
            for (int j = 0; j < vec.length; ++j) {
                vec[i][j] = rm.nextInt(10);
            }
        }

        for (int i = 0; i < mat.length; ++i)
            for (int j = 0; j < mat.length; ++j)
                System.out.print(vec[i][j] + " ");
        System.out.println("\n");
    }

    public void producto() {
        int[][] res = new int[N][N];

        for (int i = 0; i < mat.length; ++i)
            for (int k = 0; k < mat.length; ++k)
                for (int j = 0; j < mat.length; ++j)
                    res[i][k] += mat[i][j] * vec[j][k];

        for (int i = 0; i < res.length; ++i)
            for (int j = 0; j < res.length; ++j)
                System.out.print(res[i][j] + " ");
        System.out.println("\n");
    }

    public static void main(String[] args) {

        int sel, op;
        Scanner tecla = new Scanner(System.in);
        Random rm = new Random();

        do {
            do {
                System.out.println("Elija una opcion : \n1-MatrizManual\n2-MatrizRandom");
                op = tecla.nextInt();
            } while (op < 1 || op > 2);

            switch (op) {

            case 1:
                System.out.print("Introduzca el tamaño de la matriz1 ");
                int N = tecla.nextInt();
                System.out.print("Introduzca el tamaño de la matriz2 ");
                int J = tecla.nextInt();

                prodMat Manual = new prodMat(N, J);
                Manual.intro_mat();
                Manual.intro_vec();

                Date d = new Date();
                DateFormat dd = new SimpleDateFormat("HH:mm:ss");
                long ini = System.nanoTime();
                d.setTime(ini);
                Manual.producto();
                long fin = System.nanoTime();
                d.setTime(fin);
                System.out.println("Terminado : " + dd.format(d) + " tras " + (fin - ini));
                break;

            case 2:

                prodMat Random = new prodMat(2, 2);
                Random.intro_matr();
                Random.intro_vecz();

                Date dds = new Date();
                DateFormat dd1 = new SimpleDateFormat("HH:mm:ss");
                ini = System.nanoTime();
                dds.setTime(ini);

                Random.producto();
                fin = System.nanoTime();
                dds.setTime(fin);
                System.out.println("Terminado : " + dd1.format(dds) + " tras " + (fin - ini));
                break;
            }

            System.out.println("Pulse 0 para volver atras");
            sel = tecla.nextInt();
        } while (sel == 0);
    }
}