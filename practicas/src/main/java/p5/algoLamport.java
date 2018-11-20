
package p5;

import java.util.Collections;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Kevin Lopez Cala
 */
public class algoLamport implements Runnable {

    private static volatile Vector<Integer> vec = new Vector<Integer>(1);
    private static volatile Vector<Boolean> Elegir = new Vector<Boolean>(1);
    private static int inCS = 0;
    private int hilo;

    public algoLamport() {
        Elegir.add(false);
        hilo = vec.size();
        vec.add(0);
    }

    private void lock(int indice) {
        Elegir.set(indice, true);
        int maximo = Collections.max(vec);
        vec.set(indice, 1 + maximo);
        Elegir.set(indice, false);

        for (int i = 0; i < Elegir.size(); ++i) {
            if (i != indice) {
                while (Elegir.get(i)) {
                    Thread.yield();
                }
                while (vec.get(i) != 0
                        && ((vec.get(i) < vec.get(indice) || (vec.get(indice) == vec.get(i) && indice > i)))) {
                    Thread.yield();
                }
            }
        }
    }

    private void unlock(int indice) {
        vec.set(indice, 0);
    }

    public void run() {

        lock(hilo);
        inCS++;
        System.out
                .println("Number of processes in critical section" + (Thread.currentThread().getName()) + ": " + inCS);
        inCS--;
        unlock(hilo);
    }

    public static void main(String[] arg) throws Exception {

        ExecutorService exec = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 3; ++i) {
            exec.execute(new algoLamport());
        }
        exec.shutdown();
    }

}