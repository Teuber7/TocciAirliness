package BLL;

public class Vuelo {
	 private int idVuelo;
	    private String origen;
	    private String destino;
	    private String fechaSalida;
	    private String fechaLlegada;
	    private String clase;
	    private double precioEconomica;
	    private double precioPremium;
	    private double precioPrimera;
        private int capacidadEconomica;
        private int capacidadPremium;
        private int capacidadPrimera;
        private int asientosDisponiblesEconomica;
        private int asientosDisponiblesPremium;
        private int asientosDisponiblesPrimera;
   
        public Vuelo(int idVuelo, String origen, String destino, String fechaSalida, String fechaLlegada,
                int capacidadEconomica, int capacidadPremium, int capacidadPrimera,
                double precioEconomica, double precioPremium, double precioPrimera) {
       this.idVuelo = idVuelo;
       this.origen = origen;
       this.destino = destino;
       this.fechaSalida = fechaSalida;
       this.fechaLlegada = fechaLlegada;
       this.capacidadEconomica = capacidadEconomica;
       this.capacidadPremium = capacidadPremium;
       this.capacidadPrimera = capacidadPrimera;
       this.precioEconomica = precioEconomica;
       this.precioPremium = precioPremium;
       this.precioPrimera = precioPrimera;
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

		 public double getPrecioEconomica() { return precioEconomica; }
		    public void setPrecioEconomica(double precioEconomica) { this.precioEconomica = precioEconomica; }

		    public double getPrecioPremium() { return precioPremium; }
		    public void setPrecioPremium(double precioPremium) { this.precioPremium = precioPremium; }

		    public double getPrecioPrimera() { return precioPrimera; }
		    public void setPrecioPrimera(double precioPrimera) { this.precioPrimera = precioPrimera; }

		public int getCapacidadEconomica() {
			return capacidadEconomica;
		}


		public void setCapacidadEconomica(int capacidadEconomica) {
			this.capacidadEconomica = capacidadEconomica;
		}


		public int getCapacidadPremium() {
			return capacidadPremium;
		}


		public void setCapacidadPremium(int capacidadPremium) {
			this.capacidadPremium = capacidadPremium;
		}


		public int getCapacidadPrimera() {
			return capacidadPrimera;
		}


		public void setCapacidadPrimera(int capacidadPrimera) {
			this.capacidadPrimera = capacidadPrimera;
		}


		public int getAsientosDisponiblesEconomica() {
			return asientosDisponiblesEconomica;
		}


		public void setAsientosDisponiblesEconomica(int asientosDisponiblesEconomica) {
			this.asientosDisponiblesEconomica = asientosDisponiblesEconomica;
		}


		public int getAsientosDisponiblesPremium() {
			return asientosDisponiblesPremium;
		}


		public void setAsientosDisponiblesPremium(int asientosDisponiblesPremium) {
			this.asientosDisponiblesPremium = asientosDisponiblesPremium;
		}


		public int getAsientosDisponiblesPrimera() {
			return asientosDisponiblesPrimera;
		}


		public void setAsientosDisponiblesPrimera(int asientosDisponiblesPrimera) {
			this.asientosDisponiblesPrimera = asientosDisponiblesPrimera;
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
        return "Vuelo [idVuelo=" + idVuelo + ", origen=" + origen + ", destino=" + destino +
               ", fechaSalida=" + fechaSalida + ", fechaLlegada=" + fechaLlegada +
               ", precioEconomica=" + precioEconomica + ", precioPremium=" + precioPremium +
               ", precioPrimera=" + precioPrimera + ", capacidadEconomica=" + capacidadEconomica +
               ", capacidadPremium=" + capacidadPremium + ", capacidadPrimera=" + capacidadPrimera + "]";
    }
}