/**
 * 
 */
package interfaces;

import java.sql.SQLException;
import java.util.List;

import laboral.DatosNoCorrectosException;
import laboral.Empleado;

/**
 * @author estudiante
 *
 */
public interface IServiceDAO {
	
	public List<Empleado> mostrarEmpleados()throws ClassNotFoundException, SQLException, DatosNoCorrectosException;
	
	public String mostrarSalarioDni(String dni)throws ClassNotFoundException, SQLException;
	
	public Empleado mostrarEmpleadosDni(String dni)throws ClassNotFoundException, SQLException, DatosNoCorrectosException;
	
	public void modificarEmpleado(String dni, String nombre, String sexo, String categoria, String anyos)throws ClassNotFoundException, SQLException, DatosNoCorrectosException;

}
