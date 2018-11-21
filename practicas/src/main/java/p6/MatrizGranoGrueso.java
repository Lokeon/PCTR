
package p6;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.*;
import java.util.*;

/**
 * @author
 */

public class MatrizGranoGrueso implements Runnable {

    static int[][] matz;
    static int[] vecz;
    private static int[] sol;
    private int fila, sum ;
    static int N ;

    /**
     * 
     * @param hilo corresponde al número de hilos
     */
    public MatrizGranoGrueso(int hilo) {

        this.fila = hilo;

    }

    /**
     * Metodo run donde se realizara el producto de la matiz y el vector
     */
    public void run() {

        sum = 0;

        for (int i = 0; i < sol.length; ++i) {

            sol[fila] += matz[fila][i] * vecz[i];
        }
    }

    /**
     * Metodo para mostrar el vector resultante
     */
    

    public static void rellenarMatVec() {
        Scanner tecla = new Scanner(System.in);
        System.out.print("Introduzca la dimension de la matriz ");
        int N = tecla.nextInt();
        System.out.print("Introduzca la dimension del vector ");
        int O = tecla.nextInt();
        matz = new int[N][N];
        vecz = new int[O];
        sol = new int[N];
        System.out.print("Introduzca los valores de la matriz ");

        for (int i = 0; i < matz.length; ++i) {
            for (int j = 0; j < matz.length; ++j) {

                matz[i][j] = tecla.nextInt();
            }
        }

        System.out.print("Introduzca los valores del vector ");
        for (int i = 0; i < vecz.length; ++i) {

            vecz[i] = tecla.nextInt();
        }

       
    }
    
    public static void mostrar() {
        for (int i = 0; i < sol.length; ++i)
            System.out.print(sol[i] + " ");
        System.out.println("\n");

    }

    /**
     * Representa a la ecuación de Subramaniam
     * 
     * @param Nnld Número de cores lógicos del procesador
     * @param cb   Coeficiente de Bloqueo
     * @return sol
     */
    public static int Subra(int Nnld, double cb) {
        double Nt = (double) Nnld / (1 - cb);
        System.out.println("Se ejecutaran " + Nt + " hilos");
        int sol = (int) Math.round(Nt);

        return sol;
    }

    public static void main(String[] args) throws Exception {
        Scanner tecla = new Scanner(System.in);
        Random rm = new Random();
        int Nt, op, sel = 0;
        int Process = Runtime.getRuntime().availableProcessors();

        do {

            do {

                System.out.println("Elija una opcion : \n1-MatrizManual\n2-MatrizRandom");
                op = tecla.nextInt();

            } while (op < 1 || op > 2);

            switch (op) {

            case 1:

                Nt = Subra(Process, 0.1);

                Date d = new Date();
                DateFormat dd = new SimpleDateFormat("HH:mm:ss");
                long ini = System.nanoTime();
                d.setTime(ini);

                rellenarMatVec();

                ExecutorService RX = Executors.newFixedThreadPool(Nt);

                for (int i = 0; i < N; ++i) {

                    RX.execute(new MatrizGranoGrueso(i));

                }

                RX.shutdown();

                long fin = System.nanoTime();
                d.setTime(fin);
                System.out.println("Terminado : " + dd.format(d) + " tras " + (fin - ini) / 1.0E9 + " seg ");

                MatrizGranoGrueso.mostrar();

                break;

            case 2:

               /* Nt = Subra(Process, 0.2);

                int K = 100;

                int[][] mat1 = new int[K][K];
                int[] vec1 = new int[K];

                for (int i = 0; i < K; ++i) {
                    for (int j = 0; j < K; ++j) {

                        mat1[i][j] = rm.nextInt(10) + 1;

                    }
                }

                for (int i = 0; i < K; ++i) {

                    vec1[i] = rm.nextInt(10) + 1;

                }

                Date d1 = new Date();
                DateFormat dd1 = new SimpleDateFormat("HH:mm:ss");
                ini = System.nanoTime();
                d1.setTime(ini);

                ExecutorService RX1 = Executors.newFixedThreadPool(Nt);

                for (int i = 0; i < K; ++i) {

                    RX1.execute(new MatrizGranoGrueso(mat1, vec1, K, i));

                }

                RX1.shutdown();

                fin = System.nanoTime();
                d1.setTime(fin);
                System.out.println("Terminado : " + dd1.format(d1) + " tras " + (fin - ini / 1.0e9 + " seg "));

                MatrizGranoGrueso.mostrar();*/

                break;
            }

            System.out.println("Pulse 0 para volver atras");
            sel = tecla.nextInt();

        } while (sel == 0);

    }

}
