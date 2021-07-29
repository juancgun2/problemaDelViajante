package viajante;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import java.io.FileNotFoundException;


public class VisorCiudad {
	
	public static void main(String[] args) {
		Sistema sistema = new Sistema();
		// "Data/Grafo1.json"
		// "Data/Grafo2.json"
		String Path = "Data/Grafo2.json";
        cargarDatos( Path, sistema );
        System.out.println( " BackTracking");
        				int startBack = (int)System.nanoTime();        				
        System.out.println( sistema.backTracking( new Ciudad(5,"Nombre") ) ); 
        
				        int endBack = (int)System.nanoTime();
				        int elapsedTimeBack = endBack - startBack;
				        System.out.println( "tiempo backtracking Instant: " + elapsedTimeBack );
				        
        System.out.println( " Greedy ");       
        				int startGreedy= (int)System.nanoTime();      				
        System.out.println( sistema.greedy( new Ciudad ( 5,"Nombre" ) ) );
        
		        		int endGreedy= (int)System.nanoTime();
		        		int elapsedTimeGreedy = endGreedy- startGreedy;
		        		System.out.println( "tiempo greedy: " + elapsedTimeGreedy );
	}
	
	
	private static void cargarDatos(String jsonFile, Sistema sistema) {
	    File jsonInputFile = new File(jsonFile);
	    InputStream is;
	    try {
	        is = new FileInputStream(jsonInputFile);
	        // Creo el objeto JsonReader de Json.
	        JsonReader reader = Json.createReader(is);
	        // Obtenemos el JsonObject a partir del JsonReader.
	        JsonArray ciudades = (JsonArray) reader.readObject().getJsonArray("ciudades");
	        for (JsonObject ciudad : ciudades.getValuesAs(JsonObject.class) ) {
	        	Ciudad c = new Ciudad(ciudad.getInt( "id" ), ciudad.getString( "nombre" ) );
	        	sistema.addCiudad( c );
	        }
	        for (JsonObject ciudad : ciudades.getValuesAs(JsonObject.class) ) {
				for ( JsonValue adyac : ciudad.getJsonArray( "adyacentes" ) ) {
					JsonObject adyacente = adyac.asJsonObject();
					sistema.agregarArco( ciudad.getInt("id"), adyacente.getInt("id"), adyacente.getInt("distancia") );
				}
			}
	        reader.close();
	
	    } catch (FileNotFoundException e) {
	        // TODO Auto-generated catch block
	            e.printStackTrace();
        }
	} 
	
	
}

