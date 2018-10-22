package p1;

import java.util.* ; 

/**
 * @author Kevin López Cala
 */
public class intDefinidaMonteCarlo
{
    //[a,b] = [0,1]
    /**
     * Método que nos calcula la aproximación en f(x) = sin(x) en [0, 1]
     * @param n Número de Puntos
     * @param a 0 -Rango  
     * @param b 1 -Rango
     * @return  Devuelve la aproximación
     */
    public static double f1(int n, double a , double b)
    {
        Random r = new Random() ;

        double sum = 0.0 ;
        
        for(double i = 0.0 ; i < n ; ++i)
        {
            sum = sum + Math.sin(a + (b - a) * r.nextDouble());
        }

        return ((b - a ) / n) * sum;    
    }
   
    /**
     * Método que nos calcula la aproximación en f(x) = x en [0, 1]  
     * @param n Número de Puntos
     * @param a 0 -Rango  
     * @param b 1 -Rango
     * @return  Devuelve la aproximación
     */
   
    public static double f2(int n, double a , double b)
    {
        Random r = new Random() ;

        double sum = 0.0 ;
        
        for(double i = 0.0 ; i < n ; ++i)
        {
            sum = sum + (a + (b - a) * r.nextDouble());
        }

        return ((b - a ) / n) * sum ;
    }

    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int opc;
        do
        {
            System.out.println("Elige una opcion: ");
            System.out.println("1- f(x) = sin(x) en [0, 1] ");
            System.out.println("2- f(x) = x en [0, 1]");
            System.out.println("0- Salir");
            opc = s.nextInt();
            switch(opc)
            {
                case 1:
                System.out.println("Introduzca nºPuntos: ");
                int n = s.nextInt();
                System.out.println("La aproximacion es: "+f1(n,0,1));
                    break;
                case 2:
                System.out.println("Introduzca nºPuntos: ");
                int z = s.nextInt();
                System.out.println("La aproximacion es: "+f2(z,0,1));
                    break;
                
            }
        }while(opc != 0);    
   
        s.close();
    }

} 