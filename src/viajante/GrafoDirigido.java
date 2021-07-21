package viajante;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import iterator.IteradorArco;
import iterator.IteradorVertice;

public class GrafoDirigido<T> implements Grafo<T> { 
	private HashMap<Integer,Vertice<T>> verticess;

	public GrafoDirigido ( ) { 
		this.verticess = new HashMap<Integer,Vertice<T>>();
	}
	
	/* 
	 * Complejidad O(1) 
	 */
	@Override
	public void agregarVertice( int verticeId ) {
		if ( !this.verticess.containsKey(verticeId))
			this.verticess.put(verticeId, new Vertice(verticeId));
	}

	/* 
	 * Complejidad O(1) 
	 */
	private boolean existVertice(int verticeId) { 
		return this.verticess.containsKey(verticeId);
	}

	/* 
	 * Complejidad O(n*x) donde n es la cantidad de vertices, en el peor caso el elemento a eliminar se encuentra
	 * 	ultimo en la lista. x representa la cantidad de arcos que tienen los vertices.
	 */
	@Override
	public void borrarVertice(int verticeId) { 
		this.verticess.remove(verticeId); 
		for (Vertice<T> v : verticess.values()) {//tengo que borrar los arcos que tienen como destino el vertice que elimino
			v.borrarAdyacente(verticeId); 
		}
	}

	/* 
	 * Complejidad O(n) donde n es la cantidad de arcos del vertice.
	 */
	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		if ( this.existVertice(verticeId2) ) { 
			if ( this.existVertice(verticeId1) ) 
				this.verticess.get(verticeId1).addAdyacente(verticeId1, verticeId2, etiqueta);
		}
	}
	
	/* 
	 * Complejidad O(n) donde n es la cantidad arcos del vertice.
	 */
	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		if ( this.existVertice(verticeId1) && this.existVertice( verticeId2 ) )
			this.verticess.get(verticeId1).borrarAdyacente(verticeId2);
	}

	/* 
	 * Complejidad O(1) 
	 */
	@Override
	public boolean contieneVertice(int verticeId) {
		return this.existVertice(verticeId);
	}

	/* 
	 * Complejidad O(n) donde n es la cantidad de arcos del vertice.
	 */
	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		if ( this.existVertice(verticeId1) && this.existVertice(verticeId2) ) {
			Vertice aux = this.verticess.get(verticeId1); 
			return aux.existAdyacente(aux.getId(), verticeId2);
		}
		return false;
	}

	/* 
	 * Complejidad O(n) donde n es la cantidad de arcos de ese vertice. 
	 */
	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		if ( this.verticess.containsKey( verticeId1 ) && this.verticess.containsKey( verticeId2 ) ) 
			return this.verticess.get( verticeId1 ).getArco( verticeId2 ); 
		return null;
	}

	/* 
	 * Complejidad O(1) 	
	 */
	@Override
	public int cantidadVertices() {
		return this.verticess.size();
	}
	
	/* 
	 * Complejidad O(n) donde n es la cantidad de vertices. Por cada vertice se accede a un atributo del vertice
	 */
	@Override
	public int cantidadArcos() {
		int suma = 0; 
		for (Vertice<T> vertice : verticess.values()) {
			suma += vertice.getCantidadAdyacentes();
		}
		return suma;
	}

	/* 
	 * Complejidad O(1) 
	 */
	@Override
	public Iterator<Integer> obtenerVertices() { 
		Iterator<Vertice<T>> interno = this.verticess.values().iterator(); 
		return new IteradorVertice ( interno ); 
	}

	/* 
	 * Complejidad O(1) 
	 */
	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) { 
		if ( this.verticess.containsKey( verticeId ) ) 
			return this.verticess.get( verticeId ).getAdyacentes();
		return null;
	}

	/*
	 * Complejidad O(n*x) donde n es la cantidad de vertices y x es la cantidad de arcos 
	 */
	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		ArrayList<Arco<T>> arcos = new ArrayList<Arco<T>>(); 
		for (Vertice v : this.verticess.values()) {
			arcos.addAll( v.getArcos() );
		}
		return arcos.iterator();
	}

	/* 
	 * Complejidad O(1) 
	 * 	
	 */
	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		if ( this.existVertice( verticeId ) ) {
			Iterator<Arco<T>> interno = this.verticess.get(verticeId).getArcos().iterator();
			return new IteradorArco( interno );
		}
		return null;
	}

	/* 
	 * Complejidad O(n) donde n es la cantidad de vertices del grafo
	 */
	@Override
	public void print() {
		System.out.println( " ---- ");
		for (Vertice<T> vertice : this.verticess.values()) {
			System.out.print(vertice.getId() + " - ");
		}
	}

}

