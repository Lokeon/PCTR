package ejerexam;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.*;
import java.util.*;

public class sLibro extends UnicastRemoteObject implements iLibro {

    private static ArrayList<Libro> libros = new ArrayList<Libro>();

    public sLibro() throws RemoteException {
        super();
    }

    public void mostrarLibros() throws RemoteException {

        for (int i = 0; i < libros.size(); ++i) {

            System.out.println("Nombre: " + libros.get(i).getitle() + "Id: " + libros.get(i).getid());
        }
    }

    public void insertar(String cliente, int id, String title) throws RemoteException {
        libros.add(new Libro(id, title));
        System.out.println("Libro Insertado");
        mostrarLibros();
    }

    public void consultar(String cliente, int id, String title) throws RemoteException {

        if (libros.contains(new Libro(id, title))) {
            System.out.println("Libro encontrado");
            System.out.println("Nombre: " + libros.get(libros.indexOf(new Libro(id, title))).getitle() + "Id: "
                    + libros.get(libros.indexOf(new Libro(id, title))).getid());
        } else {
            System.out.println("Ese libro no existe");
            throw new RemoteException();
        }
    }

    public void extraer(String cliente, int id, String title) throws RemoteException {
        if (libros.contains(new Libro(id, title))) {
            libros.remove(new Libro(id, title));
            System.out.println("Libro encontrado. Eliminando...");
            mostrarLibros();
        } else {
            System.out.println("Libro no encontrado");
        }
    }

    public static void main(String[] args) throws Exception {

        sLibro server = new sLibro();

        Naming.bind("Servidor", server);
        System.out.println("Lets gooo");
    }
}