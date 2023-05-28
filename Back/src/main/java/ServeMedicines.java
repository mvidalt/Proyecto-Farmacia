
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
		ArrayList<Medicine> Listmedicines;
		Listmedicines = new ArrayList<>();
		ConnectionDB db = new ConnectionDB();
		Doctor doctor = new Doctor();
		boolean Logged = doctor.isLogged(mail, session);
		if (Logged) {
			try {
				db.connectar();
				String query = "SELECT mail from medicine";
				ResultSet resultSet = db.getConn().createStatement().executeQuery(query);
				  while (resultSet.next()) {
		                String mail2 = resultSet.getString("mail");
		                Listmedicines.add(mail2);
		            }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
