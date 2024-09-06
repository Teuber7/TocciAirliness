package interfaz2;

public class Telefono implements Validaciones {
	private String nombre;
	private int tel;
	protected Telefono(String nombre, int tel) {
		super();
		this.nombre = nombre;
		this.tel = tel;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getTel() {
		return tel;
	}
	public void setTel(int tel) {
		this.tel = validaTel (", Ingresar numero telefonico: ");
	}
	@Override
	public String toString() {
		return "Telefono [nombre=" + nombre + ", tel=" + tel + ", getNombre()=" + getNombre() + ", getTel()=" + getTel()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	@Override
	public boolean ingresartel(int tel) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}