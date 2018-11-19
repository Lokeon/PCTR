
package p5;

import java.util.*;
import java.util.concurrent.*;

public class algoLamport implements Runnable {

    private static int[] vec;
    private static boolean[] Elegir;
    private static volatile int inCS = 0;
    private int hilo;
    private int N;

    public algoLamport(int hilo, int N) {

        this.hilo = hilo;
        this.N = N;
        Elegir = new boolean[N];
        vec = new int[N];

        for (int i = 0; i < this.N; ++i) {

            vec[i] = 0;
            Elegir[i] = false;

        }

    }

    public void run() {

        Elegir[hilo] = true;
        vec[hilo] = 1 + Math.max(vec[hilo], vec[hilo]);
        Elegir[hilo] = false;

        for (int j = 0; j < N; ++j) {
            while (Elegir[j]) {
            }
            while ((vec[j] != 0) && (vec[j] < vec[hilo])) {
            }

        }
        inCS++;
        System.out.println("Number of processes in critical section" + (hilo) + ": " + inCS);
        inCS--;

        vec[hilo] = 0;

    }

    public static void main(String[] arg) throws Exception {

        System.out.println("Introduzca nº de procesos");
        Scanner tecla = new Scanner(System.in);
        int N = tecla.nextInt();

        ExecutorService exec = Executors.newFixedThreadPool(N);

        for (int i = 0; i < N; ++i) {

            exec.execute(new algoLamport(i, N));

        }

        exec.shutdown();
        tecla.close();
    }

}