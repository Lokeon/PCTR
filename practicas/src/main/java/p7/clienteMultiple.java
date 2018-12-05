package p7;

import java.net.*;
import java.io.*;
import java.util.*;

public class clienteMultiple {
    public static void main(String[] args) {
        Scanner tecla = new Scanner(System.in);

        System.out.println("Escriba el numero de conexiones a realizar");
        int x = tecla.nextInt();

        int puerto = 2001;

        for (int j = 0; j < x; ++j) {
            try {
                int i = (int) (Math.random() * 100);
                System.out.println("Realizando conexion...");
                Socket cable = new Socket("localhost", 2001);
                System.out.println("Realizada conexion a " + cable);
                PrintWriter salida = new PrintWriter(
                        new BufferedWriter(new OutputStreamWriter(cable.getOutputStream())));
                salida.println(i);
                salida.flush();
                System.out.println("Cerrando conexion...");
                cable.close();

            } // try
            catch (Exception e) {
                System.out.println("Error en sockets...");
            }
        } // main
    }// Cliente_Hilos
}
