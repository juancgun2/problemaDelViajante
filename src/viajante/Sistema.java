package viajante;

import java.util.HashMap;
import java.util.Iterator;

import iterator.IteradorVertice;

public class Sistema {
	private Grafo grafo;
	private Camino solucion;
	private HashMap< Integer, Ciudad > ciudades; 
	private HashMap< Integer, Ciudad > visitados; 
	
	public Sistema (Grafo g, HashMap<Integer,Ciudad> ciudad) { 
		this.grafo = g; 
		this.ciudades = ciudad;
		this.solucion = new Camino ( this.ciudades.size() );
		this.visitados = new HashMap( this.ciudades.size() );
	}
	/*
	public Sistema () { 
		this.ciudades = new HashMap(); 
		this.visitados = new HashMap();
		this.solucion = new Camino ();
	}*/

	/* 
	 * Se debe agregar una ciudad con este metodo para mantener el sistema sincronizado
	 */
	public void addCiudad ( Ciudad c ) {
		if ( this.ciudades.containsKey( c.getId() ) ) {
			this.ciudades.put( c.getId(), c); 
			this.grafo.agregarVertice( c.getId() );
		}
	}
	
	/* 
	 * Se debe eliminar una ciudad con este metodo para mantener el sistema sincronizado
	 */
	public void removeCiudad ( Ciudad c ) {
		this.ciudades.remove( c.getId() ); 
		this.grafo.borrarVertice( c.getId() );
	} 
	
	public Camino backTracking ( Ciudad origen ) { 
		if ( ! this.solucion.isEmpty() )
			this.solucion.clear();
		if ( !this.visitados.isEmpty() )
			this.visitados.clear();
		Camino solParcial = new Camino( this.ciudades.size() );
		solParcial.addCiudad( origen );
		this.visitados.put( origen.getId(), origen );
		backTracking ( origen.getId(), origen, solParcial);
		return this.solucion;
	}
	
	private void backTracking ( int vertice, Ciudad origen, Camino solParcial ) { 
		if ( this.ciudades.size() == solParcial.size() ) { 
			if ( this.grafo.existeArco(vertice, origen.getId() )) {
			//if ( vertice == origen.getId() ) { 
			//esto creo que no va, de alguna manera debo controlar que origen sea adya de vertice
				if ( solParcial.getDistancia() < this.solucion.getDistancia() ) 
					this.solucion = solParcial.copy();
			}
		} else { 
			Iterator it = this.grafo.obtenerAdyacentes( vertice );
			while ( it.hasNext() ) { 
				int siguiente = (int) it.next(); 
				if ( ! this.visitados.containsKey( siguiente )) { 
					solParcial.addCiudad( this.ciudades.get( siguiente ) );
					Arco arco = this.grafo.obtenerArco( vertice , siguiente ); 
					solParcial.sumarDistancia( (int) arco.getEtiqueta() );
					this.visitados.put( siguiente, this.ciudades.get( siguiente ) );
					
					if ( solParcial.getDistancia() < this.solucion.getDistancia() )
						backTracking ( siguiente, origen, solParcial ); 
					
					solParcial.remove ( siguiente ); 
					solParcial.restarDistancia( (int) arco.getEtiqueta());
					this.visitados.remove( siguiente ); 
				}
			}
		}
	}
	
	public Camino greedy ( Ciudad origen ) { 
		this.solucion.clear();
		this.visitados.clear();
		this.solucion.addCiudad( origen );
		this.visitados.put( origen.getId(), origen );
		
		while ( this.solucion.size() < this.ciudades.size() ) {
			int ciudad = this.ciudadMasCercana ( this.solucion.getLastInsert() );
			Arco arco = this.grafo.obtenerArco( this.solucion.getLastInsert(), ciudad);
			this.solucion.sumarDistancia( ( int ) arco.getEtiqueta());
			this.solucion.addCiudad( this.ciudades.get( ciudad ));
			this.visitados.put( ciudad , this.ciudades.get( ciudad ));
		}
		
	return this.solucion;
	} 
	
	private int ciudadMasCercana ( int idCiudad ) { 
		int menor= Integer.MAX_VALUE; 
		int idCity = 0;
		int siguiente = 0;
		Iterator it = this.grafo.obtenerAdyacentes( idCiudad ); 
		while ( it.hasNext() ) { 
			siguiente = (int)it.next(); 
			if ( !this.visitados.containsKey(siguiente) ) {
				Arco arco = this.grafo.obtenerArco( idCiudad, siguiente); 
				if ( (int)arco.getEtiqueta() < menor ) { 
					menor = (int)arco.getEtiqueta();
					idCity = siguiente;
				}
			}
		}
		return idCity;
	}
	
}
