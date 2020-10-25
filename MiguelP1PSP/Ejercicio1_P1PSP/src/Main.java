import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

	public static void main(String[] args) {
		                  
        String vocal=null;
        String ficheroInicio=args[0];
        FilesProperties fP=new FilesProperties();
//        File ficheroVocales=null;
        File result=null;
        int sumaConteo=0;
        int numVocales=0;
        
        //Comprueba si los argumentos que se le pasan por consola son correctos el programa se Ejecutará,
        try {            
            if(args.length!=3) {
                System.out.println("Error de sintaxis: java Main <dir fichero> <dir FilesProperties> <dir FilesManagedment>");            
            } else {                
                //Bucle donde se recorre la lista de vocales.                
                String[] listaVocales= {"a","e","i","o","u"};        
                for(int i=0;i<listaVocales.length;i++) {
                    vocal=listaVocales[i];
                    
                    //Se crea fichero para cada vocal.
                    File ficheroVocales=new File(".\\ResultVocal_"+vocal.toUpperCase()+".txt");
                    if(!ficheroVocales.exists()) {
                        try {
                            ficheroVocales.createNewFile();
                        } catch (IOException e) {
                            System.out.println("Error, no se ha podido crear el fichero ResultVocal_"+vocal.toUpperCase()+".txt");
                            e.getMessage();
                        } catch (NullPointerException f) {
                            System.out.println("Error,el fichero 'ResultVocal_"+vocal.toUpperCase()+".txt' al que se intenta acceder es 'null'.");
                            f.getMessage();
                        }
                    }
                    //Se crea fichero de resultados.                   
                    result=new File(".\\Result_"+ficheroInicio);
                    if(!result.exists()) {
                    	result.createNewFile();       
                    } 
                    
                    //Se crean y se inician los ProcessBuilder.
                    File rutaFM=new File(args[2]);
                    String filesManagAbsoluta=rutaFM.getAbsolutePath();
					
					//Se iniciaran los ProcessBuilder. 
					ProcessBuilder pb=new ProcessBuilder("java","-cp",filesManagAbsoluta,ficheroInicio,vocal,".\\ResultVocal_"+vocal.toUpperCase()+".txt");
					pb.redirectError(new File(".\\ErroresVocal_"+vocal.toUpperCase()+".txt"));
					pb.start();
                   
                } Thread.sleep(6000);
                              
                String numV;
                String v;
               //Recorre la lista de vocales del principio para leer los ficheros de cada vocal.    
                for(int i=0;i<listaVocales.length;i++) {
                   v=listaVocales[i];
                   File rutaFichero=new File(".\\ResultVocal_"+v.toUpperCase()+".txt");
                   
                   if(rutaFichero.exists()) {	                   
                	   BufferedReader br=fP.getBufferedReader(rutaFichero);
	                   numV=br.readLine();
	                   char[] lista=numV.toString().toCharArray();
	                   if(lista!=null) {
	                	  for(int j=0;j<lista.length;j++) {
	                		  numVocales=lista[j];
	                	  } 
	                	  sumaConteo=sumaConteo+numVocales;		
	                	  PrintWriter prWr=fP.getPrintWriter(result);
	                	  prWr.write("El resultado de la vocal_"+vocal.toUpperCase()+" es: "+numV);
	                	  System.out.println("El resultado de la vocal_"+vocal.toUpperCase()+" es: "+numVocales);
	                	  prWr.close();
	       				} 
	       				PrintWriter prWr2=fP.getPrintWriter(result);
	       				prWr2.write("El resultado Total es: "+sumaConteo);
	   					System.out.println("El resultado Total es: "+sumaConteo);
	   					
	   					//Se cierran los flujos.
	   					br.close();
	   					prWr2.close();
                   }
                }
              
            }
//            ficheroVocales.delete();
            
        } catch(ArrayIndexOutOfBoundsException f) {
            System.out.println("Error, no se está pasando ningún argumento a la matriz.");
            f.getMessage();
            
        } catch (IOException e) {
            System.out.println("Error de entrada/salida.");
            e.getMessage();
            
            e.getStackTrace();
            e.printStackTrace();
            
        } catch (InterruptedException g) {
            System.out.println("Error en el hilo (Thread.sleep())");
            g.getMessage();            
        } catch (NullPointerException h) {
			System.out.println("Error,el fichero 'ResultVocal_"+vocal.toUpperCase()+".txt' está vacío y no se puede acceder a él.");
			h.getMessage();	
		}
	}
}
