package BLL;

import DLL.ControllerUsuario;


public class Usuario {
    private int idUsuario;
    private String username;
    private String apellido;
    private String email;
    private String password;
    private String rol;

    public Usuario(int idUsuario, String username, String apellido, String email, String password, String rol) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }


	// Getters y setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    // Método de autenticación
    public static boolean login(String email, String password) {
        ControllerUsuario controller = new ControllerUsuario();
        Usuario usuario = controller.buscarUsuarioPorEmail(email);
        return usuario != null && usuario.getPassword().equals(password);
    }

    @Override
    public String toString() {
        return "Usuario [idUsuario=" + idUsuario + ", username=" + username + ", apellido=" + apellido + 
               ", email=" + email + ", rol=" + rol + "]";
    }
}
