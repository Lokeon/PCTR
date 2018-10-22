
package p2;

import java.util.*;
import java.math.*;

/**
 * @author Kevin López Cala
 */
class Estadistica
{
    /**
     * Método que nos calcula la media  
     * @param numeros  Vector-double que incluye los elementos 
     * @return retorna la media de los elementos del vector
     */
    static double media(double numeros[])
    {
        double sum = 0 ; 
        
        for(int i = 0 ; i < numeros.length ; ++i)
        {
            sum += numeros[i];    
        }
            
        return (sum/numeros.length) ;
    }

    /**
     * Método que nos calcula la moda 
     * @param numeros  Vector-double que incluye los elementos 
     * @return  retorna la moda de los elementos del vector 
     */
    static double moda(double numeros[])
    {
        int cont;
        double moda= 0.0 ,max = 0.0 ; 
        
        for(int i = 0 ; i < numeros.length ; ++i)
        {
            cont = 0;

            for(int j = 0 ; j < numeros.length ; ++j)
            {
                if(numeros[i] == numeros[j])
				{
                    cont++;
                }
                
            }
            if(max < cont)
			{
			  max = cont;
			  moda = numeros[i];
            }
		}
            return moda;
    }

    /**
     * Método que nos calcula la varianza
     * @param numeros  Vector-double que incluye los elementos 
     * @return  retorna la varianza de los elementos del vector 
     */
    static double varianza(double numeros[])
    {
        double sum = 0.0 ;

        for(int i = 0 ; i < numeros.length ; ++i)
        {
            sum += Math.pow(numeros[i],2); 
        }
    
         return (sum/(numeros.length) - Math.pow(media(numeros),2));
    }

    /**
     * Método que nos calcula la desviación típica 
     * @param numeros  Vector-double que incluye los elementos 
     * @return  retorna la desviación típica de los elementos del vector 
     */
    static double desvtipica(double numeros[])
    {
        return Math.sqrt(varianza(numeros));
    }

    /**
     * Función para mostrar el menú
     */
    static void menu()
    {
        System.out.println("1- Media");
        System.out.println("2- Moda");
        System.out.println("3- Varianza");
        System.out.println("4- Desviación Típica");
        System.out.println("0- Salir");
           
    }

    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int opc;
        double[] numeros = new double[Integer.parseInt(args[0])];
        
        System.out.println("Introduce "+Integer.parseInt(args[0])+" números");

        for(int i = 0 ; i < Integer.parseInt(args[0]) ; ++i)
        {
            System.out.println("Número "+(i+1)+": ");
            numeros[i] = s.nextDouble();
        }

        do
        {
            menu();
            opc = s.nextInt();
            switch(opc)
            {
                case 1: System.out.println("La media es " + media(numeros)+"\n"); break;
                case 2: System.out.println("La moda es " + moda(numeros)+"\n"); break;
                case 3: System.out.println("La varianza es " + varianza(numeros)+"\n"); break;
                case 4: System.out.println("La desviacion tipica es " + desvtipica(numeros)+"\n"); break;
                                
            }
        }while(opc != 0);
    
        s.close();
    }
    
}
