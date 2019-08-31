package ejerexam;

import java.util.*;
import java.util.concurrent.*;

public class filtroMedio implements Runnable {

    private static int[][] mati;
    private static double[][] matd;

    private static int N, iter;
    private int inicio, fin;
    private int f = 1;

    public filtroMedio(int i, int f) {
        inicio = i;
        fin = f;
    }

    public static int iters() {
        return iter;
    }

    public static void status() {
        for (int i = 0; i < mati.length; ++i) {
            for (int j = 0; j < mati.length; ++j) {
                System.out.println(mati[i][j] + "");
            }
        }
    }

    public static void statusd() {
        for (int i = 0; i < matd.length; ++i) {
            for (int j = 0; j < matd.length; ++j) {
                System.out.println(matd[i][j] + "");
            }
        }
    }

    public static void matrizManual() {
        Scanner tecla = new Scanner(System.in);
        System.out.println("Introduzca el tamaño de la reticula: ");
        N = tecla.nextInt();
        mati = new int[N][N];
        matd = new double[N][N];

        for (int i = 0; i < mati.length; ++i) {
            for (int j = 0; j < mati.length; ++j) {
                System.out.println("Introduzca el valor del elemento [" + i + "][" + j + "]");
                mati[i][j] = tecla.nextInt();
            }
        }

        status();

        System.out.println("Introduzca el numero de tareas: ");
        iter = tecla.nextInt();

    }

    public static void matrizAleatoria() {
        Random r = new Random(System.currentTimeMillis());
        Scanner tecla = new Scanner(System.in);
        System.out.println("Introduzca el tamaño de la reticula: ");
        N = tecla.nextInt();
        mati = new int[N][N];
        matd = new double[N][N];

        for (int i = 0; i < mati.length; ++i) {
            for (int j = 0; j < mati.length; ++j) {
                mati[i][j] = r.nextInt(256);
            }
        }

        System.out.println("Introduzca el numero de tareas: ");
        iter = tecla.nextInt();
    }

    public double aplicaFiltro(int i, int j) {
        double res = 0;

        for (int k = i - f; k <= f + i; ++k) {
            for (int r = j - f; r <= f + j; ++r) {
                try {
                    res += mati[i][j];
                } catch (ArrayIndexOutOfBoundsException e) {
                    res += 0;
                }
            }
        }

        return (double) 1.0 / Math.pow((2 * f) + 1, 2) * res;
    }

    public void run() {
        for (int i = inicio; i < fin; ++i) {
            for (int j = 0; j < mati.length; ++i) {
                matd[i][j] = aplicaFiltro(i, j);
            }
        }
    }

    public static void main(String[] args) {

        int op;
        int cores = Runtime.getRuntime().availableProcessors();
        Scanner tecla = new Scanner(System.in);
        System.out.println("\t1-MatrizManual\n\t2-MatrizAleatoria");
        op = tecla.nextInt();
        switch (op) {
        case 1:
            matrizManual();
            break;
        case 2:
            matrizAleatoria();
            break;
        }

        ThreadPoolExecutor pool = new ThreadPoolExecutor(cores, cores, 60000L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());

        int inic, fina;
        long t = System.nanoTime();
        for (int i = 0; i < iters(); ++i) {
            inic = i * (N / iters());
            if (i == iters() - 1)
                fina = N;
            else
                fina = (i + 1) * (N / iters());

            pool.submit(new filtroMedio(inic, fina));
        }

        pool.shutdown();
        while (!pool.isTerminated()) {
        }
        long tiempoT = System.nanoTime() - t;
        statusd();
        System.out.println("Tiempo total: " + tiempoT / 1000 + " microsegundos.");
    }
}