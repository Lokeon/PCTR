
package p6;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class resImagenParGru implements Runnable {

  static int N = 1000;
  static int[][] imagen;
  static int[][] res;
  private int fil;

  /**
   * Constructor de la clase
   * 
   * @param N COrresponde al número de filas/hilos ( 4 )
   */

  public resImagenParGru(int N) {

    this.fil = N;

  }

  /**
   * Metodo para seleccionar los 20 pixeles de la imagen a resltar
   * 
   * @param imagen Matriz imagen
   */

  public static void iniciar_gris(int[][] imagen) {

    Random rm = new Random();

    for (int i = 0; i < N; ++i)
      for (int j = 0; j < N; ++j)

        imagen[i][j] = rm.nextInt(20);

  }

  /**
   * Metodo run que realizara el resaltado de la imagen
   */

  public void run() {

    int imenos, jmenos;

    int i = fil;

    for (int j = 0; j < N; ++j) {

      imenos = i - 1;
      jmenos = j - 1;

      if (0 > imenos) {
        imenos = N - 1;
      }
      if (0 > jmenos) {
        jmenos = N - 1;
      }

      res[i][j] = (4 * imagen[i][j] - imagen[(i + 1) % N][j] - imagen[i][(j + 1) % N] - imagen[imenos][j]
          - imagen[i][jmenos]) / 8;

    }

  }

  /**
   * Método para mostrar imagen
   * 
   * @param imagen Matriz imagen
   */

  public static void mostrar_(int[][] imagen) {

    for (int i = 0; i < N; ++i) {
      for (int j = 0; j < N; ++j) {
        System.out.print(" " + imagen[i][j] + " ");
      }
      System.out.println("\t");
    }
  }

  /**
   * Método para mostrar res
   * 
   * @param res Matriz resultante
   */
  public static void mostrar(int[][] res) {

    for (int i = 0; i < N; ++i) {
      for (int j = 0; j < N; ++j) {
        System.out.print(" " + res[i][j] + " ");
      }
      System.out.println("\t");
    }
  }

  public static void main(String[] args) throws Exception {

    imagen = new int[N][N];
    res = new int[N][N];

    int Nt = Runtime.getRuntime().availableProcessors();

    resImagenParFin.iniciar_gris(imagen);

    // resImagenParFin.mostrar_(imagen) ;

    Date d = new Date();
    DateFormat dd = new SimpleDateFormat("HH:mm:ss");
    long ini = System.nanoTime();
    d.setTime(ini);

    ExecutorService fila = Executors.newFixedThreadPool(Nt);

    for (int i = 0; i < N; ++i) {

      fila.execute(new resImagenParGru(i));

    }
    long fin = System.nanoTime();
    d.setTime(fin);
    System.out.println("Terminado : " + dd.format(d) + " tras " + (fin - ini) / 1.0e9 + " seg ");
    fila.shutdown();
    while (!fila.isTerminated()) {
    }
    // resImagenParFin.mostrar(res) ;

  }

}
