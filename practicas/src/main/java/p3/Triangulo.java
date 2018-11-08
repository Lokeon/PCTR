
package p3;

public class Triangulo extends Poligono {
    
    public Triangulo() {
        super(3);
    }

    public Triangulo(Punto[] p) {

        super(p);
    }

    public double perimetro() {
        return super.perimetro();
    }

    public int nLados() {
        return super.nLados();
    }

    // Formula de Heron
    public double area() {
        
        double peri = perimetro();
        double res = Math.sqrt( peri*(peri - puntos.get(0).distancia(puntos.get(1))) * (peri - puntos.get(1).distancia(puntos.get(2)))* (peri - puntos.get(2).distancia(puntos.get(0))));
        
        return res ; 
    }

}