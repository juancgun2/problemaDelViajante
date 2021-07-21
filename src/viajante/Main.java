package viajante;

import viajante.Ciudad;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		HashMap<Integer,Ciudad> ciudades = new HashMap();
		GrafoNoDirigido<Integer> grafo = new GrafoNoDirigido<>();

		// GRAFO :
		// TEST 1
		grafo.agregarVertice(5);
		grafo.agregarVertice(10);
		grafo.agregarVertice(15);
		grafo.agregarVertice(20);
		
		// Test 2
		grafo.agregarVertice( 25);
		grafo.agregarVertice( 30);
		grafo.agregarVertice( 35);
		
		// Test 3 
		grafo.agregarVertice( 40 );
		
		/*
		 * TEST 1
		grafo.agregarArco(5, 10, 60); 		// 	5 	-- 	10
		grafo.agregarArco(5, 15, 5); 		// 	5   -- 	15
		grafo.agregarArco(5, 20, 10); 		// 	5	-- 	20
		grafo.agregarArco(10, 15, 1); 		// 	10 	-- 	15
		grafo.agregarArco(10, 20, 70); 		// 	10  -- 	20
		grafo.agregarArco(15, 20, 2); 		// 	15  -- 	20
		*/
		
		// Test 2
		grafo.agregarArco(5, 20, 8); 		
		grafo.agregarArco(5, 10, 15); 		
		grafo.agregarArco(10, 30, 7);
		grafo.agregarArco(30, 35, 4);
		grafo.agregarArco(35, 15, 3);
		grafo.agregarArco(15, 25, 6);
		grafo.agregarArco(15, 20, 9);
		
		/*
			grafo.agregarArco(5, 25, 12);
			grafo.agregarArco(20, 25, 90);
			grafo.agregarArco(35, 25, 35);
		*/
		
		// Test 3 
		grafo.agregarArco(25, 5, 12);
		grafo.agregarArco(25, 35, 35);
		grafo.agregarArco(25, 20, 90);
		
		// Test 3 
		grafo.agregarArco(10, 40, 101 );
		grafo.agregarArco(40, 5, 1);
		grafo.agregarArco(40, 30, 2); 
		grafo.agregarArco(40, 20, 5);
		grafo.agregarArco(15, 30, 4);
		
		// CIUDADES : 
		// Test 1
		Ciudad rauch = new Ciudad ( 5, "rauch" ); 
		Ciudad pehuajo = new Ciudad ( 10, "pehuajo" ); 
		Ciudad ayacucho = new Ciudad ( 15 , "ayachucho" ); 
		Ciudad tandil = new Ciudad ( 20, "tandil" ); 
		
		// Test 2
		Ciudad a = new Ciudad ( 25, "a");
		Ciudad b = new Ciudad ( 30, "b");
		Ciudad c = new Ciudad ( 35, "c");
		
		// Test 3 
		Ciudad d = new Ciudad ( 40, "d");
		
		// Test 1
		ciudades.put(pehuajo.getId(),pehuajo); 
		ciudades.put(ayacucho.getId(), ayacucho);
		ciudades.put(tandil.getId(),tandil);  
		ciudades.put(rauch.getId(), rauch);
		
		// Test 2
		ciudades.put( a.getId(), a);
		ciudades.put( b.getId(), b);
		ciudades.put( c.getId(), c); 
		
		// Test 3
		ciudades.put( 40, d);
		
		
		Sistema sistema = new Sistema(grafo, ciudades);
		
		System.out.println( " Solucion backTracking ");
		System.out.println( sistema.backTracking( rauch ));
		
		System.out.println( " \n ");
		
		System.out.println( " Solucion greedy ");
		System.out.println( sistema.greedy( rauch ));
		
		
	}

}
