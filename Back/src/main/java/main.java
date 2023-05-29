import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONObject;

public class main {
	  public static void main(String[] args) {
	        // Simulación de los parámetros de solicitud
	        String mail = "example@mail.com";
	        String session = "session123";

	        // Crear una instancia de ConnectionDB
	        ConnectionDB db = new ConnectionDB();

	        // Simulación de la conexión a la base de datos
	        try {
	            db.connectar();

	            // Simulación de la consulta a la base de datos
	            String query = "SELECT * FROM medicine";
	            ResultSet resultSet = db.getConn().createStatement().executeQuery(query);

	            JSONArray jsonArray = new JSONArray();
	            while (resultSet.next()) {
	                Medicine medicine = new Medicine();
	                medicine.load(resultSet.getInt("id"));
	                JSONObject json = new JSONObject(medicine);
	                jsonArray.put(json);
	            }

	            // Imprimir el JSONArray en la consola
	            System.out.println(jsonArray.toString());

	            // Cerrar la conexión a la base de datos
	            db.close();

	            // Aquí puedes realizar cualquier otra acción deseada con el JSONArray generado
	            // por ejemplo, escribirlo en un archivo, enviarlo a través de una API, etc.
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
}
