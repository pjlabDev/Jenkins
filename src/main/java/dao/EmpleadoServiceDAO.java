package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import laboral.DatosNoCorrectosException;
import laboral.Empleado;
import laboral.Nomina;

public class EmpleadoServiceDAO {
	

	Nomina n = new Nomina();
	Connection con;
	PreparedStatement pst;
	Statement st;
	ResultSet rs;
	int result;
	String forName = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@192.168.14.203:1521:xe";
	String user = "Empresa";
	String pass = "empresa";
	
	
	public void leerTablaEmpleados() {
		
		String linea;
		
		try {
			
			Class.forName(forName);
			
			con = DriverManager.getConnection(url,user,pass);
			
			
			st = con.createStatement();
			
			rs = st.executeQuery("select * from empleados");
			
			while(rs.next()) {
				
				linea = rs.getString(1) + "-" + rs.getString(2) + "-" + rs.getString(3) + "-" + rs.getInt(4) + "-" + rs.getInt(5);
				
				System.out.println(linea);

			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (SQLException sqle) {
			
			sqle.printStackTrace();
			System.out.println();
			System.out.println(sqle.getMessage());
			System.out.println();
			System.out.println(sqle.getSQLState());
			System.out.println();
			System.out.println(sqle.getErrorCode());
			
		}
		
	}
	
	public List<Empleado> mostrarTablaEmpleados() throws ClassNotFoundException, SQLException, DatosNoCorrectosException {
		
		ArrayList<Empleado> lista = new ArrayList<Empleado>();
			
			Class.forName(forName);
			
			con = DriverManager.getConnection(url,user,pass);
			
			st = con.createStatement();
			
			rs = st.executeQuery("select * from empleados");
			
			while(rs.next()) {
				
				lista.add(new Empleado(rs.getString("nombre"),rs.getString("dni"),rs.getString("sexo").charAt(0),rs.getInt("categoria"),rs.getInt("anyos")));

			}
			
			return lista;
		
	}	
	
	
	public void modificarCategoriaEmpleado(String dni, int categoria) {
		
		String linea;
		String arrayEmpleados[];
		String nombre;
		String sexo;
		int anyos;
		
		try {
			
			Class.forName(forName);
			
			con = DriverManager.getConnection(url,user,pass);
			
			pst = con.prepareStatement("SELECT * FROM EMPLEADOS WHERE DNI = ?");
			
			pst.setString(1, dni);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				
				linea = rs.getString(1) + "-" + rs.getString(2) + "-" + rs.getString(3) + "-" + rs.getInt(4) + "-" + rs.getInt(5);
				arrayEmpleados = linea.split("-");
				
				nombre = arrayEmpleados[0];
				dni = arrayEmpleados[1];
				sexo = arrayEmpleados[2];
				anyos = Integer.parseInt(arrayEmpleados[4]);
				
				Empleado emp = new Empleado(nombre,dni,sexo.charAt(0),categoria,anyos);
				
				pst = con.prepareStatement("UPDATE EMPLEADOS SET CATEGORIA = ? WHERE DNI = ?");
				
				pst.setInt(1, categoria);
				pst.setString(2,dni);
				
				pst.executeUpdate();
				
				pst.clearBatch();
				pst = con.prepareStatement("UPDATE NOMINAS SET SUELDO = ? WHERE DNI = ?");
				
				pst.setInt(1, n.sueldo(emp));
				pst.setString(2, emp.getDni());
				
				result = pst.executeUpdate();
				
				if(result>0) {
					System.out.println("Categoria del empleado actualizada.");
				}else {
					System.out.println("No se ha podido actualizar la categoria del empleado.");
				}
						
			}			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException sqle) {
			
			sqle.printStackTrace();
			System.out.println();
			System.out.println(sqle.getMessage());
			System.out.println();
			System.out.println(sqle.getSQLState());
			System.out.println();
			System.out.println(sqle.getErrorCode());
			
		} catch (DatosNoCorrectosException dao) {
			
			dao.printStackTrace();
			
		}
	
	}
	
	
	public void modificarAnyosEmpleadoDNI(int anyos, String dni) {
		
		String linea;
		String arrayEmpleados[];
		String nombre;
		String sexo;
		int categoria;
		
		try {
			
			Class.forName(forName);
			
			con = DriverManager.getConnection(url,user,pass);
			
			pst = con.prepareStatement("SELECT * FROM EMPLEADOS WHERE DNI = ?");
			
			pst.setString(1, dni);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				
				linea = rs.getString(1) + "-" + rs.getString(2) + "-" + rs.getString(3) + "-" + rs.getInt(4);
				arrayEmpleados = linea.split("-");
				
				nombre = arrayEmpleados[0];
				dni = arrayEmpleados[1];
				sexo = arrayEmpleados[2];
				categoria = Integer.parseInt(arrayEmpleados[3]);
				
				Empleado emp = new Empleado(nombre,dni,sexo.charAt(0),categoria,anyos);
				
				pst = con.prepareStatement("UPDATE EMPLEADOS SET ANYOS = ? WHERE DNI = ?");
				
				pst.setInt(1, anyos);
				pst.setString(2,dni);
				
				pst.executeUpdate();
				
				pst.clearBatch();
				pst = con.prepareStatement("UPDATE NOMINAS SET SUELDO = ? WHERE DNI = ?");
				
				pst.setInt(1, n.sueldo(emp));
				pst.setString(2, emp.getDni());
				
				result = pst.executeUpdate();
				
				if(result>0) {
					System.out.println("Anyos del empleado actualizados.");
				}else {
					System.out.println("No se han podido actualizar los anyos del empleado.");
				}
						
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException sqle) {
			
			sqle.printStackTrace();
			System.out.println();
			System.out.println(sqle.getMessage());
			System.out.println();
			System.out.println(sqle.getSQLState());
			System.out.println();
			System.out.println(sqle.getErrorCode());
			
		} catch (DatosNoCorrectosException dao) {
			dao.printStackTrace();
		}
		
	}

	
	public void leerTablaNominas() {
		
		String linea;
		
		try {
			
			Class.forName(forName);
			
			con = DriverManager.getConnection(url,user,pass);
			
			
			st = con.createStatement();
			
			rs = st.executeQuery("select * from nominas");
			
			while(rs.next()) {
				
				linea = rs.getString(1) + "-" + rs.getInt(2);
				
				System.out.println(linea);
				
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (SQLException sqle) {
			
			sqle.printStackTrace();
			System.out.println();
			System.out.println(sqle.getMessage());
			System.out.println();
			System.out.println(sqle.getSQLState());
			System.out.println();
			System.out.println(sqle.getErrorCode());
			
		}
		
	}
	
	public void altaEmpleado(Empleado emp) {
		
		try {
			
			Class.forName(forName);
			
			con = DriverManager.getConnection(url,user,pass);
			
			String sexo = Character.toString(emp.getSexo());
			
			pst = con.prepareStatement("insert into empleados values(?,?,?,?,?)");
			
			pst.setString(1, emp.getNombre());
			pst.setString(2, emp.getDni());
			pst.setString(3, sexo);
			pst.setInt(4, emp.getCategoria());
			pst.setInt(5, emp.getAnyos());
			
			pst.executeUpdate();
			
			
			pst = con.prepareStatement("insert into Nominas values(?,?)");
			
			pst.setString(1, emp.getDni());
			pst.setInt(2,n.sueldo(emp));
			
			result = pst.executeUpdate();
			
			if(result>0) {
				System.out.println("Empleado insertado junto con su sueldo en sus respectivas tablas.");
			}else {
				System.out.println("No se ha podido insertar el empleado.");
			}
	
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			
		} catch (SQLException sqle) {
			
			sqle.printStackTrace();
			System.out.println();
			System.out.println(sqle.getMessage());
			System.out.println();
			System.out.println(sqle.getSQLState());
			System.out.println();
			System.out.println(sqle.getErrorCode());
			
		}
		
		
	}
	
	
	public void altaEmpleado(String ficheroEmpleadoNuevo) throws DatosNoCorrectosException {
		
		Scanner sc = new Scanner(System.in);
		Scanner sx = new Scanner(System.in);
		BufferedReader br;
		String arrayEmpleado[];
		String linea;
		String nombre;
		String dni;
		String sexo;
		int categoria;
		int anyos;
		String salir;
		int opcion;
		
		
		do {
			
			System.out.println();
			System.out.println("1.- Para dar de alta a un empleado individualmente.\n" + 
								"2.- Para dar de alta a empleados por lote desde el fichero empleadosNuevos.txt\n" + 
								"0.- Para salir.");
			
			opcion = sc.nextInt();
			
			if(opcion == 1) {
				
				System.out.println("Ha entrado en la opcion de introducir empleados individualmente");
				
				do {
					System.out.println("Introduzca los datos de los empleados.");
					System.out.println("Nombre");
					nombre = sx.nextLine();
					System.out.println("DNI");
					dni = sx.nextLine();
					System.out.println("Sexo ('M' o 'F')");
					sexo = sx.nextLine();
					System.out.println("Categoria");
					categoria = sc.nextInt();
					System.out.println("Anyos");
					anyos = sc.nextInt();
					
					altaEmpleado(new Empleado(nombre,dni,sexo.charAt(0),categoria,anyos));
					
					System.out.println();
					
					System.out.println("�Desea seguir introduciendo empleados? \n" + 
										"Si para seguir, No para salir.");
					
					salir = sx.nextLine();
					
					if(salir.equalsIgnoreCase("No")) {
						System.out.println("Saliendo...");
					}
					
				}while(!salir.equalsIgnoreCase("No"));
				
			}else if(opcion == 2) {
	
					try {
						Class.forName(forName);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				System.out.println("Ha entrado en la opcion de introducir empleados por lotes desde \n" + 
									"el fichero empleadosNuevos.txt");

				try {
					con = DriverManager.getConnection(url,user,pass);
					
					br = new BufferedReader(new FileReader(ficheroEmpleadoNuevo));
					
					
					while((linea = br.readLine()) != null) {
						
						arrayEmpleado = linea.split("-");
						
						nombre = arrayEmpleado[0];
						dni = arrayEmpleado[1];
						sexo = arrayEmpleado[2];
						categoria = Integer.parseInt(arrayEmpleado[3]);
						anyos = Integer.parseInt(arrayEmpleado[4]);
						
						
						pst = con.prepareStatement("insert into empleados values(?,?,?,?,?)");
						
						pst.setString(1,nombre);
						pst.setString(2, dni);
						pst.setString(3, sexo);
						pst.setInt(4, categoria);
						pst.setInt(5, anyos);
						
						pst.executeUpdate();
						
						pst = con.prepareStatement("insert into nominas values(?,?)");
						
						Empleado emp = new Empleado(nombre,dni,sexo.charAt(0),categoria,anyos);
						
						pst.setString(1, emp.getDni());
						pst.setInt(2,n.sueldo(emp));
						
						result = pst.executeUpdate();
						
						if(result>0) {
							System.out.println("Empleados anadidos por lote desde el fichero correctamente.");
						}else {
							System.out.println("No se ha podido completar la transferencia de datos.");
						}
					
					}
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException io) {
					io.printStackTrace();
				} catch (SQLException sqle) {
					
					sqle.printStackTrace();
					System.out.println();
					System.out.println(sqle.getMessage());
					System.out.println();
					System.out.println(sqle.getSQLState());
					System.out.println();
					System.out.println(sqle.getErrorCode());
					
				}catch (DatosNoCorrectosException dan) {
					dan.getMessage();
					
				}
				
				
			}else if(opcion == 0) {
				System.out.println("Saliendo...");
			}
			
		}while(opcion != 0);
			
		sc.close();
		sx.close();
		
	}
	
	
	public void backupFichero(String fichero1, String fichero2) {
		
		BufferedWriter bw;
		
		try {
			
			Class.forName(forName);
			
			con = DriverManager.getConnection(url,user,pass);
			
			bw = new BufferedWriter(new FileWriter(fichero1,true));
			
			st = con.createStatement();
			
			rs = st.executeQuery("SELECT * FROM EMPLEADOS");
			
			while(rs.next()) {
				
				String linea = rs.getString(1) + "-" + rs.getString(2) + "-" + rs.getString(3) + "-" + rs.getInt(4) + "-" + rs.getInt(5);
				
				bw.write(linea);
				bw.newLine();
			}
			
			bw.close();
			
			bw = new BufferedWriter(new FileWriter(fichero2, true));
			
			st.clearBatch();
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM NOMINAS");
			
			while(rs.next()) {
				
				String linea2 = rs.getString(1) + "-" + rs.getInt(2);
				bw.write(linea2);
				bw.newLine();
				
			}
			bw.close();
			
			if(fichero1 != null && fichero2 != null) {
				System.out.println("Backup completado.");
			}else {
				System.out.println("No se ha podido realizar el backup.");
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException sqle) {
			
			sqle.printStackTrace();
			System.out.println();
			System.out.println(sqle.getMessage());
			System.out.println();
			System.out.println(sqle.getSQLState());
			System.out.println();
			System.out.println(sqle.getErrorCode());
		} catch (IOException io) {
			io.getMessage();
		}
		
	}
	
	public void mostrarSalarioDni(String dni) {
		
		try {
			
			Class.forName(forName);
			
			con = DriverManager.getConnection(url,user,pass);
			
			pst = con.prepareStatement("SELECT SUELDO FROM NOMINAS WHERE DNI = ?");
			
			pst.setString(1, dni);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				
				String linea = rs.getString(1);
				
				System.out.println("El salario es: " + linea);
				
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println();
			System.out.println(sqle.getMessage());
			System.out.println();
			System.out.println(sqle.getSQLState());
			System.out.println();
			System.out.println(sqle.getErrorCode());
		}
		
		
	}
	
	public String mostrarSalarioPorEmpleado(String dni) throws ClassNotFoundException, SQLException {
			
			String salario = null;
		
			Class.forName(forName);
			
			con = DriverManager.getConnection(url,user,pass);
			
			pst = con.prepareStatement("SELECT SUELDO FROM NOMINAS WHERE DNI = ?");
			
			pst.setString(1, dni);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				
				salario = rs.getString(1);
				
			}
				
			return salario;
		
	}
	
	public Empleado mostrarEmpleadoDni(String dni) throws ClassNotFoundException, SQLException, DatosNoCorrectosException {
		
		Empleado emp = null;
	
		Class.forName(forName);
		
		con = DriverManager.getConnection(url,user,pass);
		
		pst = con.prepareStatement("SELECT * FROM EMPLEADOS WHERE DNI = ?");
		
		pst.setString(1, dni);
		
		rs = pst.executeQuery();
		
		while(rs.next()) {
			
			emp = new Empleado(rs.getString("nombre"),rs.getString("dni"),rs.getString("sexo").charAt(0),rs.getInt("categoria"),rs.getInt("anyos"));
			
		}
		
			
		return emp;
	
	}
	
	public void modificarEmpelado(String dni, String nombre, String sexo, String categoria, String anyos) throws ClassNotFoundException, SQLException, DatosNoCorrectosException {
	
		Class.forName(forName);
		
		con = DriverManager.getConnection(url,user,pass);
		
		st = con.createStatement();
		
		st.executeUpdate("UPDATE EMPLEADOS SET NOMBRE = '" + nombre + "' WHERE DNI = '" + dni + "'");
		st.executeUpdate("UPDATE EMPLEADOS SET SEXO = '" + sexo + "' WHERE DNI = '" + dni + "'");
		st.executeUpdate("UPDATE EMPLEADOS SET CATEGORIA = '" + categoria + "' WHERE DNI = '" + dni + "'");
		st.executeUpdate("UPDATE EMPLEADOS SET ANYOS = '" + anyos + "' WHERE DNI = '" + dni + "'");
		st.executeUpdate("UPDATE NOMINAS SET SUELDO = " + Nomina.sueldoController(Integer.parseInt(categoria), Integer.parseInt(anyos)) + " WHERE DNI = '" + dni + "'");	
		st.close();
		con.close();
	}
	
	public void modificarNombreEmpleado(String nombre, String dni) {
		
		try {
			
			Class.forName(forName);
			
			con = DriverManager.getConnection(url,user,pass);
			
			pst = con.prepareStatement("UPDATE EMPLEADOS SET NOMBRE = ? WHERE DNI = ?");
			
			pst.setString(1, nombre);
			pst.setString(2, dni);
			
			result = pst.executeUpdate();
			
			if(result>0) {
				System.out.println("Nombre actualizado.");
			}else {
				System.out.println("No se ha podido modificar el nombre.");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println();
			System.out.println(sqle.getMessage());
			System.out.println();
			System.out.println(sqle.getSQLState());
			System.out.println();
			System.out.println(sqle.getErrorCode());
		}
		
	}
	
	
	public void modificarSexoEmpleado(String sexo, String dni) {
		
		try {
			
			Class.forName(forName);
			
			con = DriverManager.getConnection(url,user,pass);
			
			pst = con.prepareStatement("UPDATE EMPLEADOS SET SEXO = ? WHERE DNI = ?");
			
			pst.setString(1, sexo);
			pst.setString(2, dni);
			
			result = pst.executeUpdate();
			
			if(result>0) {
				System.out.println("Sexo actualizado.");
			}else {
				System.out.println("No se ha podido modificar el sexo.");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println();
			System.out.println(sqle.getMessage());
			System.out.println();
			System.out.println(sqle.getSQLState());
			System.out.println();
			System.out.println(sqle.getErrorCode());
		}
		
	}
	
	public void recalcularSueldoDni(String dni) {
		
		String linea;
		String arrayEmpleados[];
		String nombre;
		String sexo;
		int categoria;
		int anyos;
		
		try {
			
			Class.forName(forName);
			
			con = DriverManager.getConnection(url,user,pass);
			
			pst = con.prepareStatement("SELECT * FROM EMPLEADOS WHERE DNI = ?");
			
			pst.setString(1, dni);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				
				linea = rs.getString(1) + "-" + rs.getString(2) + "-" + rs.getString(3) + "-" + rs.getInt(4) + "-" +rs.getInt(5);
				arrayEmpleados = linea.split("-");
				
				nombre = arrayEmpleados[0];
				dni = arrayEmpleados[1];
				sexo = arrayEmpleados[2];
				categoria = Integer.parseInt(arrayEmpleados[3]);
				anyos = Integer.parseInt(arrayEmpleados[4]);
				
				Empleado emp = new Empleado(nombre,dni,sexo.charAt(0),categoria,anyos);
				
				pst = con.prepareStatement("UPDATE NOMINAS SET SUELDO = ? WHERE DNI = ?");
				
				pst.setInt(1, n.sueldo(emp));
				pst.setString(2, emp.getDni());
				
				result = pst.executeUpdate();
				
				if(result>0) {
					System.out.println("Sueldo recalculado con exito.");
				}else {
					System.out.println("No se ha podido recalcular el sueldo.");
				}
						
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException sqle) {
			
			sqle.printStackTrace();
			System.out.println();
			System.out.println(sqle.getMessage());
			System.out.println();
			System.out.println(sqle.getSQLState());
			System.out.println();
			System.out.println(sqle.getErrorCode());
			
		} catch (DatosNoCorrectosException dao) {
			dao.printStackTrace();
		}
		
	}
	
	public void recalcularSueldoTodosEmpleados() {
		
		Empleado emp;
		
		try {
			
			Class.forName(forName);
			
			con = DriverManager.getConnection(url,user,pass);
			
			st = con.createStatement();
			
			rs = st.executeQuery("SELECT * FROM EMPLEADOS");
			
			while(rs.next()) {
				
				emp = new Empleado(rs.getString(1), rs.getString(2), rs.getString(3).charAt(0), rs.getInt(4), rs.getInt(5));
				
				pst = con.prepareStatement("UPDATE NOMINAS SET SUELDO = ? WHERE DNI = ?");
				
				pst.setInt(1, n.sueldo(emp));
				pst.setString(2, emp.getDni());
				
				result = pst.executeUpdate();
						
			}
			
			if(result>0) {
				System.out.println("Sueldo recalculado con exito.");
			}else {
				System.out.println("No se ha podido recalcular el sueldo.");
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException sqle) {
			
			sqle.printStackTrace();
			System.out.println();
			System.out.println(sqle.getMessage());
			System.out.println();
			System.out.println(sqle.getSQLState());
			System.out.println();
			System.out.println(sqle.getErrorCode());
			
		} catch (DatosNoCorrectosException dao) {
			dao.printStackTrace();
		}
		
	}
	
}
