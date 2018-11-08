
package p3;

import java.util.*;

public class Poligono {

    protected List<Punto> puntos;

    public Poligono(int tam) {
        puntos = new ArrayList<Punto>(tam);
    }
    
    public Poligono(Punto[] punto) {  
        puntos = new ArrayList<Punto>(punto.length);
        
        for(int i = 0 ; i < punto.length ; ++i) {
            puntos.add(punto[i]);
        }
    }
    
    public void anadirPunto(Punto punto) {
        puntos.add(punto);
    }

    public double perimetro() {

        double per = 0.0;

        for(int i = 0 ; i < nLados() - 1; ++i) {
            per += puntos.get(i).distancia(puntos.get(i+1));
        }
        
        per += puntos.get(0).distancia(puntos.get(nLados() - 1 )) ; 
        
        return per ; 
    }
    
      
  public int nLados(){
        return puntos.size() ;
   }
 
  
 
  public void escalar(int k) {
 
    for(int i = 0 ; i < nLados() ; ++i) {
        puntos.get(i).setX(puntos.get(i).getX() * k);
        puntos.get(i).setY(puntos.get(i).getY() * k);
    }
  }

}