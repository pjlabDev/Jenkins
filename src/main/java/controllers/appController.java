package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmpleadoDAO;
import laboral.BBDD;
import laboral.DatosNoCorrectosException;
import laboral.Empleado;

/**
 * Servlet implementation class appController
 */
public class appController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public appController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = request.getParameter("action");
		
		if(action != null) {
			
			if(action.equalsIgnoreCase("Mostrar Empleados")) {
				
				this.mostrarEmpleados(request, response);
				
			}else if(action.equalsIgnoreCase("Mostrar Salario")) {
				
				this.mostrarSalarioPorEmpleado(request, response);
				
			}else if(action.equalsIgnoreCase("SacarEmpaModificar")) {
				
				this.seleccionarEmpxDNI(request, response);
				
			}else if(action.equalsIgnoreCase("Modificar")) {
				
				this.modificarEmpleado(request, response);
				
			}
			
		}else {
			System.out.println("No vale 2");
		}
			
	}
	
	
	protected void mostrarEmpleados(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EmpleadoDAO empDAO = new EmpleadoDAO();
		RequestDispatcher rd;
		
		try {
			List<Empleado> emp = empDAO.mostrarEmpleados();
			
			rd = request.getRequestDispatcher("mostrarEmpleados.jsp");
			
			request.setAttribute("listaEmp", emp);
			
			rd.forward(request, response);
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatosNoCorrectosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
//		BBDD bd = new BBDD();
//		RequestDispatcher rd;
//		
//		try {
//			
//			List<Empleado> emp = bd.mostrarTablaEmpleados();
//			
//			rd = request.getRequestDispatcher("mostrarEmpleados.jsp");
//			
//			request.setAttribute("listaEmp", emp);
//			
//			rd.forward(request, response);
//			
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (DatosNoCorrectosException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	
	
	protected void mostrarSalarioPorEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		EmpleadoDAO empDAO = new EmpleadoDAO();
		RequestDispatcher rd;
		String salario;
		Empleado emp;
		
		try {
		
			emp = empDAO.mostrarEmpleadosDni(request.getParameter("dni"));
			
			request.setAttribute("dniEmp", emp.getDni());
			
			salario = empDAO.mostrarSalarioDni(request.getParameter("dni"));
			
			rd = request.getRequestDispatcher("mostrarSalarioEmpleado.jsp");
			
			request.setAttribute("salarioEmp", salario);
			
			rd.forward(request, response);
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatosNoCorrectosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
//		BBDD bd = new BBDD();
//		RequestDispatcher rd;
//		Empleado emp;
//		String salario = null;
//		
//		try {
//			
//			emp = bd.mostrarEmpleadoDni(request.getParameter("dni"));
//			
//			request.setAttribute("dniEmp", emp.getDni());
//			
//			salario = bd.mostrarSalarioPorEmpleado(request.getParameter("dni"));
//			
//			rd = request.getRequestDispatcher("mostrarSalarioEmpleado.jsp");
//			
//			request.setAttribute("salarioEmp", salario);
//			
//			rd.forward(request, response);
//			
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (DatosNoCorrectosException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	
	
	protected void seleccionarEmpxDNI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		EmpleadoDAO empDAO = new EmpleadoDAO();
		Empleado emp;
		RequestDispatcher rd;
		
		String dni = request.getParameter("dni");
		
		request.setAttribute("dni", dni);
		
		try {
			
			emp = empDAO.mostrarEmpleadosDni(dni);
			
			request.setAttribute("empleado", emp);
			
			rd = request.getRequestDispatcher("modificarEmpleado.jsp");
			
			rd.forward(request, response);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatosNoCorrectosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		BBDD bd = new BBDD();
//		
//		Empleado emp = null;
//		
//		RequestDispatcher rd;
//		
//		String dni = request.getParameter("dni");
//		
//		request.setAttribute("dni", dni);
//		
//		try {
//			
//			emp = bd.mostrarEmpleadoDni(dni);
//			
//			request.setAttribute("empleado", emp);
//		
//			
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (DatosNoCorrectosException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		rd = request.getRequestDispatcher("modificarEmpleado.jsp");
//		
//		rd.forward(request, response);
		
	}
	
	
	protected void modificarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		EmpleadoDAO empDAO = new EmpleadoDAO();
		
		RequestDispatcher rd;
		
		String dni = request.getParameter("dni");
		
		String nombre = request.getParameter("nombre");
		
		String sexo = request.getParameter("sexo");
		
		String categoria = request.getParameter("categoria");
		
		String anyos = request.getParameter("anyos");
		
		try {
			
			empDAO.modificarEmpleado(dni, nombre, sexo, categoria, anyos);		
			
			rd = request.getRequestDispatcher("empleadoModificado.jsp");
			
			rd.forward(request, response);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatosNoCorrectosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		BBDD bd = new BBDD();
//		
//		RequestDispatcher rd;
//		
//		String dni = request.getParameter("dni");
//		
//		String nombre = request.getParameter("nombre");
//		
//		String sexo = request.getParameter("sexo");
//		
//		String categoria = request.getParameter("categoria");
//		
//		String anyos = request.getParameter("anyos");
//		
//		try {
//			
//			bd.modificarEmpelado(dni, nombre, sexo, categoria, anyos);
//			
//			rd = request.getRequestDispatcher("empleadoModificado.jsp");
//			
//			rd.forward(request, response);
//			
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (DatosNoCorrectosException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
	}
	
}
