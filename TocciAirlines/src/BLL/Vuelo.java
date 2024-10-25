package BLL;
public class Vuelo {

    private String destino;
    private double precio;

    public Vuelo(String destino, double precio) {
        this.destino = destino;
        this.precio = precio;
    }

    public String getDestino() {
        return destino;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return destino + " - $" + precio;
    }
}
