/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Pelicula;

/**
 * @author usuario
 *
 */
public class BBDD {
	
	Connection con;
	PreparedStatement pst;
	Statement st;
	ResultSet rs;
	int result;
	String forName = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cine";
	String user = "root";
	String pass = "";
	
	public BBDD() {
		
		try {
			
			Class.forName(forName);
			con = DriverManager.getConnection(url,user,pass);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public List<Pelicula> mostrarPeliculasDirector(String director) throws SQLException{
		
		List<Pelicula> listaPelis = new ArrayList<Pelicula>();
		
		st = con.createStatement();
		
		rs = st.executeQuery("SELECT * FROM pelicula WHERE director = '" + director + "'");
		
		while(rs.next()) {
			
			listaPelis.add(new Pelicula(rs.getString(1),rs.getString(2),rs.getString(3)));
			
		}
		
		return listaPelis;
		
	}
	
	
	public String loginDirector(String nombre, String password) throws SQLException {
		
		String linea = null;
		
		st = con.createStatement();
		
		rs = st.executeQuery("SELECT * FROM directores WHERE nombre = '" + nombre + "' AND password = '" + password + "'");
		
		while(rs.next()) {
			
			linea = rs.getString(1);
			
		}
		
		return linea;
		
	}
	
	
		
}
