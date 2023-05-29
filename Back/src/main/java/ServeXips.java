
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServeXips
 */
@WebServlet("/ServeXips")
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

