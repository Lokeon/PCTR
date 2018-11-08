
package p3;

public class Cajero implements Runnable {

    private static Cuenta_Banca cuenta ;
    int tipo;
    
    /**
     * Construtor de Cajero 
     */
    public Cajero(int tipo) {
        
        this.tipo = tipo;
    }
    /**
     * Metodo que nos indica la cantidad total de dinero
     */
    public static void totalDinero() {

         System.out.println("El total es: "+cuenta.Saldo());
    }
    
    public static void crearCuenta(int id , double disp) {

        cuenta = new Cuenta_Banca(id,disp);
    }

    @Override
    public void run() { 

        switch(tipo) {

            case 0 : cuenta.Deposito(400);  break;
            case 1 : cuenta.Reintegro(400); break;
        }
    }
}