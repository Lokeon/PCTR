package ejerexam;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface primesI extends Remote
{
    public int primesInRange(int ini, int fin) throws RemoteException;
}