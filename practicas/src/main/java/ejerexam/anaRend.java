package ejerexam;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class anaRend implements Runnable {
    private int hilo;
    private int cont = 0;
    private int tareas = 100;
    private Object lock = new Object();
    private static CyclicBarrier cb = new CyclicBarrier(Runtime.getRuntime().availableProcessors());
    private static AtomicInteger at = new AtomicInteger(0);
    private static ReentrantLock rl = new ReentrantLock();

    public anaRend(int i) {
        hilo = i;
    }

    public void incSync() {
        for (int i = 0; i < tareas; ++i) {
            synchronized (lock) {
                cont++;
            }
        }
    }

    public void incReen() {
        for (int i = 0; i < tareas; ++i) {
            rl.lock();
            cont++;
            rl.unlock();
        }
    }

    public void incAtom() {
        for (int i = 0; i < tareas; ++i) {
            at.incrementAndGet();
        }
    }

    public void run() {

        long t = System.nanoTime();
        incSync();
        try {
            cb.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long tiempo = System.nanoTime() - t;
        if (hilo == 0) {
            System.out.println("Tiempo synchronized:  " + tiempo);
        }

        t = System.nanoTime();
        incReen();
        try {
            cb.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tiempo = System.nanoTime() - t;
        if (hilo == 0) {
            System.out.println("Tiempo ReentrantLock: " + tiempo);
        }

        t = System.nanoTime();
        incAtom();
        try {
            cb.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tiempo = System.nanoTime() - t;
        if (hilo == 0) {
            System.out.println("Tiempo AtomicInteger: " + tiempo);
        }

    }

    public static void main(String[] args) {

        int cores = Runtime.getRuntime().availableProcessors();

        ExecutorService pool = Executors.newFixedThreadPool(cores);

        for (int i = 0; i < cores; ++i) {
            pool.submit(new anaRend(i));
        }

        pool.shutdown();
        while (!pool.isTerminated()) {
        }
    }
}