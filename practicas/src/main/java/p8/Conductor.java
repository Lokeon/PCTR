
package p8;

/**
 *
 * @author Kevin López Cala
 */
public class Conductor {

    private String nombre, dni, direccion, seguro;
    private int telf;

    public Conductor(String nombre, String dni, String dire, String seg, int tel) {

        this.nombre = nombre;
        this.dni = dni;
        this.direccion = dire;
        this.seguro = seg;
        this.telf = tel;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSeguro() {
        return seguro;
    }

    public void setSeguro(String seguro) {
        this.seguro = seguro;
    }

    public int getTelf() {
        return telf;
    }

    public void setTelf(int telf) {
        this.telf = telf;
    }

    public String toString() {

        return "Nombre: " + nombre + "\nDNI: " + dni + "\nDireccion: " + direccion + "\nTelefono: " + telf
                + "\nSeguro: " + seguro + "\n";
    }
}