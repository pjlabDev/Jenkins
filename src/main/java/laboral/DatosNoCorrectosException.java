/**
 * 
 */
package laboral;

/**
 * @author pedro
 *
 */
public class DatosNoCorrectosException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public DatosNoCorrectosException(String string) {
		
		super(string);
		
	}

	public DatosNoCorrectosException() {
		
		super("Datos no introducidos correctamente");
		
	}
	
}
