
package p2 ;

class Paciente
{
    public Paciente(String nombre, String dni, String direccion, String compseguro, String telefono) {
        this.nombre = nombre;
        this.dni = dni;
        this.direccion = direccion;
        this.compseguro = compseguro;
        this.telefono = telefono;
    }
    
    public String getNombre() {
        return this.nombre;
    }

    public String getDni() {
        return this.dni;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public String getCompseguro() {
        return this.compseguro;
    }

    public String getTelefono() {
        return this.telefono;
    }

    @Override
    public String toString() {
        return  
            "Nombre: " + getNombre() + "\n" +
            "Dni: " + getDni() + "\n" +
            "Direccion: " + getDireccion() + "\n" +
            "Compañia Seguro: " + getCompseguro() + "\n" +
            "Telefono:" + getTelefono() + "\n" ;
    } 

    private String nombre; 
    private String dni;
    private String direccion; 
    private String compseguro; 
    private String telefono;

   

    
}