package ejerexam;

import java.rmi.*;

public interface iDiane extends Remote {
    public int tirarDardo() throws RemoteException;

    public int puntuacion() throws RemoteException;
}