import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/ServePatients")
public class ServePatients extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mail = request.getParameter("email");
        String session = request.getParameter("session");
        ConnectionDB db = new ConnectionDB();
        Doctor doctor = new Doctor();
        boolean logged = doctor.isLogged(mail, session);

        if (logged) {
            try {
                db.connectar();
                String query = "SELECT * FROM patient";
                ResultSet resultSet = db.getConn().createStatement().executeQuery(query);

                List<JSONObject> jsonList = new ArrayList<>();
                while (resultSet.next()) {
                    String patientEmail = resultSet.getString("mail");

                    JSONObject json = new JSONObject();
                    json.put("mail", patientEmail);
                    jsonList.add(json);
                }
                db.close();

                JSONArray jsonArray = new JSONArray(jsonList);

                // Configure the response as JSON
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().append(jsonArray.toString());

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
