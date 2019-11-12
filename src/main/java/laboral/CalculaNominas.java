/**
 * 
 */
package laboral;

import java.util.Scanner;

/**
 * @author pedro
 *
 */
public class CalculaNominas {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Scanner sx = new Scanner(System.in);
		Ficheros f = new Ficheros();
		BBDD bd = new BBDD();
		
		try {
			
			Empleado emp = new Empleado("James Cosling","32000032G",'M',4,7);
			Empleado emp2 = new Empleado("Ada Lovelace","32000031R",'F');
			
			
			
			/**
			 * 1� Parte de Nomina
			 * 
			 */
			
//			System.out.println(escribir(emp));
//			System.out.println("-----------------------------------");
//			System.out.println(escribir(emp2));
//			
//			emp2.incrAnyo();
//			System.out.println("------------------------------------------------");
//			emp.setCategoria(9);
//	
//			System.out.println(escribir(emp));
//			System.out.println("-----------------------------------");
//			System.out.println(escribir(emp2));
//			
//			emp.setCategoria(-4);
			
			
			
			
			/**
			 * Modificar el c�digo para que el programa lea la informaci�n necesaria de los
			 * empleados desde un fichero de texto �empleados.txt� y escriba en un fichero
			 * binario �salarios.dat� el dni del empleado junto con su sueldo calculado. Debes:
			 * 
			 * 1.1. Definir el fichero de texto de entrada �empleados.txt� creando en el mismos
			 * los empleados de los apartados 4.1 y 4.2 con el formato m�s adecuado para
			 * que pueda ser le�do por el programa. Puedes incluir m�s empleados.
			 */
			
			String fichero = "empleados.txt";
			String fichero2 = "salarios.dat";
			f.escribirFicheroTexto(emp, fichero);
			f.escribirFicheroTexto(emp2, fichero);
			
			f.leerFicheroTexto(fichero);
			
			f.escribirFicheroBinario(emp, fichero2);
			f.escribirFicheroBinario(emp2, fichero2);			
			
			
			/**
			 * 1.2. Actualizar dicho fichero �empleados.txt� conforme a los cambios especificados
			 * en el apartado 4.5.
			 */
			
			String aCadena = emp.imprime();
			String aCadena2 = emp2.imprime();
			emp.setCategoria(9);
			emp2.incrAnyo();
			String nCadena = emp.imprime();
			String nCadena2 = emp2.imprime();
			
			f.modificarEmpleadoFichero(fichero, aCadena, nCadena);
			f.modificarEmpleadoFichero(fichero, aCadena2, nCadena2);
			
			System.out.println("-----------------------------------------------------------");
			
			f.leerFicheroTexto(fichero);
			
			
			/**
			 * 1.3. Definir el fichero binario de salida �sueldos.dat� con el formato m�s adecuado
			 * para almacenar el dni y el sueldo resultante para cada empleado.
			 */
			
			String fichero3 = "sueldos.dat";
			
			f.escribirFicheroBinarioDniSueldoActualizado(emp, fichero3);
			f.escribirFicheroBinarioDniSueldoActualizado(emp2, fichero3);
			
			f.leerFicheroBinario(fichero3);
			
			
			/**
			 * 2. Modificar el c�digo para que el programa lea la informaci�n necesaria de los
			 * empleados desde una base de datos con, al menos, una tabla Empleados y una
			 * tabla Nominas. Debes:
			 * 
			 * 2.1. Definir e implementar el modelo de tablas y relaciones necesarios para dar
			 * soporte a la aplicaci�n de control de n�minas. Definir en la tabla correspondiente
			 * de dicha base de datos los mismos los empleados de los apartados 4.1 y 4.2 de
			 * la parte 1 para que pueda ser le�do por el programa. Puedes incluir m�s registros
			 * de empleados. 
			 * 
			 * Las tablas las creamos dentro de la base de datos antes de hacer nada.
			 * 
			 */
			
			bd.altaEmpleado(emp);
			bd.altaEmpleado(emp2);
			
			bd.leerTablaEmpleados();
			
			System.out.println("------------------------------------------------");
			
			bd.leerTablaNominas();
			
			System.out.println();
			System.out.println("------------------------------------------------");
			System.out.println();
			
			
			
			/**
			 * 2.2. Actualizar dicha base de datos conforme a los cambios especificados en el
			 * apartado 4.5 de la parte 1.
			 * 
			 * 2.3. Actualizar la base de datos almacenando el sueldo resultante para cada
			 * empleado.
			 * 
			 */
			
			bd.modificarCategoriaEmpleado(emp.dni, 9);
			bd.modificarAnyosEmpleadoDNI(emp2.incrAnyo(), emp2.dni);
			
			bd.leerTablaEmpleados();
			System.out.println("------------------------------------------------");
			bd.leerTablaNominas();
			
			/**
			 * 3. Modificar el c�digo para crear un m�todo �altaEmpleado� que permita dar de alta
			 * empleados en el sistema y que, de forma autom�tica, calcule y almacene el sueldo
			 * de los empleados en la base de datos.
			 */
			
			
			Empleado emp3 = new Empleado("Pedraso Er Pedrake","12345678K",'M',5,5);
			Empleado emp4 = new Empleado("Saphira","12684597H",'M',4,3);
			
			bd.altaEmpleado(emp3);
			bd.altaEmpleado(emp4);
			
			bd.leerTablaEmpleados();
			System.out.println("------------------------------------------------");
			bd.leerTablaNominas();
			
			/**
			 * 3.1. Sobrecargar el m�todo �altaEmpleado� para que permita el alta de empleados
			 * de forma individual o por lotes a partir de un fichero �empleadosNuevos.txt�
			 * con los datos de los empleados a dar de alta en el sistema.
			 */
			
			String ficheroNuevo = "empleadosNuevos.txt";
			Empleado emp5 = new Empleado("Daniel","64738273K",'M',2,3);
			Empleado emp6 = new Empleado("Asdef","25486757S",'M',4,1);
			Empleado emp7 = new Empleado("Marta","56484584D",'F',3,8);
			
			//Escribimos datos en el fichero para posteriormente poder introducir empleados
			//en la base de datos por lotes.
			
			f.escribirFicheroTexto(emp5, ficheroNuevo);
			f.escribirFicheroTexto(emp6, ficheroNuevo);
			f.escribirFicheroTexto(emp7, ficheroNuevo);
			
			bd.altaEmpleado(ficheroNuevo);
			
			
			
			
			
			/**
			 * MENU DE OPCIONES PUNTO 5
			 */
			
			
			System.out.println("MENU DE OPCIONES");
			
			int opcion;
			int opcion2;
			String nombre;
			String dni;
			String sexo;
			int categoria;
			int anyos;
			
			do {
				System.out.println();
				System.out.println("1. Mostrar toda la informacion de todos los empleados.\n" +
									"2. Mostrar el salario de un empleado por su dni.\n" +
									"3. Modificar datos de empleados.\n" +
									"4. Recalcular y actualizar el sueldo de un empleado.\n" +
									"5. Recalcular y actualizar los sueldos de todos los empleados.\n" +
									"6. Realizar copia de seguridad.\n" +
						 			"0. Salir del programa.");
				
				opcion = sc.nextInt();
				
				if(opcion == 1) {
					
					System.out.println("Empleados");
					bd.leerTablaEmpleados();;
			
				}else if(opcion == 2) {
					
					System.out.println("Introduzca el dni del empleado que desea ver el salario.");
					dni = sx.nextLine();
					bd.mostrarSalarioDni(dni);
					
				}else if(opcion == 3) {
					
					System.out.println("�Que desea modificar?");
					
					do {
						
						System.out.println("1.- Modificar nombre.\n"
								 + "2.- Modificar sexo.\n"
								 + "3.- Modificar categoria.\n"
								 + "4.- Modificar anyos.\n"
								 + "0.- Salir.");
						
						opcion2 = sc.nextInt();
						
						if(opcion2 == 1) {
							
							System.out.println("DNI del empleado a modificar: ");
							dni = sx.nextLine();
							System.out.println("A continuacion el NUEVO nombre del empleado");
							nombre = sx.nextLine();
							bd.modificarNombreEmpleado(nombre, dni);
							
						}else if(opcion2 == 2) {
							
							System.out.println("DNI del empleado a modificar: ");
							dni = sx.nextLine();
							System.out.println("A continuacion el NUEVO sexo del empleado. Recuerde ('M' o 'F')");
							sexo = sx.nextLine();
							bd.modificarNombreEmpleado(sexo, dni);
							
						}else if(opcion2 == 3) {
							
							System.out.println("DNI del empleado a modificar: ");
							dni = sx.nextLine();
							System.out.println("A continuacion la NUEVA categoria del empleado");
							categoria = sc.nextInt();
							bd.modificarCategoriaEmpleado(dni, categoria);
							
						}else if(opcion2 == 4) {
							
							System.out.println("DNI del empleado a modificar: ");
							dni = sx.nextLine();
							System.out.println("A continuacion los a�os que lleva en la empresa.");
							anyos = sc.nextInt();
							bd.modificarAnyosEmpleadoDNI(anyos, dni);
							
						}
						
						if(opcion2 == 0) {
							
							System.out.println("Saliendo del submenu...");
							
						}
						
					}while(opcion2 != 0);
						
				}else if(opcion == 4) {
					
					System.out.println("DNI del empleado para recalcular su sueldo.");
					dni = sx.nextLine();
					bd.recalcularSueldoDni(dni);
					
				}else if(opcion == 5) {
					
					System.out.println("Recalculando sueldo de todos los empleados...");
					bd.recalcularSueldoTodosEmpleados();
					
				}else if(opcion == 6) {
					
					String ficheroEmpleados;
					String ficheroNominas;
					
					System.out.println("Escriba el nombre del fichero (ejemplo.txt) donde guardaremos los empleados.");
					ficheroEmpleados = sx.nextLine();
					System.out.println();
					System.out.println("Ahora el nombre del fichero (ejemplo.txt) donde guardaremos los sueldos.");
					ficheroNominas = sx.nextLine();
					System.out.println();
					System.out.println("Realizando copia de seguridad...");
					bd.backupFichero(ficheroEmpleados, ficheroNominas);
				}
				
			}while(opcion != 0);
				System.out.println("Saliendo del menu...");
			
			sc.close();
			sx.close();
				
		} catch (DatosNoCorrectosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
//	private static String escribir(Empleado e) {
//		
//		Nomina n = new Nomina();
//		
//		return e.imprime() + " " + n.sueldo(e);
//		
//	}
		
}
