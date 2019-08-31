package ejerexam;

import java.util.*;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.*;

public class wireWorld implements Runnable {

    private static CyclicBarrier cb = new CyclicBarrier(Runtime.getRuntime().availableProcessors());
    private static int[][] matriz;
    private static int[][] aux;
    private static int N, niter;
    private int inicio, fin;

    public wireWorld(int inicio, int fin) {
        this.inicio = inicio;
        this.fin = fin;
    }

    public static void iniAlt() {
        Random r = new Random(System.currentTimeMillis());
        Scanner tecla = new Scanner(System.in);

        System.out.println("Introduzca la dimension de la reticula: ");
        N = tecla.nextInt();

        matriz = new int[N][N];
        aux = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; ++j) {
                matriz[i][j] = r.nextInt(4);
            }
        }

        System.out.println("Introduzca el numero de iteraciones: ");
        niter = tecla.nextInt();
    }

    public static void iniMan() {
        Scanner tecla = new Scanner(System.in);

        System.out.println("Introduzca la dimension de la reticula: ");
        N = tecla.nextInt();
        matriz = new int[N][N];
        aux = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; ++j) {
                System.out.println("Introduzca el valor del elemento [" + i + "][" + j + "]");
                matriz[i][j] = tecla.nextInt();
            }
        }

        System.out.println("Introduzca el numero de iteraciones: ");
        niter = tecla.nextInt();
    }

    public void status() {
        for (int i = 0; i < matriz.length; ++i) {
            for (int j = 0; j < matriz.length; ++j) {
                System.out.println(matriz[i][j] + "");
            }
        }
    }

    public void generaciones() {
        for (int i = inicio; i < fin; ++i) {
            for (int j = 0; j < N; ++j) {
                funcTransicion(i, j);
            }
        }
    }

    public void actualizar() {
        for (int i = inicio; i < fin; ++i) {
            for (int j = 0; j < matriz.length; ++j) {
                matriz[i][j] = aux[i][j];
            }
        }
    }

    public int contarVecinos(int a, int b) {
        int vec = 0;

        for (int i = a - 1; i <= a + 1; ++i) {
            for (int j = b - 1; j <= j + 1; ++j) {
                if (i != b) {
                    try {
                        if (matriz[Math.floorMod(i, N)][Math.floorMod(j, N)] == 2) {
                            vec++;
                        }
                    } catch (Exception e) {
                        // TODO: hasndle exception
                    }
                }
            }
        }

        return vec;
    }

    public void funcTransicion(int a, int b) {
        int vec;

        if (matriz[a][b] != 0) {
            if (matriz[a][b] == 1) {
                aux[a][b] = 2;
            } else {
                if (matriz[a][b] == 2) {
                    aux[a][b] = 3;
                } else {
                    if (matriz[a][b] == 3) {
                        vec = contarVecinos(a, b);

                        if (vec == 1 || vec == 2) {
                            aux[a][b] = 1;
                        }
                    }
                }
            }
        }
    }

    public void run() {
        for (int i = 0; i < niter; ++i) {
            if (inicio == 0) {
                status();
            }

            generaciones();

            try {
                cb.await();
            } catch (Exception e) {
                e.printStackTrace();
            }

            actualizar();
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("1.- Introducir a mano \n2.- Introducir aleat.");
        int c = scan.nextInt();
        int nNuc = Runtime.getRuntime().availableProcessors();

        switch (c) {
        case 1:
            iniMan();
            break;

        case 2:
            iniAlt();
            break;
        }

        ThreadPoolExecutor pool = new ThreadPoolExecutor(nNuc, nNuc, 6000, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        int inic, fina;
        for (int i = 0; i < nNuc; ++i) {
            inic = i * (N / nNuc);
            if (i == nNuc - 1)
                fina = N;
            else
                fina = i * (N / nNuc) + N / nNuc;

            pool.execute(new wireWorld(inic, fina));
        }

        pool.shutdown();
        while (!pool.isTerminated()) {
        }
    }
}