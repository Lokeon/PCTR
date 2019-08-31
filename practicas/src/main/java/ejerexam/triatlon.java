package ejerexam;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.Collections;
import java.util.concurrent.*;

public class triatlon implements Runnable {

    private int hilo;
    private Random r = new Random(System.currentTimeMillis());
    private static String[] tipos = new String[] { "natacion", "ciclismo", "carrera" };
    private static CyclicBarrier cb = new CyclicBarrier(100);
    private static ArrayList<Integer> ppl = new ArrayList<Integer>(Collections.nCopies(100, 0));

    public triatlon(int id) {
        this.hilo = id;
    }

    public static void ganador() {
        int i = ppl.lastIndexOf(Collections.min(ppl));
        System.out.println("El maquina es "+ i);
    }

    public void run() {
        for (int i = 0; i < 3; ++i) {
            ppl.set(hilo, ppl.get(hilo) + r.nextInt(10000) + 1);
            try {
                Thread.sleep(ppl.get(hilo));
            } catch (Exception e) {
                e.printStackTrace();
            }

            synchronized (tipos) {
                System.out.println("Termina " + tipos[i] + " : " + hilo);
            }

            try {
                cb.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 100; ++i) {
            pool.execute(new triatlon(i));
        }

        pool.shutdown();
        while (!pool.isTerminated()) {
        }

        ganador();
    }
}