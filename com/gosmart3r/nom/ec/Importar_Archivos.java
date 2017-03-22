package com.gosmart3r.nom.ec;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


import com.unit4.karat.form.FMObject;
import com.unit4.karat.session.Session;

public class Importar_Archivos {

	 public static final String SEPARATOR=";";
	 public static final String QUOTE="\"";
	
	public Importar_Archivos() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	 private static String[] removeTrailingQuotes(String[] fields) {

	      String result[] = new String[fields.length];

	      for (int i=0;i<result.length;i++){
	         result[i] = fields[i].replaceAll("^"+QUOTE, "").replaceAll(QUOTE+"$", "");
	      }
	      return result;
	   }
	
	public boolean SubirArchivo(Session session, FMObject fmObject, String sArchivo){
		boolean bRetorno=false;
		 BufferedReader br = null;
	      
	      try {
	         
	         br =new BufferedReader(new FileReader(sArchivo));
	         String line = br.readLine();
	         while (null!=line) {
	            String [] fields = line.split(SEPARATOR);
	             
	            fields = removeTrailingQuotes(fields);
	          
	            
	          //imprimimos en pantalla el valor de cada elemento...
	            for (int i = 0 ; i < fields.length ; i++) {
	                System.out.println("Elemento en indice " + i + ": " + fields[i]);
	            }
	            
	            line = br.readLine();
	         }
	         
	      } catch (Exception e) {
	         
	      } finally {
	         
	       }
	      if (null!=br) {   try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} }
		return bRetorno;
	}
}