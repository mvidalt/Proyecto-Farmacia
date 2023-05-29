

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;
/**
 * Servlet implementation class ServePatients
 */

public class ServePatients extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mail = request.getParameter("mail");
        String session = request.getParameter("session");
        ConnectionDB db = new ConnectionDB();
        Doctor doctor = new Doctor();
        boolean logged = doctor.isLogged(mail, session);
        if (logged) {
            try {
                db.connectar();
                String query = "SELECT * FROM patients";
                ResultSet resultSet = db.getConn().createStatement().executeQuery(query);

                JSONArray jsonArray = new JSONArray();
                while (resultSet.next()) {
                    String patientEmail = resultSet.getString("mail");

                    JSONObject json = new JSONObject();
                    json.put("mail", patientEmail);
                    jsonArray.put(json);
                }
                db.close();


                // Configurar la respuesta como JSON
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.print(jsonArray.toString());
                out.flush();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
