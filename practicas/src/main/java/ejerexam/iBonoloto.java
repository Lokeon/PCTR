package ejerexam;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface iBonoloto extends Remote {
    public boolean apuesta(int[] array) throws RemoteException;
}