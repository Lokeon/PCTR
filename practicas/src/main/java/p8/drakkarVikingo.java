package p8;

import java.util.concurrent.*;

/**
 *
 * @author Kevin López Cala
 */
public class drakkarVikingo implements Runnable {
    private static int marmita = 8;
    private boolean thilo;

    public drakkarVikingo(boolean hilo) {
        thilo = hilo;

    }

    public synchronized void comer() {

        while (marmita == 0) {

            notifyAll();
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        marmita--;
        System.out.println("Marmitas restantes : " + marmita);

    }

    public synchronized void llenar() {

        int i = 0;
        while (marmita > 0) {

            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        System.out.println("Cocinero repone marmitas");
        notifyAll();
    }

    public void run() {

        while (true) {
            if (thilo) {
                llenar();
            } else {
                comer();
            }
        }

    }

    public static void main(String[] args) throws Exception {

        ExecutorService hilo = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 4; ++i) {

            hilo.execute(new drakkarVikingo(i % 4 == 0)); // vikingos a la vez

        }

    }

}
