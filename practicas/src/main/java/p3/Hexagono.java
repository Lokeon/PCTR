
package p3;

public class Hexagono extends Poligono {
    
    public Hexagono() {
        super(6);
    }

    public Hexagono(Punto[] p) {

        super(p);
    }

    public double perimetro() {
        return super.perimetro();
    }

    public int nLados() {
        return super.nLados();
    }
    
    public double area(){
    
        double area = (perimetro() * (puntos.get(0).distancia(puntos.get(1)) / (2*Math.tan(30))))/2 ;
        
        return area ; 
    }
}