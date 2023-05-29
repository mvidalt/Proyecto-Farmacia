

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class ServeXips
 */
public class ServeXips extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mail = request.getParameter("email");
        String session = request.getParameter("session");
        System.out.println(session);

        Doctor doctor = new Doctor();
        boolean isLogged = doctor.isLogged(mail, session);

        System.out.println(session);

        if (isLogged) {
            doctor.load(mail);
            doctor.loadReleaseList();
            String table = doctor.getTable();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(table);
        } else {
            response.setContentType("text/plain");
            PrintWriter out = response.getWriter();
            out.println("Access denied");
        }
    }
}

