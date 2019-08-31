package ejerexam;

import java.math.BigInteger;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.*;

public class factGrand implements Callable<BigInteger> {

    private int ini, fin;

    public factGrand(int ini, int fin) {
        this.ini = ini;
        this.fin = fin;
    }

    public BigInteger call() {
        BigInteger res = new BigInteger("1");
        for (int i = ini; i < fin; ++i) {
            if (i != 0) {
                res = res.multiply(new BigInteger(Integer.toString(1)));
            }
        }
        return res;
    }

    public static void main(String[] args) {

        Scanner tecla = new Scanner(System.in);
        int ini, fin;

        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService pool = Executors.newFixedThreadPool(cores);
        System.out.println("Introduzca un numero: ");

        int num = tecla.nextInt();

        ArrayList<Future<BigInteger>> sal = new ArrayList<Future<BigInteger>>();
        for (int i = 0; i < cores; ++i) {
            ini = i * (num / cores);
            if (i != cores - 1) {
                fin = (i * (num / cores) + (num / cores));
            } else {
                fin = num;
            }
            sal.add(pool.submit(new factGrand(ini, fin)));
        }

        pool.shutdown();
        while (!pool.isTerminated()) {
        }

        for (int i = 0; i < cores; ++i) {
            try {
                System.out.println(sal.get(i).get().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        BigInteger res = new BigInteger("1");

        for (int i = 0; i < cores; ++i) {
            try {
                res = res.multiply(sal.get(i).get());
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

        System.out.println("El resultado es: " + res.toString());
    }
}