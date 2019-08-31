package ejerexam;

import java.rmi.Naming;
import java.util.concurrent.CyclicBarrier;

public class cDiane implements Runnable {

    private static iDiane server;
    private static CyclicBarrier lock = new CyclicBarrier(10);
    private int hilo;

    public cDiane(int id) {
        hilo = id;
    }

    public void run() {

        try {
            for (int i = 0; i < 4; ++i) {
                System.out.println("El cliente " + hilo + " ha impactado en " + server.tirarDardo());
            }
            System.out.println("El cliente " + hilo + " puntuacion:  " + server.puntuacion());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        server = (iDiane) Naming.lookup("//localhost:2020/diane");
    }
}