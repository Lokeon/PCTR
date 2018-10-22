package p2;

/**
 * @author Kevin López Cala
 */
class Ack
{
    /**
     * Método que calcula la función de Ackermann recursivamente
     * @param m  //Primer parametro de la función de Ackermann
     * @param n // Segundo parametro de la función de Ackermann
     * @return  // devuelve el resultado de la función de Ackermann
     */
    static int ackerman(int m, int n)
    {
        if(m == 0)
        {
            return n + 1;
        }   
        else
        {
            if(m > 0 && n == 0)
            {
                return ackerman(m-1, 1);
            }   
            else
            {
                return ackerman(m-1, ackerman(m, n-1));
            }   
        }
    }
    public static void main(String[] args)       
    {
        if(args.length == 2)
        {
            System.out.println(ackerman(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
        }
        else
        {
            System.err.println("Debes pasarle dos argumentos por linea de comandos.");
        }
    }
}