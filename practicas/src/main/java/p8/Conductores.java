
package p8;

import java.util.ArrayList;

/**
 *
 * @author Kevin López Cala
 */
public class Conductores {

    private static ArrayList<Conductor> Con = new ArrayList<Conductor>(100);
    private static ArrayList<Boolean> ocupado = new ArrayList<Boolean>(100);

    public Conductores() {

        iniciarOcupados();

    }

    private void iniciarOcupados() {

        for (int i = 0; i < 100; ++i) {

            ocupado.add(Boolean.FALSE);

        }
    }

    public synchronized void intorducirCond(Conductor P) {

        int i = 0;

        while (ocupado.get(i)) {

            Con.add(P);
            ocupado.add(Boolean.TRUE);
            ++i;
        }
    }

    public synchronized void buscarCond(String dni, int opc) {

        int i = 0;

        while (i < 100) {

            if (ocupado.get(i)) {

                if ((Con.get(i).getNombre() == dni && opc == 1) || (Con.get(i).getDni() == dni && opc == 2)) {
                    System.out.println(Con.get(i));
                }
            }
            ++i;
        }
    }

    public synchronized void eliminarCond(String dni) {

        int i = 0;

        while (i < 100) {
            if (ocupado.get(i)) {

                if (Con.get(i).getDni() == dni) {
                    Con.remove(i);
                }

            }
            ++i;
        }

    }

}
