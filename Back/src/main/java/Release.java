
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class Release
 */
public class Release extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Release() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String doctor_mail = request.getParameter("email");
		String session = request.getParameter("session");
		System.out.print(doctor_mail);
		int idXip = Integer.parseInt(request.getParameter("idXip"));
		int idMed = Integer.parseInt(request.getParameter("idMed"));
		String dateLimit = request.getParameter("dateLimit");
		String mailP = request.getParameter("mailP");
		ConnectionDB db = new ConnectionDB();
		System.out.print("Release:"+session);
		Doctor doctor = new Doctor();
		boolean Logged = doctor.isLogged(doctor_mail, session);
		if (Logged) {
			try {
				db.connectar();
				String query = "INSERT INTO xip (id, doctor_mail, id_medicine, id_patient, date) VALUES (?, ?, ?, ?,?)";
				PreparedStatement statement = db.getConn().prepareStatement(query);
				statement.setInt(1, idXip);
				statement.setString(2, doctor_mail);
				statement.setInt(3, idMed);
				statement.setString(4, mailP);
				statement.setString(5, dateLimit);
				statement.executeUpdate();

				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
				response.getWriter().write("error");
			}
		}
	}

}
