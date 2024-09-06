package interfaz2;

public class Contraseña implements Validaciones {
    private String nombre;
    private String contraseña;

    public Contraseña(String nombre, String contraseña) {
        this.nombre = nombre;
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = validaContrasena("Ingrese contraseña: ");
    }

    @Override
    public String toString() {
        return "Contraseña [nombre=" + nombre + ", contraseña=" + contraseña + "]";
    }

    @Override
    public boolean ingresartel(int tel) {
        return false;
    }
}



	
