import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
	

public class Xip {

	int id;
	Medicine medicine;
	Patient patient;
	LocalDate date;
	ConnectionDB db = new ConnectionDB();

	Xip() {
	}

	Xip(int id, Medicine medicine, Patient patient, LocalDate date) {
		this.id = id;
		this.medicine = medicine;
		this.patient = patient;
		this.date = date;
	}
	
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void load(int id) {
        try {
            db.connectar();
            String query = "SELECT * FROM xip WHERE id = ?";
            PreparedStatement statement = db.getConn().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
            	
            	int id1 = resultSet.getInt("id");
            	int id_medicine = resultSet.getInt("id_medicine");
            	String id_patient = resultSet.getString("id_patient");
            	LocalDate date = resultSet.getDate("date").toLocalDate();
            	
            	Medicine medicine = new Medicine();
            	Patient patient = new Patient();
            	
            	medicine.load(id_medicine);
            	patient.load(id_patient);
            	
            	setId(id1);
            	setMedicine(medicine);
            	setPatient(patient);
            	setDate(date);



                

                System.out.println("Carga de datos exitosa para el Xip con ID: " + id);
            } else {
                System.out.println("Xip no encontrado con ID: " + id);
            }

            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

}
