import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Patient extends Person {

	Patient() {
	}

	Patient(String name, String mail) {
		super(name, mail);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	ConnectionDB db = new ConnectionDB();
	@Override
	public void load(String id) {
		try {
			db.connectar();
			String query = "SELECT * FROM patient WHERE mail = ?";
			PreparedStatement statement = db.getConn().prepareStatement(query);
			statement.setString(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {

				setName(resultSet.getString("name"));
				setMail(resultSet.getString("mail"));

				System.out.println("Carga de datos exitosa.");
			} else {
				System.out.println("Paciente no encontrado.");
			}

			db.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
