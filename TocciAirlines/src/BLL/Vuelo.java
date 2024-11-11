package BLL;

public class Vuelo {
	 private int idVuelo;
	    private String origen;
	    private String destino;
	    private String fechaSalida;
	    private String fechaLlegada;
	    private String clase;
	    private double precio;
        private int capacidadEconomica;
        private int capacidadPremium;
        private int capacidadPrimera;
        private int asientosDisponiblesEconomica;
        private int asientosDisponiblesPremium;
        private int asientosDisponiblesPrimera;
   
    public Vuelo(int idVuelo, String origen, String destino, String fechaSalida, String fechaLlegada, String string, double precio,
                 int capacidadEconomica, int capacidadPremium, int capacidadPrimera) {
        this.idVuelo = idVuelo;
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.precio = precio;
        this.capacidadEconomica = capacidadEconomica;
        this.capacidadPremium = capacidadPremium;
        this.capacidadPrimera = capacidadPrimera;
        this.asientosDisponiblesEconomica = capacidadEconomica;
        this.asientosDisponiblesPremium = capacidadPremium;
        this.asientosDisponiblesPrimera = capacidadPrimera;
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public String getFechaLlegada() {
        return fechaLlegada;
    }

    public double getPrecio() {
        return precio;
    }

    public int getAsientosDisponiblesEconomica() {
        return asientosDisponiblesEconomica;
    }

    public int getAsientosDisponiblesPremium() {
        return asientosDisponiblesPremium;
    }

    public int getAsientosDisponiblesPrimera() {
        return asientosDisponiblesPrimera;
    }

    public boolean comprarAsientos(String clase, int cantidad) {
        switch (clase.toLowerCase()) {
            case "economica":
                if (asientosDisponiblesEconomica >= cantidad) {
                    asientosDisponiblesEconomica -= cantidad;
                    return true;
                }
                break;
            case "premium":
                if (asientosDisponiblesPremium >= cantidad) {
                    asientosDisponiblesPremium -= cantidad;
                    return true;
                }
                break;
            case "primera":
                if (asientosDisponiblesPrimera >= cantidad) {
                    asientosDisponiblesPrimera -= cantidad;
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Vuelo [idVuelo=" + idVuelo + ", origen=" + origen + ", destino=" + destino + ", fechaSalida=" + fechaSalida +
                ", fechaLlegada=" + fechaLlegada + ", precio=" + precio +
                ", asientosDisponiblesEconomica=" + asientosDisponiblesEconomica +
                ", asientosDisponiblesPremium=" + asientosDisponiblesPremium +
                ", asientosDisponiblesPrimera=" + asientosDisponiblesPrimera + "]";
    }
}