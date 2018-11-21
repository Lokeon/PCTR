
package p6;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;

/**
 *
 * @author Kevin Lopez Cala
 */
public class resImagen {

    static int[][] imagen;
    static int[][] res;
    private static int N = 10000;

    /**
     * 
     * @param imagen Corresponde a la matiz imagen
     */

    public static void iniciar_gris(int imagen[][]) {

        Random rm = new Random();

        for (int i = 0; i < N; ++i)
            for (int j = 0; j < N; ++j)

                imagen[i][j] = rm.nextInt(20);
    }

    /**
     * Método que resalta los 20 pixeles aleatorios
     * 
     * @param imagen Corresponde a la matriz
     * @param res    Corresponde a la matriz resultante del resaltado
     */
    public static void resaltar_(int[][] imagen, int[][] res) {

        int imenos, jmenos;

        for (int i = 0; i < N; ++i) {
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

    public static void main(String[] args) throws IOException {

        CargaImagen.ConvertirAGris("/src/main/java/p6/uca.png", "/src/main/java/p6/uca_gris.png");
        imagen = CargaImagen.cargar("uca_gris.png");
        res = new int[imagen.length][imagen.length];

        iniciar_gris(imagen);

        // resImagen.mostrar_(imagen) ;

        Date d = new Date();
        DateFormat dd = new SimpleDateFormat("HH:mm:ss");
        long ini = System.nanoTime();
        d.setTime(ini);

        resImagen.resaltar_(imagen, res);

        long fin = System.nanoTime();
        d.setTime(fin);
        System.out.println("Terminado : " + dd.format(d) + " tras " + (fin - ini) / 1.0e9 + " seg ");

        // resImagen.mostrar(res) ;

    }

}
