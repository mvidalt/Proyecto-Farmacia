import java.time.LocalDate;

public class Xip {

	int id;
	Medicine medicine;
	Patient patient;
	LocalDate date;

	Xip() {
	}

	Xip(int id, Medicine medicine, Patient patient, LocalDate date) {
		this.id = id;
		this.medicine = medicine;
		this.patient = patient;
		this.date = date;
	}
	
    public void load(int id) {
        // Load attributes of the Xip from the database
    }
    

}
