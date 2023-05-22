
public class Patient extends Person{

	Patient() {
	}

	Patient(String name, String mail) {
		super(name, mail);
	}

	@Override
	public void load(String id) {
		// Cargar los datos del doctor desde la base de datos
		// en base al id dado (BBDD: farmacia.doctor.mail)
	}
	
}
