
package p3;

public class Punto {
  double x, y; // Variables de instancia

  public Punto(double x, double y) { // El constructor
    this.x= x; this.y= y;
  }
  public void moverEn(double dx, double dy) { // Un método
    this.x+= dx; this.y+= dy;
  }
  public String toString() { // Otro método
    return "("+this.x+","+this.y+")";
  }

  public double distancia(Punto p) {
        return Math.hypot(p.getX() - this.x, p.getY() - this.y);
  }

  public int cuadrante (){
    if(x >= 0)
        if(y >= 0) return 1 ;
        else return 4 ; 
    else
        if(y >= 0) return 2 ; 
        else return 3 ;
    }

  public double getY() {
    return this.y;
  }

  public void setY(double y) {
    this.y =y ;
  }
  public double getX() {
    return this.x;
  }

  public void setX(double x) {
    this.x = x ;
  }
}
