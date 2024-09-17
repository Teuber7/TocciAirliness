
import java.util.LinkedList;

public class Usuario {
	private String mail;
	private String contrasena;
	private String rol;
	
	public Usuario(String mail, String contrasena, String rol) {
		super();
		this.mail = mail;
		this.contrasena = contrasena;
		this.rol = rol;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Usuario [mail=" + mail + ", contrasena=" + contrasena + ", rol=" + rol + "]";
	}
	
	public static Usuario Login(LinkedList<Usuario> usuarios, String mail, String cotrasena ) {
	
		for (Usuario usuario : usuarios) {
			if (usuario.getMail().equals(mail) && usuario.getContrasena().equals(cotrasena)) {
				return usuario;
			}
		}
		return null;
	}
	public void menu() {
		
	}
	
	
}
