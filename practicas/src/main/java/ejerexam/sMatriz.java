package ejerexam;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class sMatriz extends UnicastRemoteObject implements iMatriz {
    public sMatriz() throws RemoteException {
        super();
    }

    public int[][] Transposicion(int[][] m) throws RemoteException {

        int res[][] = new int[m.length][m.length];

        for (int i = 0; i < m.length; ++i) {
            for (int j = 0; j < m.length; ++j) {
                res[i][j] = m[j][i];
            }
        }

        return res;
    }

    public int[][] Escalado(int[][] m, int k) throws RemoteException {
        int res[][] = new int[m.length][m.length];

        for (int i = 0; i < m.length; ++i) {
            for (int j = 0; j < m.length; ++j) {
                res[i][j] = m[i][j] * k;
            }
        }

        return res;
    }

    public int[][] Suma(int[][] m, int m1[][]) throws RemoteException {
        int res[][] = new int[m.length][m.length];

        for (int i = 0; i < m.length; ++i) {
            for (int j = 0; j < m.length; ++j) {
                res[i][j] = m[i][j] + m1[i][j];
            }
        }

        return res;
    }

    public int[][] Prodcuto(int[][] m, int m1[][]) throws RemoteException {
        int res[][] = new int[m.length][m.length];

        for (int i = 0; i < m.length; ++i) {
            for (int j = 0; j < m.length; ++j) {
                for (int k = 0; k < m.length; ++k) {
                    res[i][j] += m[i][k] * m1[k][j];
                }
            }
        }

        return res;
    }
}