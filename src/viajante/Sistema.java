package viajante;

import java.util.HashMap;
import java.util.Iterator;

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
		if ( ! this.ciudades.containsKey( c.getId() ) ) {
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
		this.visitados.clear();
		this.solucion.clear();
		if ( this.grafoConexo() ) {
			this.solucion.clear();
			this.visitados.clear();
			Camino solParcial = new Camino( this.ciudades.size() );
			solParcial.addCiudad( origen );
			this.visitados.put( origen.getId(), origen );
			backTracking ( origen.getId(), origen, solParcial);
		}
		return this.solucion;
	}
	
	private void backTracking ( int vertice, Ciudad origen, Camino solParcial ) { 
		if ( this.ciudades.size() == solParcial.size() ) { 
			if ( this.grafo.existeArco(vertice, origen.getId() )) {
				Arco arco = this.grafo.obtenerArco( vertice, origen.getId() );
				int distanciaUltima = (int) arco.getEtiqueta();
				solParcial.sumarDistancia( distanciaUltima );
				if ( solParcial.getDistancia() < this.solucion.getDistancia() )  
					this.solucion = solParcial.copy();
				solParcial.restarDistancia( distanciaUltima );
			}
		} else { 
			Iterator it = this.grafo.obtenerAdyacentes( vertice );
			while ( it.hasNext() ) { 
				int siguiente = (int) it.next(); 
				if ( ! this.visitados.containsKey( siguiente )) { 
					solParcial.addCiudad( this.ciudades.get( siguiente ) );
					Arco arco = this.grafo.obtenerArco( vertice , siguiente ); 
					int distancia = (int) arco.getEtiqueta();
					solParcial.sumarDistancia( distancia );
					this.visitados.put( siguiente, this.ciudades.get( siguiente ) );
					
					if ( solParcial.getDistancia() < this.solucion.getDistancia() )
						backTracking ( siguiente, origen, solParcial ); 
					
					solParcial.remove ( siguiente ); 
					solParcial.restarDistancia( distancia );
					this.visitados.remove( siguiente ); 
				}
			}
		}
	}
	
	public Camino greedy ( Ciudad origen ) { 
		this.solucion.clear();
		if ( this.grafoConexo() ) {
			this.visitados.clear();
			this.solucion.addCiudad( origen );
			this.visitados.put( origen.getId(), origen );
			
			while ( this.solucion.size() < this.ciudades.size() ) {
				int ciudad = this.ciudadMasCercana ( this.solucion.getLastInsert() );
				if ( ciudad != Integer.MIN_VALUE ) {
					Arco arco = this.grafo.obtenerArco( this.solucion.getLastInsert(), ciudad);
					this.solucion.sumarDistancia( ( int ) arco.getEtiqueta());
					this.solucion.addCiudad( this.ciudades.get( ciudad ));
					this.visitados.put( ciudad , this.ciudades.get( ciudad ));
				} else { 
					this.solucion.clear();
					return this.solucion;
				}	
			}
			if ( !this.solucion.isEmpty() ) {
				int ultimaCiudad = this.solucion.getLastInsert();
				if ( this.grafo.existeArco(ultimaCiudad, origen.getId() )) {
					Arco arco = this.grafo.obtenerArco ( this.solucion.getLastInsert(), origen.getId() ) ;
					int distancia = (int) arco.getEtiqueta();
					this.solucion.sumarDistancia( distancia );
				} else  
					this.solucion.clear();
			}
		}
		return this.solucion;
	}
	
	private int ciudadMasCercana ( int idCiudad ) { 
		int menor= Integer.MAX_VALUE; 
		int idCity = Integer.MIN_VALUE;
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
	
	/**
	 * Verifica que el grafo sea conexo, en caso de ser true no se podra encontrar ninguna solucion
	 */
	private boolean grafoConexo () { 
		if ( ! this.ciudades.isEmpty() ) {
			this.visitados.clear();
			int origen = 0;
			for (Integer id : this.ciudades.keySet() ) {
				origen = id;
				break;
			}
			this.dfs( origen );
			if ( this.visitados.size() == this.ciudades.size() )
				return true;
		}
		return false;
	}
	
	private void dfs ( int vertice ) {
		Iterator it = this.grafo.obtenerAdyacentes( vertice ); 
		this.visitados.put ( vertice, this.ciudades.get( vertice ) );
		while ( it.hasNext() ) { 
			int siguiente = (int)it.next(); 
			if ( !this.visitados.containsKey( siguiente ))
				dfs ( siguiente );
		}
	} 
	
}
