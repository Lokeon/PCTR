
package p3;


public class Hilo extends Thread{

    private static int n = 0  ; 
    private int iter = 1000 , tipo ;

    /**
     * Constructor de Hilos 
     * @param tipo Int- este parametro designa el tipo de hilo 
     */
    
    public Hilo(int tipo)
    {
        this.tipo = tipo ; 
    }

    @Override
    public void run(){

        switch(tipo)
        {
            case 0: for(int i = 0 ; i < iter ; ++i, ++n) { System.out.println(n); } ; break ;
            case 1: for(int i = 0 ; i < iter ; ++i, --n) { System.out.println(n); } ; break ; 
  
        }

    }


}