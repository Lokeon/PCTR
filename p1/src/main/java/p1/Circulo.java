package p1;

class Circulo
{
    public static final double PI = Math.PI ;  
    public static void main(String[] args)
    {
        double diam = 14.2 , altura = 20 ; 
        double volumen = ((1./3)*( PI*Math.pow((diam/2),2) * altura));
        System.out.println("El volumen del cono es: "+volumen+" cm³");
    }
}