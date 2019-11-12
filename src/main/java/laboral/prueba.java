/**
 * 
 */
package laboral;

import java.sql.SQLException;

/**
 * @author pedro
 *
 */
public class prueba {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BBDD bd = new BBDD();
		
		try {
			
			Empleado emp = new Empleado("James Cosling","32000032G",'M',4,7);
			Empleado emp2 = new Empleado("Ada Lovelace","32000031R",'F');
			
			
			bd.altaEmpleado(emp);
			bd.altaEmpleado(emp2);
			
			
		} catch (DatosNoCorrectosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
