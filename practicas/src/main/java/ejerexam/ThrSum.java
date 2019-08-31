
package ejerexam;

import java.util.*;

public class ThrSum {
    static Random r = new Random();
    public static int cont = 0;
    public static int ini, fin;
    public static int[] datArr = new int[10];

    public static void main(String[] args) {
        Thread hilos[] = new Thread[5];

        for (int i = 0; i < 10; ++i) {
            datArr[i] = r.nextInt(10) + 1;
        }

        for (int i = 0; i < 5; ++i) {
            hilos[i] = new pruebaThrSum(i * (10 / 5), (i + 1) * (10 / 5));
            hilos[i].start();
        }

        for (int i = 0; i < 5; ++i) {
            try {
                hilos[i].join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 10; ++i) {
            System.out.println(datArr[i]);
        }

        System.out.println(" la suma es: " + cont);
    }
}