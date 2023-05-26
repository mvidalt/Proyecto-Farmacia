import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Medicine {
	
	int id;
	String name;
	float Tmax;
	float Tmin;
	ConnectionDB db = new ConnectionDB();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getTmax() {
		return Tmax;
	}

	public void setTmax(float tmax) {
		Tmax = tmax;
	}

	public float getTmin() {
		return Tmin;
	}

	public void setTmin(float tmin) {
		Tmin = tmin;
	}

	public Medicine() {
	}

	public Medicine(int id, String name, float tmax, float tmin) {
		this.id = id;
		this.name = name;
		this.Tmax = tmax;
		this.Tmin = tmin;
	}

	public void load(int id) {
		try {
			db.connectar();
			String query = "SELECT * FROM medicine WHERE id = ?";
			PreparedStatement statement = db.getConn().prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {

				setId(resultSet.getInt("id"));
				setName(resultSet.getString("name"));
				setTmax(resultSet.getInt("tmax"));
				setTmin(resultSet.getInt("tmin"));

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
