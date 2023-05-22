import java.time.LocalDate;
import java.util.ArrayList;

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
		// Si mail y pass son iguales
		// envia mail y pass a la BBDD con el metodo load()
		// Tambien enviar el lastLog y session a la BBDD
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
		//todas las fichas activas asociadas al médico        
		return "";
	}
	
}

