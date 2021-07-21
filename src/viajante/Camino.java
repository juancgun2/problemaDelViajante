package viajante;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import viajante.Ciudad;

public class Camino {
	private int distancia; 
	/* 
	 * Uso LinkedHashMap para que mantenga el orden de insercion de los elementos. 
	 * Si uso HashMap en el toString no me los muestra en orden
	 */
	private LinkedHashMap<Integer,Ciudad> camino;
	private int idUltimo; 
	
	private Camino ( LinkedHashMap<Integer,Ciudad> camino, int distancia ) { 
		this.camino = camino;
		this.distancia = distancia;
	}
	
	public Camino ( int size ) { 
		this.camino = new LinkedHashMap<Integer,Ciudad>( size );
		this.distancia = 0;
	}
	
	public int getDistancia () { 
		if ( this.isEmpty() )
			return Integer.MAX_VALUE;
		return this.distancia; 
	}
	
	public void addCiudad ( Ciudad ciudad) { 
		this.camino.put(ciudad.getId(), ciudad);
		this.idUltimo = ciudad.getId();
	}
	
	public int size ( ) { 
		return this.camino.size();
	}
	
	// ver si se puede modificar la distancia desde camino y no desde afuera
	public void remove ( int id ) { 
		this.camino.remove( id );
		this.calcularUltimo();
	}
	
	public String toString () { 
		if ( this.camino.isEmpty() ) 
			return " No se encontro solucion ";
		return " km total: " + this.distancia + " Camino: " + this.camino.toString();
	}
	
	public boolean isEmpty () { 
		return this.camino.isEmpty();
	}
	
	public Camino copy ( ) { 
		Camino retorno = new Camino ( (LinkedHashMap<Integer,Ciudad>)this.camino.clone(), this.distancia ); 
		return retorno;
	}
	
	public void clear () { 
		this.camino.clear();
		this.distancia = 0;
	}

	public void sumarDistancia(int numero) {
		this.distancia += numero;
	}
	
	public void restarDistancia ( int numero) { 
		this.distancia -= numero;
	}
	
	/* 
	 * Ver si realmente retorna el ultimo
	 */
	public int getLastInsert () { 
		return this.idUltimo;
	}
	
	/* 
	 * ver si calcula el ultimo bien o hace cualquier cosa
	 */
	private void calcularUltimo() { 
		for (Integer id: this.camino.keySet() ){
			this.idUltimo = id;
		}
	}
}
