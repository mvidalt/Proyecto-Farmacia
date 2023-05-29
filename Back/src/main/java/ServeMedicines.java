import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class ServeMedicines
 */
@WebServlet("/ServeMedicines")
public class ServeMedicines extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String mail = request.getParameter("mail");
		String session = request.getParameter("session");
		ConnectionDB db = new ConnectionDB();
		Doctor doctor = new Doctor();
		boolean Logged = doctor.isLogged(mail, session);
		if (Logged) {
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

				response.setContentType("application/json");
				PrintWriter out = response.getWriter();

				// Escribir el JSON en el PrintWriter de la respuesta
				out.print(jsonArray.toString());
				out.flush();

				// Cerrar la conexión a la base de datos
				db.close();

				// Aquí puedes realizar cualquier otra acción deseada con el JSONArray generado
				// por ejemplo, escribirlo en un archivo, enviarlo a través de una API, etc.
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
