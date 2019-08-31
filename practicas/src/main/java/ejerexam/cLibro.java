package ejerexam;

import java.util.*;
import java.rmi.*;

public class cLibro {

    private static Scanner tecla = new Scanner(System.in);

    public static void menu() {
        System.out
                .println("\t1-Insertar\n" + "\t2-Extraer\n" + "\t3-Consultar\n" + "\t4-MostarLibros\n" + "\t5-Salir\n");
    }

    public static void main(String[] args) throws Exception {

        iLibro Ref = (iLibro)Naming.lookup("//localhost/Servidor");
        String cliente;
        int op;

        do {

            System.out.println("Introduzca su nombre: ");
            cliente = tecla.nextLine();
            menu();
            op = tecla.nextInt();
            tecla.nextLine();

            int id;
            String title;

            switch (op) {

            case 1:
                System.out.println("Id: ");
                id = tecla.nextInt();
                tecla.nextLine();
                System.out.println("Titulo: ");
                title = tecla.nextLine();
                Ref.insertar(cliente, id, title);
                break;
            case 2:
                System.out.println("Id: ");
                id = tecla.nextInt();
                tecla.nextLine();
                System.out.println("Titulo: ");
                title = tecla.nextLine();
                Ref.extraer(cliente, id, title);
                break;
            case 3:
                System.out.println("Id: ");
                id = tecla.nextInt();
                tecla.nextLine();
                System.out.println("Titulo: ");
                title = tecla.nextLine();
                Ref.consultar(cliente, id, title);
                break;
            case 4:
                Ref.mostrarLibros();
                break;
            case 5:
                System.out.println("Pa tu casa... ");
                break;

            }

        } while (op != 5);

    }

}