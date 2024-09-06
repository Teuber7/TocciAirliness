package interfaz2;

public class Email implements Validaciones {
    private String nombre;
    private String email;

    public Email(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = validaEmail("Ingrese correo electrónico: ");
    }

    @Override
    public String toString() {
        return "Email [nombre=" + nombre + ", email=" + email + "]";
    }

    @Override
    public boolean ingresartel(int tel) {
        return false;
    }
}


