package ejerexam;

import java.rmi.RemoteException;
import java.rmi.*;

public interface iHelion extends Remote {
    public void iniHelion() throws RemoteException;

    public boolean arminsticio() throws RemoteException;

    public void ojiva() throws RemoteException;

    public void status() throws RemoteException;
}