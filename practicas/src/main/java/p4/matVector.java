
package p4;

import java.util.*;
import java.text.*;

/**
 * @author Kevin López Cala
 */
public class matVector {

    private int[][] mat;
    private double[] vec;
    private int N, J;

    public matVector(int tam, int tav) {
        this.N = tam;
        this.J = tav;
        mat = new int[N][N];
        vec = new double[J];
    }

    public void intro_mat() {
        Scanner tecla = new Scanner(System.in);
        System.out.print("Introduzca valores para la matriz ");

        for (int i = 0; i < mat.length; ++i) {
            for (int j = 0; j < mat.length; ++j) {
                mat[i][j] = tecla.nextInt();
            }  asd asd asd as das das gssf df sf sdf sd asda gsdg sd sdgs d asd gdfg gsdgsd sdf sdfsdfsdgsdfsdfsdfsd
        }
    }

    public void intro_vec() {
        Scanner teclas = new Scanner(System.in);
        System.out.print("Introduzca valores para el vector ");

        for (int i = 0; i < vec.length; ++i) {
            vec[i] = teclas.nextDouble();
        }
    }

    public void intro_matr() {
        Random rm = new Random();

        for (int i = 0; i < mat.length; ++i)
            for (int j = 0; j < mat.length; ++j)
                mat[i][j] = rm.nextInt(10);
    }

    public void intro_vecr() {
        Random rm = new Random();

        for (int i = 0; i < vec.length; ++i)
            vec[i] = rm.nextInt(10);
    }

    public void producto() {
        System.out.println("\n");
        int[] res = new int[N];

        for (int i = 0; i < mat.length; ++i) {
            for (int j = 0; j < mat.length; ++j) {
                res[i] += vec[j] * mat[i][j];
            }
        }

        for (int i = 0; i < N; ++i)
            System.out.println(res[i]);
    }

    public static void main(String[] args) {

        int sel, op;
        Scanner tecla = new Scanner(System.in);

        do {
            do {
                System.out.println("Elija una opcion : \n1-MatrizManual\n2-MatrizRandom");
                op = tecla.nextInt();
            } while (op < 1 || op > 2);

            switch (op) {
            case 1:
                System.out.print("Introduzca el tamaño de la matriz ");
                int N = tecla.nextInt();
                System.out.print("Introduzca el tamaño del vector ");
                int J = tecla.nextInt();

                matVector Manual = new matVector(N, J);
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
                matVector Random = new matVector(1000, 1000);
                Random.intro_matr();
                Random.intro_vecr();

                Date d2 = new Date();
                DateFormat dd1 = new SimpleDateFormat("HH:mm:ss");
                ini = System.nanoTime();
                d2.setTime(ini);
                Random.producto();
                fin = System.nanoTime();
                d2.setTime(fin);
                System.out.println("Terminado : " + dd1.format(d2) + " tras " + (fin - ini));
                break;
            }
            System.out.println("Pulse 0 para volver atras");
            sel = tecla.nextInt();

        } while (sel == 0);
        tecla.close();
    }
}