
package p3;

public class Pentagono extends Poligono {
    
    public Pentagono() {
        super(5);
    }

    public Pentagono(Punto[] p) {

        super(p);
    }

    public double perimetro() {
        return super.perimetro();
    }

    public int nLados() {
        return super.nLados();
    }
    
    public double area(){
    
        double area = (perimetro() * (puntos.get(0).distancia(puntos.get(1)) / (2*Math.tan(36))))/2 ;
        
        return area ;    
    }
    
}