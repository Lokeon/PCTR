package ejerexam;

import java.util.*;
import java.rmi.RemoteException;
import java.rmi.Naming;

public class primesC {
    static primesI servidor;
    int iniRange, endRange, subRange;

    public void menu() {
        Scanner tecla = new Scanner(System.in);
        System.out.println("Introduzca el inicio del rango");
        iniRange = tecla.nextInt();
        System.out.println("Introduzca el fin del rango");
        endRange = tecla.nextInt();
        System.out.println("Introduzca el numero de subrangos");
        subRange = tecla.nextInt();
    }

    public int cantPrimos() throws RemoteException {
        int cont = 0, fin, ini;

        for (int i = 0; i < subRange; ++i) {
            ini = (i * (endRange - iniRange) / subRange) + iniRange;
            if (i == subRange - 1) {
                fin = endRange + 1;
            } else {
                fin = ((i + 1) * (endRange - iniRange) / subRange) + iniRange;
            }

            cont += servidor.primesInRange(ini,fin);
        }

        return cont ;
    }

    public static void main(String[] args) throws RemoteException {
        try {
            servidor = (primesI) Naming.lookup("//localhost:1099/servidor");
        } catch (Exception e) {
            e.printStackTrace();
        }

        primesC prim = new primesC();
        prim.menu();

        int primos = prim.cantPrimos();
        System.out.println("La cantidad de primos son: " + primos);
    }

}