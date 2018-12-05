package p7;

import java.io.RandomAccessFile;
import java.util.concurrent.*;

public class ficheroSeguro implements Runnable {

    private int hilo;
    private static int cont = 0;
    private static RandomAccessFile file;
    private static Object lock = new Object();

    public ficheroSeguro(int i) {
        hilo = i;
    }

    public void run() {

        synchronized (lock) {
            while (hilo != cont) {
                try {
                    lock.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                file.writeBytes("Hilo " + hilo + " escribiendo datos\r\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
            cont++;
            lock.notifyAll();
        }
    }

    public static void main(String[] args) {

        try {
            file = new RandomAccessFile("src/main/java/p7/fichero.txt", "rw");
        } catch (Exception e) {
            e.printStackTrace();
        }

        long ini = System.currentTimeMillis();
        ExecutorService pool = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 1000; ++i) {
            pool.execute(new ficheroSeguro(i));
        }

        pool.shutdown();
        while (!pool.isTerminated()) {
        }

        try {
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Tiempo " + (System.currentTimeMillis() - ini) + " ms");
    }

}