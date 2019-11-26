/**
 * 
 */
package model;

/**
 * @author usuario
 *
 */
public class Pelicula {

	private String director;
	private String titulo;
	private String fecha;

	public Pelicula(String director, String titulo, String fecha) {

		this.director = director;
		this.titulo = titulo;
		this.fecha = fecha;

	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}
