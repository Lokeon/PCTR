
package p8;

import java.util.concurrent.*;

/**
 *
 * @author Kevin López Cala
 */

public class usaLectorEscritor implements Runnable {

    private boolean hilo;
    private static lectorEscritor lE = new lectorEscritor();

    public usaLectorEscritor(boolean lectores) {
        this.hilo = lectores;

    }

    public void run() {
        while (true) {
            if (hilo) {
                lE.inicia_escritura();
                lE.fin_escritura();
            } else {

                lE.inicia_lectura();
                lE.fin_lectura();
            }
        }
    }

    public static void main(String[] args) {

        ExecutorService hilo = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 4; ++i) {

            hilo.execute(new usaLectorEscritor(i % 4 == 0));

        }

        hilo.shutdown();
    }

}