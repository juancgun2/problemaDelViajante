package viajante;

import java.util.ArrayList;
import java.util.Iterator;
import iterator.IteradorArco;

public class Vertice<T> {
	private int id; 
	private ArrayList<Arco<T>> adyacentes;
	private int cantidadArcos; 
	
	public Vertice ( int id ) { 
		this.id = id;
		this.adyacentes = new ArrayList<>(); 
		this.cantidadArcos = 0;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	/* 
	 * Complejidad O(n) ya que se debe verificar que el arco a ingresar no exista, es por ello que se debe recorrer toda 
	 * 		la lista de adyacentes en el peor caso.
	 */
	public void addAdyacente ( int origen, int destino, T etiqueta) { 
		if ( ! this.existAdyacente(origen, destino) ) {
			Arco arco = new Arco ( origen,destino,etiqueta); 
			this.adyacentes.add(arco);
			this.cantidadArcos++; 
		}
	}
	
	/* 
	 * Complejidad O(n) donde n es la cantidad de arcos 
	 */
	public void borrarAdyacente ( int destino) { // a partir de un vertice elimino un arco cuyo destino sea el parametro 
		for (int i = 0; i < adyacentes.size(); i++) {
			if ( this.adyacentes.get(i).getVerticeDestino() == destino ) {
				this.adyacentes.remove(i);
				this.cantidadArcos--;
			}
		}
	}
	
	/* 
	 * Complejidad O(n) donde n es la cantidad de arcos, en el peor caso no existe tal arco y se debe recorrer toda la lista
	 */
	public boolean existAdyacente ( int origen, int destino ) { 
		Arco aux = new Arco ( origen,destino, null);
		for (Arco a : adyacentes) {
			if ( a.equals(aux) ) 
				return true;
		}
		return false;
	}
	
	/* 
	 * Complejidad O(1)
	 */
	public boolean equals ( Object o) { 
		try { 
			Vertice v = (Vertice) o; 
			return this.id == v.getId();
		} catch ( Exception e ) { 
			return false;
		}
	}
	
	/* 
	 * Complejidad O(n) donde n es la cantidad de arcos, en el peor caso el arco se encuentra ultimo en la lista
	 */
	public Arco getArco ( int destino ) { // devuelve un arco si el destino es el del parametro
		for (Arco arco : adyacentes) {
			if ( arco.getVerticeDestino() == destino ) { 
				return arco;
			}
		}
		return null;
	}

	/* 
	 * Complejidad O(1)
	 */
	public int getCantidadAdyacentes() {
		return this.cantidadArcos;
	}

	/* 
	 * Complejidad O(1)  
	 */
	public IteradorArco<Integer> getAdyacentes() { // devuelve los ids de los vertices adyacentes
		Iterator<Arco<T>> it = this.adyacentes.iterator(); 
		return new IteradorArco ( it ); 
	}
	
	/* 
	 * Complejidad O(n) donde n es la cantidad de arcos 
	 */
	public ArrayList<Arco<T>> getArcos () { // devuelve la lista de arcos del vertice
		ArrayList<Arco<T>> aux = new ArrayList(); 
		aux.addAll(this.adyacentes);
		return aux;
	}
}

