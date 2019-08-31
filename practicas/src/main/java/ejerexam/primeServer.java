package ejerexam;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class primeServer extends UnicastRemoteObject implements primeInterface {
    public primeServer() throws RemoteException {
        super();
    }

    public int primesInRange(int iniRange, int endRange) throws RemoteException {

        int cont = 0;

        for (int i = iniRange; i < endRange; ++i) {
            if (i >= 2) // quitar 0 y 1
            {
                int divs = 0;
                for (int j = 2; j <= Math.round((float) i / 2f); ++j) {

                    if (i % j == 0) {
                        divs++;
                    }
                }
                if (divs == 0) {
                    cont++;
                }
            }
        }

        return cont;
    }

    public static void main(String[] args) {
        try {
            primeServer server = new primeServer();
            Naming.bind("//localhost:1099/servidor", server);
            System.out.println("Server Listo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}