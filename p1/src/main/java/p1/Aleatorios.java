package p1;

/**
 * @author Kevin López Cala
 */
class Aleatorios
{
    public static void main(String[] args)
    {
        for(int i=0; i < Integer.parseInt(args[0]); ++i)
        {
            System.out.println(Math.random()*10);
        }
    }
}