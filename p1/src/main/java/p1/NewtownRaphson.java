package p1;

import java.util.Scanner;

class NewtownRaphson
{
    /**
     * 
     * @param x Aproximimación Inicial
     * @param n Número de Iteraciones
     */
    public static void f1(double x, int n)
    {
        for(int i = 0 ; i < n ; ++i)
        {
            x = x -((Math.cos(x)- Math.pow(x,3))/(-Math.sin(x) - 3*Math.pow(x,2)));
            System.out.println("Iteracion"+(i+1)+" Resultado:"+x);
        }
    }
    /**
     * 
     * @param x Aproximación Inicial
     * @param n Número de Iteraciones
     */
    public static void f2(double x, int n)
    {
        for(int i = 0 ; i < n ; ++i)
        {
            x = x -((Math.pow(x,2) - 5)/(2*x));
            System.out.println("Iteracion"+(i+1)+" Resultado:"+x);
        }
    }
    public static void main(String []args)
    {
        Scanner s = new Scanner(System.in);
        int opc ; 
        do
        {
            System.out.println("Elige una opcion: ");
            System.out.println("1- f(x) = cos(x) − x³ en [0, 1] ");
            System.out.println("2- f(x) = x² − 5 en [2, 3]");
            System.out.println("0- Salir");
            opc = s.nextInt();
            switch(opc)
            {
                case 1:
                    System.out.println("Introduzca Aproximacion Inicial: ");
                    double x = s.nextDouble();
                    System.out.println("Introduzca Numero de Iteraciones: ");
                    int n = s.nextInt();
                    f1(x,n);
                    break;
                case 2:
                    System.out.println("Introduzca Aproximacion Inicial: ");
                    double z = s.nextDouble();
                    System.out.println("Introduzca Numero de Iteraciones: ");
                    int y = s.nextInt();
                    f2(z,y);
                    break;
                
            }
        }while(opc != 0); 
    
    
        s.close();
    }
}