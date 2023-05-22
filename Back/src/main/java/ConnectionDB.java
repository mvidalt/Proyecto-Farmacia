import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

public class ConnectionDB {
	//Atributs
	private Connection conn;
	private Statement st;
	
	//Constructors
	public ConnectionDB() {
		this.setConn(null);
		this.setSt(null);
	}
	
	//Mètodes
	public boolean connectar() {
		boolean proces = true;
		try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException error) {
	        System.out.println("Error al cargar el driver JDBC de MySQL: " + error.getMessage());
	        proces = false;
	    }
		
		try {
			this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmacia","root","");
	    } catch (SQLException error) {
	        System.out.println("Error al conectar con el servidor MySQL/MariaDB: " + error.getMessage());
	        proces = false;
	    }
		
	    try {
	        this.st = this.conn.createStatement();
	    } catch (SQLException error) {
	        System.out.println("Error al establecer declaración de conexión MySQL/MariaDB: " + error.getMessage());
	        proces = false;
	    }
	    
	    return proces;
	}
	
	
	public void close() {
		try {
			st.close();
			conn.close();
		} catch (SQLException error) {
			 System.out.println("Error al ejecutar tancar la connexió: " + error.getMessage());
		}
		
	}
	
	//Getters i Setters
	public void setConn(Connection conn) {this.conn=conn;}
	public void setSt(Statement st) {this.st=st;}

}
	
