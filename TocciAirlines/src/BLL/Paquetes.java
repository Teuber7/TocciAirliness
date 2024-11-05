package BLL;

public class Paquetes {
    private String descripcion;
    private double precio;
    private String nombre;

    // Constructor
    public Paquetes(String nombre, String descripcion, double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    // Métodos Getter y Setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Método para representar el paquete en formato de cadena
    @Override
    public String toString() {
        return "Paquete: " + nombre + "\n" +
               "Descripción: " + descripcion + "\n" +
               "Precio: $" + precio;
    }
}
