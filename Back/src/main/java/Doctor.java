import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.math.BigInteger;

public class Doctor extends Person {

	String pass;
	LocalDate lastLog;
	String session;
	ArrayList<Xip> releaseList;
	ConnectionDB db = new ConnectionDB();

	public void setName(String name) {
		this.name = name;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMail() {
		return mail;
	}

	public String getPass() {
		return pass;
	}
	

	public void setPass(String pass) {
		this.pass = pass;
	}

	public LocalDate getLastLog() {
		return lastLog;
	}

	public void setLastLog(LocalDate lastLog) {
		this.lastLog = lastLog;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public ArrayList<Xip> getReleaseList() {
		return releaseList;
	}

	public void setReleaseList(ArrayList<Xip> releaseList) {
		this.releaseList = releaseList;
	}

	Doctor() {
	}

	Doctor(String name, String mail, String pass, LocalDate lastLog, String session) {
		super(name, mail);
		this.pass = pass;
		this.lastLog = lastLog;
		this.session = session;
	}

	public void login(String mail, String pass) {
		try {
			db.connectar();
			// Verificar si el correo electrónico y la contraseña son correctos
			String query = "SELECT * FROM doctor WHERE mail = ? AND pass = ?";
			PreparedStatement statement = db.getConn().prepareStatement(query);
			statement.setString(1, mail);
			statement.setString(2, pass);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				System.out.println("Inicio de sesión exitoso.");

				// Cargamos los datos al objeto
				load(mail);
				// Generar un código de sesión de 10 dígitos aleatorios
				Random random = new Random();
				String sessionNumber = "";
				for (int i = 0; i < 9; i++) {
					int digit = random.nextInt(10);
					sessionNumber += digit;
				}

				try {
					String updateQuery = "UPDATE doctor SET session = ?, last_log = ? WHERE mail = ?";
					PreparedStatement statement2 = db.getConn().prepareStatement(updateQuery);
					statement2.setInt(1, Integer.parseInt(sessionNumber)); // Establecer el código de sesión aleatorio
					statement2.setObject(2, LocalDate.now());
					statement2.setString(3, mail);
					statement2.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				session = sessionNumber;

			} else {
				// El correo electrónico y/o la contraseña son incorrectos
				session = "";
				System.out.println("Credenciales inválidas.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isLogged(String mail, String session) {
	    try {
	        db.connectar();
	        String query = "SELECT * FROM doctor WHERE mail = ? AND session = ?";
	        PreparedStatement statement = db.getConn().prepareStatement(query);
	        statement.setString(1, mail);
	        statement.setString(2, session);
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            // Sesión válida, los datos se han cargado correctamente
	            return true;
	        } else {
	            // Sesión inválida, los datos no coinciden
	            return false;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false; // En caso de error, se considera la sesión como inválida
	    } finally {
	        db.close();
	    }
	}


	@Override
	public void load(String id) {
		try {
			db.connectar();
			String query = "SELECT * FROM doctor WHERE mail = ?";
			PreparedStatement statement = db.getConn().prepareStatement(query);
			statement.setString(1, id);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				// Cargar los datos del doctor en el objeto
				setName(resultSet.getString("name"));
				setMail(resultSet.getString("mail"));
				setPass(resultSet.getString("pass"));
				setLastLog(resultSet.getDate("last_log").toLocalDate());
				setSession(resultSet.getString("session"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void loadReleaseList() {
		try {
			db.connectar();
			releaseList = new ArrayList<>();
			String query = "SELECT * FROM xip WHERE doctor_mail = ?";
			PreparedStatement statement = db.getConn().prepareStatement(query);
			statement.setString(1,getMail());
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				int id1 = resultSet.getInt("id");
				Xip xip = new Xip();
				xip.load(id1);
				releaseList.add(xip);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (Xip i : releaseList) {
			System.out.print(i.getId());
		}
	}

	public String getTable() {
	    StringBuilder table = new StringBuilder();
	    table.append("<table>");
	    table.append("<tr><th>ID</th><th>Medicine</th><th>Patient</th><th>Date</th></tr>");

	    for (Xip xip : releaseList) {
	        if (xip.getDate().isAfter(LocalDate.now())) {
	            table.append("<tr>");
	            table.append("<td>").append(xip.getId()).append("</td>");
	            table.append("<td>").append(xip.getMedicine().getName()).append("</td>");
	            table.append("<td>").append(xip.getPatient().getName()).append("</td>");
	            table.append("<td>").append(xip.getDate()).append("</td>");
	            table.append("</tr>");
	        }
	    }

	    table.append("</table>");
	    return table.toString();
	}


}
