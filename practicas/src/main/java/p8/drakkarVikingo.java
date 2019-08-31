package p8;

import java.util.concurrent.*;

/**
 *
 * @author Kevin López Cala
 */
public class drakkarVikingo implements Runnable {
    private static Marmita mm = new Marmita(8);
    private boolean thilo;

    public drakkarVikingo(boolean hilo) {
        thilo = hilo;

    }

    public void run() {

        while (true) {
            if (thilo) {
                mm.llenar();
            } else {
                mm.comer();
            }
        }

    }

    public static void main(String[] args) {

        ExecutorService hilo = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 4; ++i) {

            hilo.execute(new drakkarVikingo(i % 4 == 0)); // vikingos a la vez

        }
        hilo.shutdown();

    }
}
    class Marmita {

        private int marmita;

        public Marmita(int marmita) {
            this.marmita = marmita;
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
            System.out.println("Marmitas restantes : " + marmita);
            --marmita;

        }

        public synchronized void llenar() {

            while (marmita > 0) {

                try {
                    wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            marmita = 8;
            System.out.println("Cocinero repone marmitas");
            notifyAll();
        }

    }

