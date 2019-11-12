/**
 * 
 */
package controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.EmpleadoDAO;
import laboral.DatosNoCorrectosException;
import laboral.Empleado;

/**
 * @author estudiante
 *
 */
@Controller
public class EmpleadoController {
	
	@RequestMapping(value = "/Mostrar Lista", method = RequestMethod.POST)
	public String goHome(Model model) throws ClassNotFoundException, SQLException, DatosNoCorrectosException {
		
		EmpleadoDAO empDAO = new EmpleadoDAO();
		
		List<Empleado> empleado = empDAO.mostrarEmpleados();
		
		model.addAttribute("listaEmp", empleado);
		
		return "mostrarEmpleados";
		
	}
	
}
