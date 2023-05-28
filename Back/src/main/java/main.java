
public class main {

	public static void main(String[] args) {
	    Xip xip = new Xip();
	    int xipId = 0; // ID del Xip que deseas cargar

	    xip.load(xipId);

	    System.out.println("Datos del Xip cargados:");
	    System.out.println("ID: " + xip.getId());
	    System.out.println("Medicina: " + xip.getMedicine().getName());
	    System.out.println("Paciente: " + xip.getPatient().getName());
	    System.out.println("Fecha de finalización: " + xip.getDate());
	    
	    Doctor doctor = new Doctor();
	    doctor.load("miguel11nemo@gmail.com");
	    doctor.loadReleaseList();
	    System.out.print(doctor.getTable());
	    
	}


}
