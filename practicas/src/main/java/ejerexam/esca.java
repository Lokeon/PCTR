package ejerexam;

import java.util.concurrent.Callable;
import java.util.*;
import java.util.concurrent.*;

public class esca implements Callable<Float> {

    int ini, fin;
    static Float[] v1 = new Float[1000];
    static Float[] v2 = new Float[1000];

    public esca(int in, int f) {
        ini = in;
        fin = f;
    }

    public static void rellenar() {
        for (int i = 0; i < 1000; ++i) {
            v1[i] = 1f;
            v2[i] = 1f;
        }
    }

    public Float call() {

        Float res = 0f;
        Float cont;

        for (int i = ini; i < fin; ++i) {
            cont = (v1[i] * v2[i]);
            res += cont;
        }

        return res;
    }

    public static void main(String[] args) {
        rellenar();

        int nNuc = Runtime.getRuntime().availableProcessors();

        ArrayList<Future<Float>> arl = new ArrayList<Future<Float>>();
        ExecutorService pool = Executors.newFixedThreadPool(nNuc);

        Float res = 0f;

        long t1 = System.currentTimeMillis();

        for (int i = 0; i < nNuc; ++i) {
            arl.add(pool.submit(new esca(i * 1000 / nNuc, (i * 1000 / nNuc) + 1000 / nNuc)));
        }

        pool.shutdown();

        for (int i = 0; i < nNuc; ++i) {
            try {
                res += arl.get(i).get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        long t2 = System.currentTimeMillis() - t1;

        System.out.println("El tiempo total ha sido " + t2 + " milisegundos");
    }
}