package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BBDD;
import model.Pelicula;

/**
 * Servlet implementation class CineController
 */
public class CineController extends HttpServlet {
	BBDD bd = new BBDD();
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CineController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String action = request.getParameter("action");

		if (action != null) {

			if (action.equalsIgnoreCase("Consultar")) {

				this.mostrarPeliculasDirector(request, response);

			}

		}

	}

	public void mostrarPeliculasDirector(HttpServletRequest request, HttpServletResponse response) {

		List<Pelicula> listaPelis = new ArrayList<Pelicula>();
		String director;
		String message = null;
		RequestDispatcher rd;

		director = request.getParameter("director");

		try {

			listaPelis = bd.mostrarPeliculasDirector(director);
			
			if(listaPelis.isEmpty()) {
				rd = request.getRequestDispatcher("consultaDirector.jsp");
				message = "No existe el director que buscas.";
			}else {
				rd = request.getRequestDispatcher("listadoPeliculas.jsp");
			}
			
			request.setAttribute("message", message);
			request.setAttribute("listaPelis", listaPelis);
			request.setAttribute("director", director);

			

			rd.forward(request, response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public void loginDirectores(HttpServletRequest request, HttpServletResponse response) {
		
		String director;
		String message = null;
		RequestDispatcher rd;
		
		try {
			
			director = bd.loginDirector(request.getParameter("director"), request.getParameter("password"));
			
			if(director == null) {
				message = "No existe el director en la base de datos.";
				rd = request.getRequestDispatcher("login.jsp");
			}else {
				rd = request.getRequestDispatcher("login.jsp");
			}
			
			request.setAttribute("message", message);
			request.setAttribute("director", director);
			
			rd.forward(request, response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
