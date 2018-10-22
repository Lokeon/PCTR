package p1;

import java.util.Scanner ;

/**
 * @author Kevin López Cala
 */
class Cesar
{
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Introduce n: ");
        int n = s.nextInt();
        System.out.println("Introduce la cadena para cifrarla: ");
        s.nextLine();
        String cad = s.nextLine();
        for(int i=0; i < cad.length(); ++i)
        {
            char c = (char)(cad.charAt(i) + n%27);
            System.out.print(c);
        }
        s.close();
    }
}