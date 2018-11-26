
package p6;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;

/**
 * @author Kevin López Cala
 */
public class resImagen {

    public static void resaltar(int[][] mat) {
        int n = mat.length;
        for (int i = 0; i < mat.length; ++i) {
            for (int j = 0; j < mat[0].length; ++j) {
                if ((i != 0 && j != 0 ) && (i != mat.length -1 && j != mat.length -1))
                    mat[i][j] = (4 * mat[i][j] - mat[i][(j + 1) % n] - mat[i][(j - 1) % n] - mat[(i - 1) % n][j]) / 8;
            }
        }
    }
    public static void main(String[] args) throws IOException {

        int[][] mat = CargaImagen.cargar("src/main/java/p6/uca_gris.png");

        Date d = new Date();
        DateFormat dd = new SimpleDateFormat("HH:mm:ss");
        long ini = System.nanoTime();
        d.setTime(ini);

        resaltar(mat);

        long fin = System.nanoTime();
        d.setTime(fin);
        System.out.println("Terminado : " + dd.format(d) + " tras " + (fin - ini) / 1.0e9 + " seg ");

        CargaImagen.guardar(mat, "src/main/java/p6/uca_resaltada.png");
    }
}