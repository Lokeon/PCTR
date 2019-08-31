package ejerexam;

public class pruebaThrSum extends Thread {
    private int ini, fin;
    public static Object lock = new Object();

    public pruebaThrSum(int i, int j) {
        ini = i;
        fin = j;
    }

    public void run() {
        int cont = 0;

        for (int i = ini; i < fin; ++i) {
            cont += ThrSum.datArr[i];
        }

        synchronized (lock) {
            ThrSum.cont += cont;
        }
    }
}