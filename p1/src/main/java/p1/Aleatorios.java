package p1;

class Aleatorios
{
    public static void main(String []args)
    {
        for(int i=0; i < Integer.parseInt(args[0]); ++i)
        {
            System.out.println(Math.random());
        }
    }
}