package p2;

class Ack
{
    static int ackerman(int m, int n)
    {
        if(m == 0)
            return n+1;
        else
            if(m > 0 && n == 0)
                return ackerman(m-1, 1);
            else
                return ackerman(m-1, ackerman(m, n-1));
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