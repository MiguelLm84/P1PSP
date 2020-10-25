import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FilesProperties {

	public FilesProperties() {
		
	}

	//Método para leer ficheros.
	public BufferedReader getBufferedReader(File fileName) {
		
		File fn=fileName;
		BufferedReader br=null;
		
		try {
			FileReader leer=new FileReader(fn);
			br=new BufferedReader(leer);
			
		} catch (FileNotFoundException e) {
			System.out.println("Error, el archivo con la ruta especificada no existe.");
			e.getMessage();
			
		} 
		return br;		
	}
	
	//Método para escribir en ficheros.
	public PrintWriter getPrintWriter(File fileName) {
		
		File fn=fileName;
		PrintWriter pW=null;
		
		try {
			FileWriter fW=new FileWriter(fn,true);
			pW=new PrintWriter(fW);
			
		} catch (IOException e) {
			System.out.println("Error de entrada/salida.");
			e.getMessage();
		}
		
		return pW;
	}
}
