
package p6;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Kevin López Cala
 */
public class resImagenParFin implements Runnable {

    private int hilo;
    public static int[][] mat;

    public resImagenParFin(int hilo) {
        this.hilo = hilo;
    }

    public static void rellenarmat() throws IOException {
        mat = CargaImagen.cargar("src/main/java/p6/uca_gris.png");
    }

    public void run() {
        int n = mat.length;
        for (int j = 0; j < mat[0].length; ++j) {
            if ((hilo != 0 && j != 0) && (hilo != mat.length - 1 && j != mat.length - 1))
                mat[hilo][j] = (4 * mat[hilo][j] - mat[hilo][(j + 1) % n] - mat[hilo][(j - 1) % n]
                        - mat[(hilo - 1) % n][j]) / 8;
        }
    }
    public static void main(String[] args) throws IOException {

        rellenarmat();
        Date d = new Date();
        DateFormat dd = new SimpleDateFormat("HH:mm:ss");
        long ini = System.nanoTime();
        d.setTime(ini);

        ExecutorService fila = Executors.newFixedThreadPool(mat.length);

        for (int i = 0; i < mat.length; ++i) {

            fila.execute(new resImagenParFin(i));

        }

        long fin = System.nanoTime();
        fila.shutdown();
        d.setTime(fin);
        System.out.println("Terminado : " + dd.format(d) + " tras " + (fin - ini) / 1.0e9 + " seg ");

        CargaImagen.guardar(mat, "src/main/java/p6/uca_resaltada.png");
    }
}