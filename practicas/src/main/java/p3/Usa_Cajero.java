
package p3;

/**
 * @author Kevin López Cala
 */

 public class Usa_Cajero {

    public static void main(String[] args) throws Exception {
        
        int tam = Runtime.getRuntime().availableProcessors();

        Cajero.crearCuenta(666, 400);

        Thread[] hilos = new Thread[tam];

        for(int i = 0 ; i < tam ; ++i) {

            if(i % 2 != 0) { 
            
                hilos[i] = new Thread(new Cajero(0));
            
            }else {

                hilos[i] = new Thread(new Cajero(1));
            }
        }
        
        for(int i = 0 ; i < tam ; ++i) { hilos[i].start(); }
        for(int i = 0 ; i < tam ; ++i) { hilos[i].join(); }

        Cajero.totalDinero();
    }
 }