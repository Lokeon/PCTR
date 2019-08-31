package ejerexam;

public class eslecMon {

    private static int N;
    private static boolean escribiendo;

    public eslecMon() {
        this.N = 0;
        this.escribiendo = false;
    }

    public synchronized void inicia_lectura() {

        while (escribiendo) {
            try {
                wait();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ++N;

        System.out.println("Lector inicia lectura...");
        notifyAll();

    }

    public synchronized void fin_lectura() {

        --N;

        if (N == 0) {

            notifyAll();

        }
        System.out.println("Lector finaliza lectura...");
    }

    public synchronized void inicia_escritura() {

        while ((N != 0) || escribiendo) {
            try {
                wait();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        escribiendo = true;

        System.out.println("Escritor inicia escritura...");
    }

    public synchronized void fin_escritura() {

        escribiendo = false;
        notifyAll();

        System.out.println("Escritor finaliza escritura...");

    }
}