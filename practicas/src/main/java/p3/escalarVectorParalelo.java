
package p3;

import java.util.*;

/**
 * @author Kevin López Cala
 */

public class escalarVectorParalelo extends Thread {

    private static int k = 10 ;
    static int vector[] = new int[(int)Math.pow(10,8)] ;
    private int ini , fin ;

    /**
     * Constructor de escalarVectorParalelo que nos inicializa las iter correspondientes
     * @param ini Int- Iteraciones iniciales del hilo
     * @param fin Int- Iteraciones finales del hilo
     */
    
    public escalarVectorParalelo(int ini , int fin) {

        this.ini = ini ; this.fin = fin ;
    }

    /**
     * Método que nos rellena el vector
     */
    public static void rellenar() {

        Random pp = new Random(System.nanoTime());    
   
        for(int i = 0 ; i < vector.length ; ++i) {

            vector[i] = pp.nextInt() % 50;
        }
    }
    
    @Override
    public void run() {

        for(int i = ini ; i < fin; ++i) {

            vector[i] *= k ; 
        }

    }
    public static void main(String[] args) throws Exception {
        
        long tin , tfin ;   
        int tam = Runtime.getRuntime().availableProcessors() ;
        
        escalarVectorParalelo[] h1 = new escalarVectorParalelo[tam];
        
        for(int i = 0 ; i < tam ; ++i) {

            h1[i] = new escalarVectorParalelo(i * (vector.length /tam), (i+1) * (vector.length /tam));
        }
        
        rellenar();

        tin =  System.nanoTime() ;  
         
        for(int i = 0 ; i < tam ; ++i) { h1[i].start(); }
        for(int i = 0 ; i < tam ; ++i) { h1[i].join(); }
        
        tfin =  System.nanoTime() - tin ; 
      
        System.out.println("Ha tardado: "+tfin+" ns"); 

    }


}