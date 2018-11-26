
package p6;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.*;
import java.util.*;

/**
 * @author Kevin Lopez Cala
 */

public class MatrizGranoGrueso implements Runnable {

    private static int[][] matz;
    private static int[] vecz;
    private static int[] sol;
    private int fila, end;
    private static int N;

    /**
     * 
     * @param hilo corresponde al número de hilos
     */
    public MatrizGranoGrueso(int hilo, int end) {

        this.fila = hilo;
        this.end = end;

    }

    /**
     * Metodo run donde se realizara el producto de la matiz y el vector
     */
    public void run() {

        for (int i = fila; i < end; ++i)
            for (int j = 0; j < N; ++j)
                sol[i] += matz[i][j] * vecz[j];
    }
    
    private static void Filler() {
        N = 5;
        Random r = new Random();
        sol = new int[N];
        Arrays.fill(sol, 0);
        vecz = new int[N];
        matz = new int[N][N];
        for (int i = 0; i < N; ++i)
            vecz[i] = (int) (r.nextInt() * 10 + 1);

        for (int i = 0; i < N; ++i)
            for (int j = 0; j < N; ++j)
                matz[i][j] = (int) (r.nextInt() * 10 + 1);
    }

    public static void mostrar() {
        for (int i = 0; i < N; ++i)
            System.out.print(sol[i] + " ");
        System.out.println("\n");

    }

    public static void main(String[] args) throws Exception {

        Filler();
        int nTareas = Runtime.getRuntime().availableProcessors();
        int tVentana = (int) Math.ceil((matz.length * 1.)/nTareas);
        int start = 0;
        int end = 0;
        boolean tareas = true;
        Date d = new Date();
        DateFormat dd = new SimpleDateFormat("HH:mm:ss");
        long ini = System.nanoTime();
        d.setTime(ini);
        ExecutorService exe = Executors.newFixedThreadPool(nTareas);

        for (int i = 0; i < nTareas; ++i) {
            start = i * tVentana;
            end = (i + 1) * tVentana;
            if (tareas) {
                tareas = (end < matz.length);
                end = (tareas) ? end : matz.length;
                exe.submit(new MatrizGranoGrueso((int) start, (int) end));
            }
        }

        exe.shutdown();

        long fin = System.nanoTime();
        d.setTime(fin);
        System.out.println("Terminado : " + dd.format(d) + " tras " + (fin - ini) / 1.0E9 + " seg ");

        MatrizGranoGrueso.mostrar();
    }
}
