package ejerexam;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface iLibro extends Remote {
    public void insertar(String cliente, int id, String title) throws RemoteException;

    public void extraer(String cliente, int id, String title) throws RemoteException;

    public void consultar(String cliente, int id, String title) throws RemoteException;

    public void mostrarLibros() throws RemoteException;

}