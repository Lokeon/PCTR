package ejerexam;

import java.rmi.Naming;
import java.util.*;

public class cMatriz {

    private static iMatriz serv;

    public static void menu() {
        System.out.println("1-Transposicion\n\t2-Escalado\n\t3-Suma\n\t4-Producto");
    }

    public static int[][] initMa() {
        Scanner tecla = new Scanner(System.in);
        System.out.println("Introduzca el tamaño de la matriz: ");
        int N = tecla.nextInt();

        int[][] m = new int[N][N];

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                System.out.println("Introduzca el valor para m[" + i + "][" + j + "]");
                m[i][j] = tecla.nextInt();
            }
        }
        return m;
    }

    public static void main(String[] args) {

        try {
            serv = (iMatriz) Naming.lookup("//localhost:5050/matriz");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scanner tecla = new Scanner(System.in);
        menu();

        int opc = tecla.nextInt();

        try {
            switch (opc) {
            case 1:
                int[][] m1 = initMa();
                m1 = serv.Transposicion(m1);
                break;
            case 2:
                int[][] m2 = initMa();
                System.out.println("Introduzca el escalado: ");
                int k = tecla.nextInt();
                m2 = serv.Escalado(m2, k);
                break;
            case 3:
                int[][] m11 = initMa();
                int[][] m12 = initMa();
                int[][] res = serv.Suma(m11, m12);
                break;
            case 4:
                int[][] m21 = initMa();
                int[][] m22 = initMa();
                int[][] res1 = serv.Suma(m21, m22);
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}