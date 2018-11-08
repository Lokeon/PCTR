
package p3;

import java.util.* ; 

/**
 * @author Kevin López Cala
 */

public class usaTodo {
    
    public static void main(String[] args){
    
        // Punto
        int elec , pp  ; 
        double x , y  ;
        
        Scanner S = new Scanner(System.in) ;
        System.out.println("Introduzca 2 puntos  ");
        System.out.print("\n");
        
        Punto[] points = new Punto[2] ;
        Punto[] tria = new Punto[3] ;
        Punto[] cuad = new Punto[4] ;
        Punto[] pent = new Punto[5] ;
        Punto[] hexa = new Punto[6] ;
        
        System.out.println("Clase Punto ");
        
        for(int i = 0 ; i < points.length ; ++i){
        
            if(points[i] == null){
            
            System.out.println("Punto "+(i+1));    
            System.out.print("Coordenada X "+(i+1)+" del vector ");
             x = S.nextDouble() ;
            System.out.print("Coordenaa Y "+(i+1)+" del vector ");
             y = S.nextDouble() ;
            
            points[i] = new Punto(x ,y) ;
          } 
        }    
             
         System.out.println("Distancia entre el primer punto y el segundo punto "+points[0].distancia(points[1]));
         System.out.println("Introduzca un punto a trasladar ");
         int a = S.nextInt() ;
         System.out.println("Introduzca dos valores para trasladar X e Y ");
         double b = S.nextDouble() ; double c = S.nextDouble();
         System.out.println("Punto original "+points[a]+" en el cuadrante "+points[a].cuadrante());
         points[a].moverEn(b, c);
         System.out.println("Punto transladado "+points[a]+" en el cuadrante "+points[a].cuadrante()); 
         System.out.print("\n");
        
           
        System.out.println("Clase Poligono ");  
        
        do{
           do{
              
              System.out.println("Elija un poligono : \n1-Triangulo\n2-Cuadradado\n3-Pentagono\n4-Hexagono");
               elec = S.nextInt() ;
            
            }while(elec < 1 || elec > 4) ;
        
           switch(elec){
               
        case 1 : 
             
           for(int i = 0 ; i < 3 ; ++i) {
        
            if(tria[i] == null) {
            
            System.out.println("Punto "+(i+1));    
            System.out.print("Coordenada X "+(i+1)+" del Triangulo ");
             x = S.nextDouble() ;
            System.out.print("Coordenaa Y "+(i+1)+"  del Triangulo ");
             y = S.nextDouble() ;
            
            tria[i] = new Punto(x ,y) ;
          } 
        }    
            
             Triangulo tri = new Triangulo(tria) ; 
             System.out.print("\n");
             System.out.println("El triangulo tiene "+tri.nLados()+" lados");
             System.out.println("El perimetro es : "+tri.perimetro());    
             System.out.println("Su area es "+tri.area()); break ; 
                   
        case 2 :
            
            for(int i = 0 ; i < cuad.length ; ++i){
        
            if(cuad[i] == null){
            
            System.out.println("Punto "+(i+1));    
            System.out.print("Coordenada X "+(i+1)+" del Cuadrado ");
             x = S.nextDouble() ;
            System.out.print("Coordenaa Y "+(i+1)+"  del Cuadrado ");
             y = S.nextDouble() ;
            
            cuad[i] = new Punto(x ,y) ;
                }
            }      
             Cuadrado cua = new Cuadrado(cuad) ;
             System.out.print("\n");
             System.out.println("El cuadrado tiene "+cua.nLados()+" lados");
             System.out.println("El perimetro es : "+cua.perimetro());    
             System.out.println("Su area es "+cua.area()); break ; 
           
        case 3 :
                 
            for(int i = 0 ; i < pent.length ; ++i){
        
            if(pent[i] == null){
            
            System.out.println("Punto "+(i+1));    
            System.out.print("Coordenada X "+(i+1)+" del Pentagono ");
             x = S.nextDouble() ;
            System.out.print("Coordenaa Y "+(i+1)+"  del Pentagono ");
             y = S.nextDouble() ;
            
            pent[i] = new Punto(x ,y) ;
               }
            }      
             Pentagono penta = new Pentagono(pent) ;
             System.out.print("\n");
             System.out.println("El pentagono tiene "+penta.nLados()+" lados");
             System.out.println("El perimetro es : "+penta.perimetro());    
             System.out.println("Su area es "+penta.area()); break ; 
               
         case 4 :  
                   
            for(int i = 0 ; i < hexa.length ; ++i){
        
            if(hexa[i] == null){
            
            System.out.println("Punto "+(i+1));    
            System.out.print("Coordenada X "+(i+1)+" del Hexagono ");
             x = S.nextDouble() ;
            System.out.print("Coordenaa Y "+(i+1)+"  del Hexagono ");
             y = S.nextDouble() ;
            
            hexa[i] = new Punto(x ,y) ;
               }
            }      
             Hexagono hex = new Hexagono (hexa) ;
             System.out.print("\n");
             System.out.println("El hexagono tiene "+hex.nLados()+" lados");
             System.out.println("El perimetro es : "+hex.perimetro());    
             System.out.println("Su area es "+hex.area()); break ;     
                 
           }
               System.out.println("\n");
               System.out.println("Pulse 0 para volver atras");
                pp = S.nextInt() ;  
           
        }while(pp == 0) ;   
        
        S.close();           
    }
}