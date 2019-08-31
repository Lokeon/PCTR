package ejerexam;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface iIBEX5 extends Remote {
    public String verValor(int t) throws RemoteException;

    public String consultaTodo() throws RemoteException;
}