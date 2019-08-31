package ejerexam;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;
import java.util.Random;

import javafx.util.*;

public class sHelion extends UnicastRemoteObject implements iHelion {

    private int nCiudades;
    private int[][] helion = new int[200][100];
    private Pair<Integer, Integer> blackHole;

    public sHelion() throws RemoteException {
        super();
    }

    public boolean arminsticio() throws RemoteException {
        if (nCiudades < 100 || helion[blackHole.getKey()][blackHole.getValue()] == 0) {
            return true;
        } else
            return false;
    }

    public void ojiva() throws RemoteException {
        Random r = new Random(System.currentTimeMillis());

        int a, b;
        a = r.nextInt(200);
        b = r.nextInt(100);

        if (helion[a][b] != 0) {
            helion[a][b] = 0;
            nCiudades--;
        }
    }

    public void status() throws RemoteException {
        System.out.println("Quedan " + nCiudades + " ciudades");
    }

    public void iniHelion() throws RemoteException {
        Random r = new Random(System.currentTimeMillis());
        nCiudades = 0;
        int a, b;
        boolean salir = false;

        while (nCiudades != 2500) {
            a = r.nextInt(200);
            b = r.nextInt(100);

            if (helion[a][b] == 0) {
                helion[a][b] = 1;
                nCiudades++;
            }
        }

        while (!salir) {
            a = r.nextInt(200);
            b = r.nextInt(100);

            if (helion[a][b] == 0) {
                helion[a][b] = 2;
                salir = true;
                blackHole = new Pair<Integer, Integer>(a, b);
            }
        }

    }

    public static void main(String[] args) throws Exception {
        sHelion server = new sHelion();
        Naming.bind("//localhost:2020/helion", server);
        System.out.println("Server up");
    }
}