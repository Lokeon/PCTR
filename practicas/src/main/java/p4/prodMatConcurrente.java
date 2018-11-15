
package p4;

/**
 *
 * @author Kevin López Cala
 */
public class prodMatConcurrente extends Thread {

    private int[][] mat1, mat2;
    private int tama, fil;
    private static int[][] res;

    public prodMatConcurrente(int[][] mat, int[][] vec, int tam, int fila) {

        mat1 = mat;
        mat2 = vec;
        this.tama = tam;
        this.fil = fila;

        res = new int[tama][tama];
    }

    public void run() {

        for (int i = 0; i < tama; ++i)
            for (int k = 0; k < tama; ++k)

                res[fil][k] += mat1[fil][i] * mat2[i][k];

    }

    public static void mostrar() {

        for (int i = 0; i < res.length; ++i)
            for (int j = 0; j < res.length; ++j)
                System.out.print(res[i][j] + " ");
        System.out.println("\n");

    }
}