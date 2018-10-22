
package p2;

import java.util.*;

class usaElipse
{
    /**
     * Función que inserta los semiejes de la elipse 
     * @return una Elipse
     */
    static Elipse insertarElipse()
    {
        Scanner s = new Scanner(System.in);
        
        System.out.println("Introduzca el semieje mayor de la elipse");
        double rx = s.nextDouble();
        System.out.println("Introduzca el semieje menor de la elipse");
        double ry = s.nextDouble();

        return new Elipse(rx, ry) ;
    }

    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        Elipse A = insertarElipse() ;  
    
        System.out.println("Introduzca la coordenada X del punto");
        double x = s.nextDouble();
        System.out.println("Introduzca la coordenada Y del punto");
        double y = s.nextDouble();
        System.out.println("Introduzca la coordenada X del origen de la Elipse");
        double ox = s.nextDouble();
        System.out.println("Introduzca la coordenada Y del origen de la Elipse");
        double oy = s.nextDouble();

        if(A.pertenecePunto(x, y, ox, oy))
        {
            System.out.println("El punto ("+ x +","+ y +") pertence a la Elipse");
        }
        else
        {
            System.out.println("El punto ("+ x +","+ y +") no pertence a la Elipse");
        }
    }



}