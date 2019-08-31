
package p8;

import java.util.*;
import java.util.concurrent.*;

/**
 *
 * @author Kevin López Cala
 */
public class usaConductores implements Runnable {

    private int hilo;
    private static Conductores Cn = new Conductores();

    public usaConductores(int hil) {

        this.hilo = hil;

    }

    public void run() {

        Scanner tecla = new Scanner(System.in);

        int op = 0;
        String nombre, dni, direcc, seguro;
        int telf;

        switch (hilo) {

        case 0:

            System.out.print("Introduzca el nombre: ");
            nombre = tecla.nextLine();
            System.out.print("Introduzca el DNI: ");
            dni = tecla.nextLine();
            System.out.print("Introduzca la direccion: ");
            direcc = tecla.nextLine();
            System.out.print("Introduzca el telefono: ");
            telf = tecla.nextInt();
            System.out.print("Introduzca el seguro: ");
            seguro = tecla.nextLine();

            Conductor Cd = new Conductor(nombre, dni, direcc, seguro, telf);
            Cn.intorducirCond(Cd);

            break;

        case 1:

            System.out.println("Buscar por : \n1-Nombre\n2-DNI");
            op = tecla.nextInt();

            switch (op) {

            case 1:

                System.out.println("Introduzca el Nombre");

                nombre = tecla.nextLine();
                Cn.buscarCond(nombre, op);
                break;

            case 2:

                System.out.println("Intoduzca el DNI");

                dni = tecla.nextLine();
                Cn.buscarCond(dni, op);
                break;

            }
            break;

        case 2:

            System.out.println("Introduzca el DNI del Conductor a elimiar");
            dni = tecla.nextLine();

            Cn.eliminarCond(dni);

            break;
        }

    }

    public static void main(String[] args) throws Exception {

        ExecutorService hilo = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 4; ++i) {
            hilo.execute(new usaConductores(i));
        }
        hilo.shutdown();

    }

}