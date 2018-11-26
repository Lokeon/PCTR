
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
public class resImagenParGru implements Runnable {

  private int hilo, end;
  public static int[][] mat;

  public resImagenParGru(int hilo, int end) {
    this.hilo = hilo;
    this.end = end;
  }

  public static void rellenarmat() throws IOException {
    mat = CargaImagen.cargar("src/main/java/p6/uca_gris.png");
  }

  public void run() {
    int n = mat.length;
    for (int i = hilo; i < end; ++i) {
      for (int j = 0; j < mat[0].length; ++j) {
        if ((i != 0 && j != 0) && (i != mat.length - 1 && j != mat.length - 1))
          mat[i][j] = (4 * mat[i][j] - mat[i][(j + 1) % n] - mat[i][(j - 1) % n] - mat[(i - 1) % n][j]) / 8;
      }
    }
  }

  public static void main(String[] args) throws IOException {

    rellenarmat();
    int nTareas = Runtime.getRuntime().availableProcessors();
    int tVentana = (int) Math.ceil((mat.length * 1.)/nTareas);
    int start = 0;
    int end = 0;
    boolean tareas = true;
    Date d = new Date();
    DateFormat dd = new SimpleDateFormat("HH:mm:ss");
    long ini = System.nanoTime();
    d.setTime(ini);

    ExecutorService fila = Executors.newFixedThreadPool(nTareas);

    for (int i = 0; i < nTareas; ++i) {
      start = i * tVentana;
      end = (i + 1) * tVentana;
      if (tareas) {
        tareas = (end < mat.length);
        end = (tareas) ? end : mat.length;
        fila.submit(new resImagenParGru((int) start, (int) end));
      }
    }

    long fin = System.nanoTime();
    fila.shutdown();
    d.setTime(fin);
    System.out.println("Terminado : " + dd.format(d) + " tras " + (fin - ini) / 1.0e9 + " seg ");

    CargaImagen.guardar(mat, "src/main/java/p6/uca_resaltada.png");
  }
}