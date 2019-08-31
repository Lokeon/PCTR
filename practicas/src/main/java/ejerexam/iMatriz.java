package ejerexam;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface iMatriz extends Remote {
    public int[][] Transposicion(int[][] m) throws RemoteException;

    public int[][] Escalado(int[][] m, int k) throws RemoteException;

    public int[][] Suma(int[][] m, int m1[][]) throws RemoteException;

    public int[][] Prodcuto(int[][] m, int m1[][]) throws RemoteException;
}