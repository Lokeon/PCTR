package p7;

import java.util.*;
import java.util.concurrent.*;

public class intParalelauniCont implements Runnable {

    static double sum = 0;
    static double res;
    private int iteraciones;
    private static int iteracionesGlobal = 0;
    private static Object lock = new Object();
    private Random r = new Random();

    public intParalelauniCont(int i) {
        iteraciones = i;
        iteracionesGlobal += i;

    }

    /**
     * Método para calcular el número de hilos
     * 
     * @param Nldn número de cores lógicos
     * @param Cb   Coeficiente de bloqueo
     * @return número de hilos
     */
    public static int Subramanian(int Nldn, double Cb) {

        double Nt = (double) Nldn / (1 - Cb);
        int res = (int) Math.round(Nt);
        System.out.println("Numero de hilos totales " + res);

        return res;

    }

    public static double getResult() {
        return res = ((1.0 / iteracionesGlobal) * sum);
    }

    public void run() {

        synchronized (lock) {

            for (double i = 0.0; i < iteraciones; ++i) {
                sum += Math.sin(r.nextDouble());
            }
        }

    }

    public static void main(String[] args) {

        int Nt = Subramanian(Runtime.getRuntime().availableProcessors(), 0.0);

        ExecutorService Monte = Executors.newFixedThreadPool(Nt);

        for (int i = 0; i < Nt; ++i) {
            Monte.execute(new intParalelauniCont(100000));
        }

        Monte.shutdown();
        while (!Monte.isTerminated()) {
        }

        System.out.println(getResult());

    }

}