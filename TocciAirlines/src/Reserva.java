public class Reserva {

    private Vuelo vuelo;
    private PaqueteActividades paquete;

    public Reserva(Vuelo vuelo, PaqueteActividades paquete) {
        this.vuelo = vuelo;
        this.paquete = paquete;
    }

    @Override
    public String toString() {
        return "Vuelo: " + vuelo.getDestino() + ", Paquete: " + paquete;
    }
}
