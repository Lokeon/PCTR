
package p4;
import java.text.*;
import java.util.*;

/**
 *
 * @author Kevin López Cala
 */
public class usaProdMatConcurrente {

    public static void main(String[] args) throws Exception {

        int sel, op;
        Scanner tecla = new Scanner(System.in);
        Random rm = new Random();

        do {

            do {

                System.out.println("Elija una opcion : \n1-MatrizManual\n2-MatrizRandom");
                op = tecla.nextInt();

            } while (op < 1 || op > 2);

            switch (op) {

            case 1:

                System.out.print("Introduzca el tamaño de la matriz1 ");
                int N = tecla.nextInt();
                System.out.print("Introduzca el tamaño de la matriz2 ");
                int J = tecla.nextInt();

                int[][] mat = new int[N][N];
                int[][] vec = new int[J][J];
                System.out.print("Introduzca los valores de la matriz1 ");

                for (int i = 0; i < mat.length; ++i) {
                    for (int j = 0; j < mat.length; ++j) {

                        mat[i][j] = tecla.nextInt();
                    }
                }

                System.out.print("Introduzca los valores de la matriz2 ");
                for (int i = 0; i < vec.length; ++i) {
                    for (int j = 0; j < vec.length; ++j) {

                        vec[i][j] = tecla.nextInt();
                    }
                }
                prodMatConcurrente[] hilo = new prodMatConcurrente[N];

                for (int i = 0; i < mat.length; ++i) {

                    hilo[i] = new prodMatConcurrente(mat, vec, N, i);

                }

                Date d = new Date();
                DateFormat dd = new SimpleDateFormat("HH:mm:ss");
                long ini = System.nanoTime();
                d.setTime(ini);

                for (int i = 0; i < mat.length; ++i) {

                    hilo[i].start();

                }

                long fin = System.nanoTime();
                d.setTime(fin);
                System.out.println("Terminado : " + dd.format(d) + " tras " + (fin - ini));

                for (int i = 0; i < mat.length; ++i) {

                    hilo[i].join();

                }
                prodMatConcurrente.mostrar();

                break;
            case 2:

                int K = 2;

                int[][] matz = new int[K][K];
                int[][] vecz = new int[K][K];

                for (int i = 0; i < matz.length; ++i) {
                    for (int j = 0; j < matz.length; ++j) {

                        matz[i][j] = rm.nextInt(10);
                    }
                }
                for (int i = 0; i < vecz.length; ++i) {
                    for (int j = 0; j < vecz.length; ++j) {

                        vecz[i][j] = rm.nextInt(10);
                    }
                }

                prodMatConcurrente[] hilos = new prodMatConcurrente[K];

                for (int i = 0; i < matz.length; ++i) {

                    hilos[i] = new prodMatConcurrente(matz, vecz, K, i);

                }

                Date dds = new Date();
                DateFormat dd1 = new SimpleDateFormat("HH:mm:ss");
                ini = System.nanoTime();
                dds.setTime(ini);

                for (int i = 0; i < matz.length; ++i) {

                    hilos[i].start();

                }

                fin = System.nanoTime();
                dds.setTime(fin);
                System.out.println("Terminado : " + dd1.format(dds) + " tras " + (fin - ini));

                for (int i = 0; i < matz.length; ++i) {

                    hilos[i].join();

                }
                prodMatConcurrente.mostrar();

                break;

            }

            System.out.println("Pulse 0 para volver atras");
            sel = tecla.nextInt();

        } while (sel == 0);

    }

}
