import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor extends Person {

	String pass;
	LocalDate lastLog;
	String session;
	ArrayList<Xip> releaseList;

	Doctor() {
	}

	Doctor(String pass, LocalDate lastLog, String session) {
		super();
		this.pass = pass;
		this.lastLog = lastLog;
		this.session = session;
	}

	public void login(String mail, String pass) {
		ConnectionDB db = new ConnectionDB();
		try {
			db.connectar();
			// Verificar si el correo electrónico y la contraseña son correctos
	        String query = "SELECT * FROM doctor WHERE mail = ? AND pass = ?";
			PreparedStatement statement = db.getConn().prepareStatement(query);
            statement.setString(1, mail);
            statement.setString(2, pass);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				//load();
				

				System.out.println("Inicio de sesión exitoso.");
			} else {
				// El correo electrónico y/o la contraseña son incorrectos
				System.out.println("Credenciales inválidas.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	

	public boolean isLogged(String mail, String session) {
		// Comprueba si el mail y la session son correctos
		// Si coincide devuelve true y carga los datos con login()
		// Si no da false
		return false;
	}

	@Override
	public void load(String id) {
		// Cargar los datos del doctor desde la base de datos
		// en base al id dado (BBDD: farmacia.doctor.mail)
	}

	public void loadReleaseList() {
		// Carga todas las fichas de la base de datos vinculadas
		// al doctor en el array releaseList del doctor
	}

	public String getTable() {
		// Devuelve una cadena de tabla HTML con
		// todas las fichas activas asociadas al médico
		return "";
	}

}
