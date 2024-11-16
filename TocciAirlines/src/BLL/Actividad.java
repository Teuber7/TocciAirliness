package BLL;

public class Actividad {
    private int idActividad;
    private String nombre;
    private String descripcion;
    private double precio;

    public Actividad(int idActividad, String nombre, String descripcion, double precio) {
        this.idActividad = idActividad;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

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

    @Override
    public String toString() {
        return "Actividad [idActividad=" + idActividad + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio + "]";
    }
}
