package p5;

import java.util.concurrent.*;

/**
 * @author Kevin Lopez Cala
 */
public class algEisenbergMcGuire implements Runnable {

        public static enum estado {
                AUS, ESPERANDO, ACTIVO
        };

        private static estado flags[] = {estado.AUS,estado.AUS};
        private static volatile int inCS = 0;
        private static int turn = 0;
        private int i = 0 ;
        private static int process = 2;

        public algEisenbergMcGuire(int pros) {
                i = pros;
        }

        public void run() {
                int indice;
                do {
                        flags[i] = estado.ESPERANDO;
                        indice = turn;

                        while (indice != i) {
                                if (flags[indice] != estado.AUS) {
                                        indice = turn;
                                } else {
                                        indice = (indice + 1) % process;
                                }
                        }
                        flags[i] = estado.ACTIVO;
                        indice = 0;
                        while ((indice < process) && ((indice == i) || (flags[turn] != estado.ACTIVO))) {
                                indice++;
                        }

                } while ((indice >= process) && ((turn == i) || (flags[turn] == estado.ESPERANDO)));

                turn = i;

                /* CRITICAL SECTION */

                inCS++;
                System.out.println("Number of processes in critical section" + (Thread.currentThread().getName()) + ": "
                                + inCS);
                inCS--;

                indice = (turn + 1) % process;
                while (flags[indice] == estado.AUS) {
                        indice = (indice + 1) % process;
                }
                turn = indice;
                flags[i] = estado.AUS;
        }

        public static void main(String[] args) throws Exception {

                ExecutorService h1 = Executors.newFixedThreadPool(2);
                h1.execute(new algEisenbergMcGuire(0));
                h1.execute(new algEisenbergMcGuire(1));
                h1.shutdown();
        }
}