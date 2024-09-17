
import javax.swing.JOptionPane;

public class Admin extends Usuario  implements Menu{

		private static int incremental ;
		private int nro;
		public Admin(String mail, String contrasena, String rol) {
			super(mail, contrasena, rol);
			incremental++;
			this.nro = nro;
		}
		@Override
	public void menu() {
			JOptionPane.showMessageDialog(null, "Menu admin");

		}
		@Override
		public void menuPrincipal() {
			JOptionPane.showMessageDialog(null, "Menu admin desde interface");
			
		}
		
		
	}

}
