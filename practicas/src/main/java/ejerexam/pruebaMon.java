package ejerexam;

import p3.hilo;

public class pruebaMon extends Thread {

    static monitorSem mon;
    static int var_com = 0;

    public static void createMon(int permits) {
        mon = new monitorSem(permits);
    }

    public void run() {

        for (int i = 0; i < 500; ++i) {
            mon.waitSem();
            var_com++;
            mon.signalSem();
        }
    }

    public static void main(String[] args) {
        Thread hilo1 = new Thread(new pruebaMon());
        Thread hilo2 = new Thread(new pruebaMon());

        createMon(1);

        hilo1.start();
        hilo2.start();

        try {
            hilo1.join();
        } catch (Exception e) {
            // TODO: handle exception
        }

        try {
            hilo2.join();
        } catch (Exception e) {
            // TODO: handle exception
        }

        System.out.println(var_com);
    }
}