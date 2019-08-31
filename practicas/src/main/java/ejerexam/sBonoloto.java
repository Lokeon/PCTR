package ejerexam;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class sBonoloto extends UnicastRemoteObject implements iBonoloto {

    private static int[] serie;
    private Random r;

    public sBonoloto() throws RemoteException {
        super();
    }

    public sBonoloto(int tam) throws RemoteException {
        serie = new int[tam];
        r = new Random(System.currentTimeMillis());

        for (int i = 0; i < serie.length; ++i) {
            serie[i] = r.nextInt(49) + 1;
        }
    }

    public static void mostarSerie() {
        for (int i : serie) {
            System.out.println(i + " ");
        }
    }

    public boolean apuesta(int[] array) throws RemoteException {

        for (int i = 0; i < serie.length; ++i) {
            if (serie[i] != array[i])
                return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {

        sBonoloto server = new sBonoloto(6);

        Naming.bind("Servidor", server);

        System.out.println("Server up. Running...");
        mostarSerie();

    }
}