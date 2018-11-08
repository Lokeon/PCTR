
package p3;

public class Cuadrado extends Poligono {

    public Cuadrado() {
        super(4);
    }

    public Cuadrado(Punto[] p) {

        super(p);
    }

    public double perimetro() {
        return super.perimetro();
    }

    public int nLados() {
        return super.nLados();
    }

    public double area() {
        double area = Math.pow(puntos.get(0).distancia(puntos.get(1)),2);
        return area ; 
    }
}