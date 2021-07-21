package viajante;

import viajante.Ciudad;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		HashMap<Integer,Ciudad> ciudades = new HashMap();
		GrafoNoDirigido<Integer> grafo = new GrafoNoDirigido<>();

		// GRAFO :
		grafo.agregarVertice(5);
		grafo.agregarVertice(10);
		grafo.agregarVertice(15);
		grafo.agregarVertice(20);
		
		grafo.agregarArco(5, 10, 60); 		// 	5 	-- 	10
		grafo.agregarArco(5, 15, 5); 		// 	5   -- 	15
		grafo.agregarArco(5, 20, 10); 		// 	5	-- 	20
		grafo.agregarArco(10, 15, 1); 		// 	10 	-- 	15
		grafo.agregarArco(10, 20, 70); 		// 	10  -- 	20
		grafo.agregarArco(15, 20, 2); 		// 	15  -- 	20

		
		// CIUDADES :
		Ciudad rauch = new Ciudad ( 5, "rauch" ); 
		Ciudad pehuajo = new Ciudad ( 10, "pehuajo" ); 
		Ciudad ayacucho = new Ciudad ( 15 , "ayachucho" ); 
		Ciudad tandil = new Ciudad ( 20, "tandil" ); 
		
		ciudades.put(pehuajo.getId(),pehuajo); 
		ciudades.put(ayacucho.getId(), ayacucho);
		ciudades.put(tandil.getId(),tandil);  
		ciudades.put(rauch.getId(), rauch); 
		
		Sistema sistema = new Sistema(grafo, ciudades);
		System.out.println( sistema.backTracking( rauch ) ); 
		System.out.println( " linea ");
		System.out.println( sistema.greedy( rauch ));
		
		
	}

}
