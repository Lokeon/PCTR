package ejerexam;

import java.rmi.*;
import java.util.*;

public class primeClient {

    private int iniRange, endRange, subrangos;
    private static primeInterface serv;

    public void leerEntrada() {
        Scanner tecla = new Scanner(System.in);
        System.out.println("Introduzca el rango inicial: ");
        iniRange = tecla.nextInt();
        System.out.println("Introduzca el rango final: ");
        endRange = tecla.nextInt();
        System.out.println("Introduzca el nº de subrangos");
        subrangos = tecla.nextInt();
    }

    public int primosTotal() throws RemoteException {
        int cont = 0, ini, fin;

        for (int i = 0; i < subrangos; ++i) {
            ini = (i * (endRange - iniRange) / subrangos) + iniRange;

            if (i == subrangos - 1) {
                fin = endRange + 1;
            } else {
                fin = ((i + 1) * (endRange - iniRange) / subrangos) + iniRange;
            }

            cont += serv.primesInRange(ini, fin);
        }

        return cont;
    }

    public static void main(String[] args) {

        int primos = 0 ;

        try {
            serv = (primeInterface) Naming.lookup("//localhost:1099/servidor");

        } catch (Exception e) {
            e.printStackTrace();
        }

        primeClient client = new primeClient();

        client.leerEntrada();

        try {
            primos = client.primosTotal();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Hay " + primos + " primos");
    }

}