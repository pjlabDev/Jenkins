/**
 * 
 */
package laboral;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author pedro
 *
 */
public class Ficheros {

	Nomina n = new Nomina();
	File fichero = new File("empleados.txt");
	BufferedWriter bw;
	BufferedReader br;
	FileInputStream fi;
	FileOutputStream fo;
	DataInputStream dis;
	DataOutputStream dos;
	
	
	public void escribirFicheroTexto(Empleado emp, String fichero) {
		
		try {
			
			bw = new BufferedWriter(new FileWriter(fichero,true));
			bw.write(emp.imprime());
			bw.newLine();
			bw.close();
			
			if(fichero != null) {
				System.out.println("Empleado anadido al fichero.");
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void leerFicheroTexto(String fichero)  {
		
		try {
			
			br = new BufferedReader(new FileReader(fichero));
			
			String emp;
			
			while((emp = br.readLine()) != null) {
				
				System.out.println(emp);
				
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		
		}catch (IOException io){
			
			io.printStackTrace();
			
		}
		
	}
	
	
	
	public void escribirFicheroBinario(Empleado emp, String fichero) {
		
		try {
			
			fo = new FileOutputStream(fichero,true);
			dos = new DataOutputStream(fo);
			
			String sueldo = Integer.toString(n.sueldo(emp));
			
			dos.writeUTF(emp.dni + "-" + sueldo + "\n");
				
			fo.close();
			dos.close();
			
			if(fichero != null) {
				System.out.println("Empleados introducidos en binario.");
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		
		}catch (IOException io) {
			// TODO Auto-generated catch block
			
			io.printStackTrace();
		}
		
		
	}
	
	
	public void modificarEmpleadoFichero(String fichero, String aCadena, String nCadena) {
		
		
		try {
			
			br = new BufferedReader(new FileReader(fichero));
			String linea;
			while((linea = br.readLine()) != null) {
				
				if(linea.equalsIgnoreCase(aCadena)) {
					
					escribirFichero(fichero, nCadena);
					
				}
		
			}
			
			br.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void escribirFichero(String fichero, String cadena) {
		
		try {
			
			bw = new BufferedWriter(new FileWriter(fichero,true));
			bw.write(cadena);
			bw.newLine();
			bw.close();
			
			if(fichero != null) {
				System.out.println("Empleado actualizado.");
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void vaciarFichero() {
		
		try {
			
			bw = new BufferedWriter(new FileWriter(fichero));
			bw.write("");
			bw.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public void escribirFicheroBinarioDniSueldoActualizado(Empleado emp,String fichero) {
		
		try {
			
			fo = new FileOutputStream(fichero,true);
			dos = new DataOutputStream(fo);
			
			String sueldo = Integer.toString(n.sueldo(emp));
			
			dos.writeUTF(emp.dni + "-" + sueldo + "\n");
			
			fo.close();
			dos.close();
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void leerFicheroBinario(String fichero) {
		
		fi = null;
		dis = null;
		
		try {
			
			fi= new FileInputStream(fichero);
			dis = new DataInputStream(fi);
			
			while(true) {
				
				System.out.println(dis.readUTF());
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (EOFException eo) {
			System.out.println("Fin del fichero binario.");
		}catch (IOException io) {
			io.printStackTrace();
		}
		
	}
	
	
	
}
