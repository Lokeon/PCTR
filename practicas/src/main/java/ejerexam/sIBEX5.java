package ejerexam;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.*;

public class sIBEX5 extends UnicastRemoteObject implements iIBEX5, Runnable {

    private static int TRI;
    private static int CHN;
    private static int BMM;
    private static int CRR;
    private static int TDC;
    private static Random r = new Random(System.currentTimeMillis());

    public sIBEX5() throws RemoteException {
        TRI = r.nextInt(10);
        CHN = r.nextInt(10);
        BMM = r.nextInt(10);
        CRR = r.nextInt(10);
        TDC = r.nextInt(10);
    }

    public String verValor(int i) throws RemoteException {
        int val = -1;

        switch (i) {
        case 1:
            val = TRI;
            break;
        case 2:
            val = CHN;
            break;
        case 3:
            val = BMM;
            break;
        case 4:
            val = CRR;
            break;
        case 5:
            val = TDC;
            break;
        }
        if (val == -1) {
            return "Ese valor no existe";
        } else {
            return "El valor es " + val;
        }
    }

    public void run() {

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Logger.getLogger(sIBEX5.class.getName()).log(Level.SEVERE, null, e);
            }
            TRI = r.nextInt(100);
            CHN = r.nextInt(100);
            BMM = r.nextInt(100);
            CRR = r.nextInt(100);
            TDC = r.nextInt(100);
        }

    }

    public String consultaTodo() throws RemoteException {
        return ("TRI=" + TRI + "\nCHN=" + CHN + "\nBMM=" + BMM + "\nCRR=" + CRR + "\nTDC=" + TDC);
    }

    public static void main(String[] args) throws Exception {
        sIBEX5 server = new sIBEX5();
        Naming.bind("rmi:/localhost:2020/ibex", server);
        System.out.println("Server operativo");

        ExecutorService pool = Executors.newSingleThreadExecutor();
        pool.execute(server);

    }

}