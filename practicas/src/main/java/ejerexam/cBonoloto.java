package ejerexam;

import java.rmi.Naming;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.rmi.*;

public class cBonoloto implements Runnable {

    private static Random r;
    private static iBonoloto Ref;
    private int hilo;

    public cBonoloto(int i) {
        this.hilo = i;
    }

    public int[] apuestaCliente() {
        int[] apuesta = new int[6];

        for (int i = 0; i < 6; ++i) {
            apuesta[i] = r.nextInt(49) + 1;
        }

        return apuesta;
    }

    public void run() {

        while (true) {
            synchronized (r) {

                try {

                    if (Ref.apuesta(apuestaCliente())) {
                        System.out.println("El ganador es el cliente" + hilo);
                        try {
                            Thread.sleep(5000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("El cliente " + hilo + " no ha acertado la serie");
                    }

                } catch (RemoteException e) {

                }

            }

        }

    }

    public static void main(String[] args) throws Exception {

        r = new Random(System.currentTimeMillis());
        Ref = (iBonoloto) Naming.lookup("Servidor");

        ExecutorService pool = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 100; ++i) {
            pool.execute(new cBonoloto(i));
        }

        pool.shutdown();
        while (!pool.isTerminated()) {
        }

    }
}