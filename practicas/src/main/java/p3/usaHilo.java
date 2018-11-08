package p3;

/**
 * @author Kevin López Cala
 */

public class usaHilo{

    public static void main(String[] args) throws Exception{
        
        Hilo uno  = new Hilo(0);
        Hilo dos = new Hilo(1);

        uno.start();
        dos.start();

        uno.join();
        dos.join();

    }





}