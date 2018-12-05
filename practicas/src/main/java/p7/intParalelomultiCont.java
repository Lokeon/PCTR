package p7;

import java.util.*;
import java.util.concurrent.*;

public class intParalelomultiCont extends Thread {

    private double sum = 0;
    static double res;
    private int iteraciones;
    private static int iteracionesGlobal = 0;
    private Random r = new Random();

    public intParalelomultiCont(int i) {
        iteraciones = i;
        sum = 0;
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

    public double getSum() {
        return sum;
    }

    public static double getResult(intParalelomultiCont[] threads) {
        double total = 0;
        for (int i = 0; i < threads.length; ++i) {
            total += threads[i].getSum();
        }

        return res = ((1.0 / iteracionesGlobal) * total);
    }

    public void run() {

        for (double i = 0.0; i < iteraciones; ++i) {
            sum += Math.sin(r.nextDouble());
        }

    }

    public static void main(String[] args) {

        int Nt = Subramanian(Runtime.getRuntime().availableProcessors(), 0.0);

        intParalelomultiCont[] thread = new intParalelomultiCont[Nt];

        for (int i = 0; i < Nt; ++i) {
            thread[i] = new intParalelomultiCont(100000);
            thread[i].start();
        }

        try {
            for (int i = 0; i < Nt; ++i) {
                thread[i].join();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(getResult(thread));
    }
}