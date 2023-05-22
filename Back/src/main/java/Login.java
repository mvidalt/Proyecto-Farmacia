

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
    	
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Datos de conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/farmacia";
        String username = "root";
        String password = "";
        
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		System.out.println("Usuario: "+email);
		System.out.println("Pass: "+pass);
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		ConnectionDB db = new ConnectionDB();
		db.connectar();
		db.close();
		// Establecer la conexión
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // Crear una declaración
            Statement statement = connection.createStatement();
            
            // Ejecutar una consulta
            String query = "SELECT * FROM doctor WHERE mail='"+email+"' AND pass='"+pass+"'";
            ResultSet resultSet = statement.executeQuery(query);
            
            // Recorrer los resultados y mostrarlos en la consola
            while (resultSet.next()) {
                String mail = resultSet.getString("mail");
                String name = resultSet.getString("name");
                System.out.println("ID: " + mail + ", Nombre: " + name);
            }
            
            // Cerrar el resultado y la declaración
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
		
		
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		response.setHeader("Access-Control-Allow-Origin", "*");
	}
	
	

}
