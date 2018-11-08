
package p3 ;

import java.util.* ;

/**
 * @author Kevin López Cala
 */

 public class escalarVector {
    
    /**
     *  Método que nos rellena el vector
     * @param vector Int- Vector que se usará para rellenar números aleatorios
     */
     static void rellenar(int vector[]) {
   
        Random pp = new Random(System.nanoTime()) ;
   
        for(int i = 0 ; i <vector.length ; ++i){
   
            vector[i] = pp.nextInt() % 50 ;
   
        } 
   
    }    
    /**
     * Método para escalar el Vector
     * @param vector Int- vector
     * @param k Int- número que se multiplicará el vector para el escalado
     */
    static void escalar(int vector[], int k) {
    
       for(int i = 0 ; i < vector.length ; ++i){
       
            vector[i] *= k ;
       }
    }
    public static void main(String[] args) {
      
        long tin , tfin ; 

        int vector[] = new int[(int)Math.pow(10,8)];

        rellenar(vector);

        tin =  System.nanoTime() ;  
         
        escalar(vector, 10); 

        tfin =  System.nanoTime() - tin ; 
      
          System.out.println("Ha tardado: "+tfin+" ns"); 
      }
  }
   
   
   
   
   

