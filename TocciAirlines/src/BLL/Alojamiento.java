package BLL;

public class Alojamiento {
    private int idAlojamiento;
    private String ubicacion;
    private String tipo;
    private double precio;
    private int capacidad;

    public Alojamiento(int idAlojamiento, String ubicacion, String tipo, double precio, int capacidad) {
        this.idAlojamiento = idAlojamiento;
        this.ubicacion = ubicacion;
        this.tipo = tipo;
        this.precio = precio;
        this.capacidad = capacidad;
    }

    public int getIdAlojamiento() {
        return idAlojamiento;
    }

    public void setIdAlojamiento(int idAlojamiento) {
        this.idAlojamiento = idAlojamiento;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "Alojamiento [idAlojamiento=" + idAlojamiento + ", ubicacion=" + ubicacion + ", tipo=" + tipo + ", precio=" + precio + ", capacidad=" + capacidad + "]";
    }
}