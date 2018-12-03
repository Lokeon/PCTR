
package p7;

import java.net.*;
import java.io.*;
import static java.lang.Thread.sleep;
import java.util.concurrent.*;

public class ServidorHiloconPool implements Runnable {
    Socket enchufe;
    int persona;

    public ServidorHiloconPool(Socket s, int pers) {

        enchufe = s;
        this.persona = pers;

    }

    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(enchufe.getInputStream()));
            String datos = entrada.readLine();
            int j;
            int i = Integer.valueOf(datos).intValue();
            for (j = 1; j <= 20; ++j) {
                System.out.println("El hilo " + persona + " escribiendo el dato " + i);
                sleep(1000);
            }
            enchufe.close();
            System.out.println("El hilo " + persona + " cierra su conexion...");
        } catch (Exception e) {
            System.out.println("Error...");
        }
    }

    public static void main(String[] args) throws Exception {

        int puerto = 2001, r = 0;

        ServerSocket X = new ServerSocket(puerto, 3000);
        ExecutorService servidor = Executors.newCachedThreadPool();

        while (true) {

            System.out.println("Esperando solicitud de conexion...");
            Socket cable = X.accept();
            System.out.println("Recibida solicitud de conexion...");
            servidor.execute(new ServidorHiloconPool(cable, r));

            r++;
        }

    }

}
