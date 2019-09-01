package ejerexam;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.ArrayList;

public class numeroPerfe implements Callable<Integer> {

    int ini, fin;
    static ArrayList<Future<Integer>> arl = new ArrayList<Future<Integer>>();
    static int total = 0;

    public numeroPerfe(int ini, int fin) {
        this.ini = ini;
        this.fin = fin;
    }

    public Integer call() {

        int cont = 0;
        for (int i = ini; i <= fin; i++) {
            int aux = 0;
            for (int j = 1; j < i; j++) {
                if (i % j == 0)
                    aux += j;
            }
            if (i == aux && i != 0) {
                System.out.println("Numero: " + i);
                cont++;
            }
        }
        return cont;
    }

    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        // int tareas = Integer.parseInt(args[1]);
        int tareas = 8;
        int parts = (n + tareas - 1) / tareas;

        ExecutorService pool = Executors.newFixedThreadPool(tareas);

        for (int i = 0; i < tareas; ++i) {
            int ini = i * parts;
            int fini = Math.min(parts * (i + 1), n);
            System.out.println(ini + " " + fini);

            arl.add(pool.submit(new numeroPerfe(ini, fini)));
        }

        pool.shutdown();
        while (!pool.isTerminated()) {
        }

        int res = 0;

        for (int i = 0; i < tareas; ++i) {
            try {
                res += arl.get(i).get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println(res);

    }

}