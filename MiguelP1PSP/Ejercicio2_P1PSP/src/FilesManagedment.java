
import java.io.*;
import java.util.*;

public class FilesManagedment {
	
	public FilesManagedment() {
		
	}
	
	public static boolean counter(String fileName,String vocal,String fileResultName) {
		
		int contador=0;
		boolean volcado=false;
		File ficheroArgs=new File(fileName);
		File fileResVocal=new File(fileResultName);
		String v=vocal;
		char vocalMin=v.charAt(0);
		char vocalMay=v.toUpperCase().charAt(0);
		FilesProperties fP=new FilesProperties();		
		
		//Comprobación si el fichero que se pasa por argumentos (args[0]) existe.
		if(!ficheroArgs.exists()) {
			System.out.println("El fichero de la posición args[0] no existe o no se encuentra.");
			System.out.println(ficheroArgs);
			
		} else {			
			//Se crea el fichero de resultados de cada vocal.
			if(!fileResVocal.exists()) {
				try {
					fileResVocal.createNewFile();
					System.out.println("El fichero de la vocal se ha creado correctamente.");
					
				} catch (IOException k) {
					System.out.println("Error, el fichero '"+fileResVocal+"' no se ha podido crear.");
					k.getMessage();
				}				
			} else {
				System.out.println("El fichero no existe y no se ha creado.");
			}
			
			//Se lee en bucle las líneas del fichero inicial (args[0]).
			try {
				ArrayList<String> lineasFichero=new ArrayList<String>();	
				BufferedReader br=fP.getBufferedReader(ficheroArgs);
				String lecturaLineas;
				while(br!=null) {
					lecturaLineas=br.readLine();
					if(lecturaLineas==null) {
						break;
					}else {
						lineasFichero.add(lecturaLineas);
					}					
				}
				br.close();
				
				//Se lee la lista de líneas y se va aumentando el contador si procede,
				//además se guarda el resultado en el fichero de conteo de cada vocal.
				for(String read : lineasFichero) {
					char[] lineas=read.toCharArray();
					if(lineas==null) {
						break;
					}
					for(int i=0;i<lineas.length;i++) {
						
						char charVowel=lineas[i];
						if(charVowel==vocalMin || charVowel==vocalMay) {
							contador++;
						}
					}					
				}
				
				//Se escribe en el fichero de resultados de las vocales el contador con el conteo de cada una.
				String conteo=String.valueOf(contador);
				PrintWriter pw=fP.getPrintWriter(fileResVocal);
				pw.write(conteo);
				
				//Si el volcado ha sido correcto y hay datos en el fichero, el volcado será igual a true.
				if(fileResVocal!=null) {
					volcado=true;
				} 
				pw.close();
				
				BufferedReader br2=fP.getBufferedReader(fileResVocal);
				String cont=br2.readLine();
				System.out.println(cont);					
				br2.close();
				
			} catch (IOException e1) {
				System.out.println("Error,el fichero "+ficheroArgs+" no se ha podido leer.");
				e1.getMessage();
			}
		}
		
		try {
			fP.getBufferedReader(ficheroArgs).close();
			
		} catch (IOException e) {
			System.out.println("Error al cerrar el flujo en BufferedReader de la clase Main.java");
			e.getMessage();
			
		} catch(NullPointerException f) {
			System.out.println("Error, el fichero '"+ficheroArgs+"' está vacío y no se puede acceder a él.");
			f.getMessage();	
			
		}
		try {
			fP.getPrintWriter(fileResVocal).close();
			
		}catch(NullPointerException g) {
			System.out.println("Error, el fichero '"+fileResVocal+"' está vacío y no se puede acceder a él.");
			g.getMessage();	
			
		}

		return volcado;
	}
	
	public static void main(String[] args) {
		
		String fichero=null;
		String vocalArgs=null;
		String directorio=null;
		
	try {
		fichero=args[0];
		vocalArgs=args[1];
		directorio=args[2];
		System.out.println(fichero);
		System.out.println(vocalArgs);
		System.out.println(directorio);
		
	}catch (ArrayIndexOutOfBoundsException h) {
		System.out.println("Error, no se encuentra ningún argumento en args[] del Main de FilesManagedment.");
		System.out.println(fichero);
		System.out.println(vocalArgs);
		System.out.println(directorio);
		h.getMessage();
	}
	
	//Se llama al método 'counter' para que haga todo el proceso de conteo de vocales.
	counter(fichero,vocalArgs,directorio);
	}
}