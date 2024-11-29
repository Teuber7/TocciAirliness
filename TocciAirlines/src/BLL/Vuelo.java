package BLL;

public class Vuelo {
    private int idVuelo;
    private String origen;
    private String destino;
    private String fechaSalida;
    private String fechaLlegada;
    private String clase;
    private double precio;

    public Vuelo(int idVuelo, String origen, String destino, String fechaSalida, String fechaLlegada, String clase, double precio) {
        this.idVuelo = idVuelo;
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.clase = clase;
        this.precio = precio;
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(String fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Vuelo [idVuelo=" + idVuelo + ", origen=" + origen + ", destino=" + destino + ", fechaSalida=" + fechaSalida +
                ", fechaLlegada=" + fechaLlegada + ", clase=" + clase + ", precio=" + precio + "]";
    }
}
