
package ejerexam;

import java.util.concurrent.*;

import java.util.Random;

public class Life implements Runnable {

    private int hilos;
    private static int N = 10;
    private static int nCelvivas;
    private static int[][] matriz = new int[N][N];
    private static int[][] aux = new int[N][N];
    private static int nNuc = 10;
    private static CyclicBarrier cb = new CyclicBarrier(nNuc);

    public Life(int hilo) {
        hilos = hilo;
    }

    public static void inciarMatriz() {
        Random r = new Random();
        nCelvivas = 0;

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (Math.round(r.nextFloat()) == 1) {
                    matriz[i][j] = 1;
                    nCelvivas++;
                } else {
                    matriz[i][j] = 0;
                }
            }
        }
    }

    public synchronized int nVecinos(int a, int b) {
        int nV = 0;

        for (int i = a - 1; i <= a + 1; ++i) {
            for (int j = b - 1; j <= b + 1; ++j) {
                if (i != j && (i % N != j)) {
                    nV += matriz[a % N][b % N];
                }
            }
        }

        return nV;
    }

    public synchronized void nextGen(int fila) {
        int nV;

        for (int i = 0; i < N; ++i) {
            nV = nVecinos(fila, i);

            if (matriz[fila][i] == 1) // Esta vivita
            {
                if (nV < 2 || nV > 3) {
                    aux[fila][i] = 0;
                    nCelvivas--;
                }
            } else {
                if (nV == 3) {
                    aux[fila][i] = 1;
                    nCelvivas++;
                }
            }
        }
    }

    public synchronized void status() {
        System.out.println("Soy el hilo " + hilos + " tengo prioridad " + Thread.currentThread().getPriority() + " hay "
                + nCelvivas + " celulas vivas");
    }

    public void run() {

        nextGen(hilos);
        status();

        try {
            cb.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        matriz = aux;
    }

}