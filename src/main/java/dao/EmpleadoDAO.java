/**
 * 
 */
package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaces.IServiceDAO;
import laboral.DatosNoCorrectosException;
import laboral.Empleado;

/**
 * @author estudiante
 *
 */
public class EmpleadoDAO implements IServiceDAO {
	
	EmpleadoServiceDAO esDAO = new EmpleadoServiceDAO();
	
	
	
	public List<Empleado> mostrarEmpleados() throws ClassNotFoundException, SQLException, DatosNoCorrectosException{
		
		List<Empleado> empleados = new ArrayList<Empleado>();
		
		empleados = esDAO.mostrarTablaEmpleados();
		
		return empleados;
		
	}
	
	public String mostrarSalarioDni(String dni) throws ClassNotFoundException, SQLException {
		
		String salario;
		
		salario = esDAO.mostrarSalarioPorEmpleado(dni);
		
		return salario;
		
	}
	
	public Empleado mostrarEmpleadosDni(String dni) throws ClassNotFoundException, SQLException, DatosNoCorrectosException {
		
		Empleado emp;
		
		emp = esDAO.mostrarEmpleadoDni(dni);
		
		return emp;
		
	}
	
	public void modificarEmpleado(String dni, String nombre, String sexo, String categoria, String anyos) throws ClassNotFoundException, SQLException, DatosNoCorrectosException {
		
		esDAO.modificarEmpelado(dni, nombre, sexo, categoria, anyos);
		
	}
	
	
	
}
