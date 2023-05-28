
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Release
 */
@WebServlet("/Release")
public class Release extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mail = request.getParameter("mail");
		String session = request.getParameter("session");
		int idXip = Integer.parseInt(request.getParameter("idXip"));
		int idMed = Integer.parseInt(request.getParameter("idMed"));
		String date = request.getParameter("date");
		String mailP = request.getParameter("mailP");

		Doctor doctor = new Doctor();
		if (!doctor.isLogged(mail, session)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		try {
			ConnectionDB db = new ConnectionDB();
			String query = "INSERT INTO xip (id, id_medicine, date, id_patient) VALUES (?, ?, ?, ?)";
			PreparedStatement statement = db.getConn().prepareStatement(query);
			statement.setInt(1, idXip);
			statement.setInt(2, idMed);
			statement.setString(3, date);
			statement.setString(4, mailP);
			statement.executeUpdate();

			db.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
