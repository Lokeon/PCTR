package ejerexam;

import java.math.BigInteger;
import java.rmi.Naming;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class cRMI implements Runnable {
    static iRMI server = null;
    int n;

    public cRMI(int n) {
        this.n = n;
    }

    public void run() {
        try {
            BigInteger aux = server.factorial(n);
            System.out.println(aux.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int nNuc = Runtime.getRuntime().availableProcessors();

        ThreadPoolExecutor pool = new ThreadPoolExecutor(nNuc, nNuc, 6000L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        try {
            server = (iRMI) Naming.lookup("//localhost:5050/server");
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 200; ++i) {
            pool.execute(new cRMI(i));
        }

        pool.shutdown();
        while (!pool.isTerminated()) {
        }
    }

}
