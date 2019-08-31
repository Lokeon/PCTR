package ejerexam;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class prodEscalar implements Callable<Float> {
    private int ini, fin;
    private static Float[] v1 = new Float[10000];
    private static Float[] v2 = new Float[10000];

    public static void rellenar() {
        for (int i = 0; i < 10000; ++i) {
            v1[i] = 1f;
            v2[i] = 1f;
        }
    }

    public prodEscalar(int ini, int fin) {
        this.ini = ini;
        this.fin = fin;
    }

    public Float call() {
        Float res = 0f;

        for (int i = ini; i < fin; ++i) {
            res += (v1[i] * v2[i]);
        }

        return res;
    }

    public static void main(String[] args) {

        Float res = 0f;
        int cores = Runtime.getRuntime().availableProcessors();
        ArrayList<Future<Float>> list = new ArrayList<Future<Float>>();
        ExecutorService pool = Executors.newFixedThreadPool(cores);

        rellenar();

        long init = System.nanoTime();
        for (int i = 0; i < cores; ++i) {
            list.add(pool.submit(new prodEscalar(i * 10000, (i * 10000 / cores) + 10000 / cores)));
        }

        pool.shutdown();
        while (!pool.isTerminated()) {
        }

        for (int i = 0; i < cores; ++i) {
            try {
                res += list.get(i).get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        long endt = System.nanoTime() - init;

        System.out.println("El tiempo total es: " + endt);

    }
}