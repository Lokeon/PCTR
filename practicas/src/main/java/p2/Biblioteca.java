package p2;

import java.util.*;

/**
 * @author Kevin López Cala
 */
class Biblioteca
{
    private List<Paciente> pacientes_ = new ArrayList<Paciente>() ;
    
   
    /**
    * Método que nos inserta los Pacientes en el ArrayList 
    */
    public void insertar()
    {
        Scanner s = new Scanner(System.in);

        System.out.println("Nombre: ");
        String nombre = s.nextLine();
        System.out.println("DNI: ");
        String dni = s.nextLine();
        System.out.println("Telefono: ");
        String telefono = s.nextLine();
        System.out.println("Direccion: ");
        String direccion = s.nextLine();
        System.out.println("Compañia de Seguro: ");
        String compseguro = s.nextLine();

        
        pacientes_.add(new Paciente(nombre, dni, direccion, compseguro, telefono));

        s.close();
    }

    /**
     * Método que nos borra un Paciente del ArrayList según un DNI
     */
    public void borrar()
    {
        Scanner s = new Scanner(System.in);

        System.out.println("Introduzca el dni del paciente para eliminar: ");
        String dni = s.nextLine();

        for(int i = 0 ; i < pacientes_.size() ; ++i)
        {
            if(pacientes_.get(i).getDni().equals(dni))
            {
                pacientes_.remove(i);
            }
        }
    
        s.close();
    }

    /**
     * Método que nos muestra la información de un Paciente
     */
    public void consultar()
    {
        Scanner s = new Scanner(System.in);

        System.out.println("Introduzca el dni del paciente a consultar: ");
        String dni = s.nextLine();

        for(int i = 0 ; i < pacientes_.size() ; ++i)
        {
            if(pacientes_.get(i).getDni().equals(dni))
            {
                System.out.println(pacientes_.get(i));
            }
        }
        
        s.close();
    }

    /**
     * Función para el menú 
     */
    static void menu()
    {
        System.out.println("1- Insertar Paciente");
        System.out.println("2- Borrar Paciente");
        System.out.println("3- Consultar Paciente");
        System.out.println("0- Salir");
    }
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        Biblioteca b = new Biblioteca();
        int opc ;

        do
        {
            menu() ;    
            opc = s.nextInt();
            switch(opc)
            {
                case 1: b.insertar(); break;
                case 2: b.borrar(); break;
                case 3: b.consultar(); break;
            }

        }while(opc != 0);

        s.close();

    }

}