package BLL;

public class Paquete {
    private int idPaquete;
    private int idVuelo;
    private int idActividad;
    private int idAlojamiento;
    private double precioTotal;

    public Paquete(int idPaquete, int idVuelo, int idActividad, int idAlojamiento, double precioTotal) {
        this.idPaquete = idPaquete;
        this.idVuelo = idVuelo;
        this.idActividad = idActividad;
        this.idAlojamiento = idAlojamiento;
        this.precioTotal = precioTotal;
    }

    public int getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(int idPaquete) {
        this.idPaquete = idPaquete;
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public int getIdAlojamiento() {
        return idAlojamiento;
    }

    public void setIdAlojamiento(int idAlojamiento) {
        this.idAlojamiento = idAlojamiento;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    @Override
    public String toString() {
        return "Paquete [idPaquete=" + idPaquete + ", idVuelo=" + idVuelo + ", idActividad=" + idActividad + ", idAlojamiento=" + idAlojamiento + ", precioTotal=" + precioTotal + "]";
    }
}
