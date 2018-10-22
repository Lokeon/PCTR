
package p2 ;

import java.util.*;

/**
 * @author Kevin López Cala
 */

 class usaComplejos
 {

    /**
     * Función para introducir la parte real y imaginaria del Complejo
     * @return retorna un Complejo
     */
    static Complejos introducirComplejo()
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Parte real: ");
        double x = s.nextDouble();
        System.out.println("Parte Imaginaria: ");
        double i = s.nextDouble();

        return new Complejos(x,i);
    }

    /**
     * Función para mostrar el menú
     */
    static void menu()
    {
        System.out.println("1- Sumar Complejos");
        System.out.println("2- Restar Complejos");
        System.out.println("3- Producto Complejos");
        System.out.println("4- Cociente Complejos");
        System.out.println("5- Módulo Complejo");
        System.out.println("0- Salir");
    }

    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int opc ;
        Complejos A, B; 
        do
        {
            menu();
            opc = s.nextInt();
            
            switch(opc)
            {
                case 1: 
                    System.out.println("Introduce dos Complejos");
                    A = introducirComplejo();
                    B = introducirComplejo();
                    System.out.println("La suma es: "+A.suma(B));
                    break;
                case 2: 
                    System.out.println("Introduce dos Complejos");
                    A = introducirComplejo();
                    B = introducirComplejo();
                    System.out.println("La resta es: "+A.resta(B));
                    break;
                case 3: 
                    System.out.println("Introduce dos Complejos");
                    A = introducirComplejo();
                    B = introducirComplejo();
                    System.out.println("El producto es: "+A.producto(B));
                    break;
                case 4: 
                    System.out.println("Introduce dos Complejos");
                    A = introducirComplejo();
                    B = introducirComplejo();
                    System.out.println("El cociente es: "+A.cociente(B));
                    break;
                case 5:
                    System.out.println("Introduce un Complejo");
                    A = introducirComplejo();
                    System.out.println("El módulo es: "+A.modulo());
                    break;
            }
        }while(opc != 0);

        s.close();
    }
}