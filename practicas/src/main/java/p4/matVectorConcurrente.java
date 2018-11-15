package p4;

import java.util.*;
import java.text.*;

/**
 * @author Kevin López Cala
 */
public class matVectorConcurrente implements Runnable {
    private int fil, tam;
    private int[][] matz;
    private int[] vecz;
    private static int[] res;

    public matVectorConcurrente(int fila, int[][] mat, int[] vec, int tam) {
        vecz = vec;
        matz = mat;
        this.fil = fila;
        this.tam = tam;
        res = new int[tam];
    }

    @Override
    public void run() {
        for (int j = 0; j < matz.length; ++j) {
            res[fil] += matz[fil][j] * vecz[j];
        }

    }

    public static void mostrar() {
        for (int i = 0; i < res.length; ++i) {
            System.out.println(res[i]);
        }
    }

    public static void main(String[] args) throws Exception {

        int sel, op;
        Scanner tecla = new Scanner(System.in);
        Random rm = new Random();
        int[][] mat, matz;
        int[] vec, vecz;

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
                mat = new int[N][N];
                vec = new int[J];
                System.out.print("Introduzca valores para la matriz ");
                for (int i = 0; i < N; ++i) {
                    for (int j = 0; j < N; ++j) {
                        mat[i][j] = tecla.nextInt();
                    }
                }
                System.out.print("Introduzca valores para el vector ");
                for (int i = 0; i < J; ++i) {

                    vec[i] = tecla.nextInt();
                }
                Thread[] uno = new Thread[N];

                for (int i = 0; i < N; ++i) {

                    uno[i] = new Thread(new matVectorConcurrente(i, mat, vec, N));

                }
                Date d = new Date();
                DateFormat dd = new SimpleDateFormat("HH:mm:ss");
                long ini = System.nanoTime();
                d.setTime(ini);

                for (int i = 0; i < N; ++i)
                    uno[i].start();

                long fin = System.nanoTime();
                d.setTime(fin);
                System.out.println("Terminado : " + dd.format(d) + " tras " + (fin - ini));

                for (int i = 0; i < N; ++i)
                    uno[i].join();

                matVectorConcurrente.mostrar();

                break;

            case 2:

                int K = 2;
                int S = 2;
                matz = new int[K][K];
                vecz = new int[S];

                for (int i = 0; i < K; ++i) {
                    for (int j = 0; j < K; ++j) {
                        matz[i][j] = rm.nextInt(10);
                    }
                }

                for (int i = 0; i < S; ++i) {

                    vecz[i] = rm.nextInt(10);
                }
                Thread[] dos = new Thread[K];

                for (int i = 0; i < K; ++i) {

                    dos[i] = new Thread(new matVectorConcurrente(i, matz, vecz, K));

                }

                Date d1 = new Date();
                DateFormat dd1 = new SimpleDateFormat("HH:mm:ss");
                ini = System.nanoTime();
                d1.setTime(ini);

                for (int i = 0; i < K; ++i)
                    dos[i].start();

                fin = System.nanoTime();
                d1.setTime(fin);
                System.out.println("Terminado : " + dd1.format(d1) + " tras " + (fin - ini));
                for (int i = 0; i < K; ++i)
                    dos[i].join();

                matVectorConcurrente.mostrar();

                break;

            }

            System.out.println("Pulse 0 para volver atras");
            sel = tecla.nextInt();

        } while (sel == 0);

    }

}