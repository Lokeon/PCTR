package ejerexam;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class rend {

    private static int nNuc = Runtime.getRuntime().availableProcessors();
    private static final long nIter = 100000L;
    private static Object lock = new Object();
    private static ReentrantLock relock = new ReentrantLock();
    private static AtomicLong atm = new AtomicLong(0);
    private static long valR = 0;
    private static long valS = 0; 
    private static ExecutorService pool = null;

    private static void reeLock() {
        pool = Executors.newFixedThreadPool(nNuc);
        for (int i = 0; i < 5000; ++i) {
            pool.execute(new Runnable() {
                public void run() {
                    relock.lock();
                    valR++;
                    relock.unlock();
                }
            });
        }
        pool.shutdown();
        while (!pool.isTerminated()) {
        }
    }

    private static void sync() {
        pool = Executors.newFixedThreadPool(nNuc);
        for (int i = 0; i < 5000; ++i) {
            pool.execute(new Runnable() {
                public void run() {
                    synchronized (lock) {
                        valS++;
                    }
                }
            });

        }
        pool.shutdown();
        while (!pool.isTerminated()) {

        }
    }

    private static void atom() {
        pool = Executors.newFixedThreadPool(nNuc);
        for (int i = 0; i < 5000; ++i) {
            pool.execute(new Runnable() {
                public void run() {
                    atm.incrementAndGet();
                }
            });

        }
        pool.shutdown();
        while (!pool.isTerminated()) {
        }

    }

    public static void main(String[] args) {
        int iters = (int) nIter / 5000;
        long iniR, finR, iniA, finA, iniS, finS;

        for (int i = 0; i < iters; ++i) {

            iniR = System.currentTimeMillis();
            reeLock();
            finR = System.currentTimeMillis() - iniR;

            iniA = System.currentTimeMillis();
            atom();
            finA = System.currentTimeMillis() - iniA;

            iniS = System.currentTimeMillis();
            sync();
            finS = System.currentTimeMillis() - iniS;

            System.out.println("Iteracion:" + (i + 1));
            System.out.println("El tiempo con ReentrantLock: " + finR);
            System.out.println("El tiempo con AtomicLong: " + finA);
            System.out.println("El tiempo con Bloque Sincronizado: " + finS + "\n");
        }
    }

}