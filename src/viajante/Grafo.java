package viajante;

import java.util.Iterator;

public interface Grafo<T> { 
	
	// Agrega un vertice 
	public void agregarVertice(int nombre);

	// Borra un vertice
	public void borrarVertice(int nombre);

	// Agrega un arco con una etiqueta, que conecta el verticeId1 con el verticeId2
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta);

	// Borra el arco que conecta el verticeId1 con el verticeId2
	public void borrarArco(int nombre1, int nombre2);

	// Verifica si existe un vertice
	public boolean contieneVertice(int nombre);  

	// Verifica si existe un arco entre dos vertices
	public boolean existeArco(int nombre1, int nombre2);
	
	// Obtener el arco que conecta el verticeId1 con el verticeId2
	public Arco<T> obtenerArco(int nombre1, int nombre2);

	// Devuelve la cantidad total de vertices en el grafo
	public int cantidadVertices();

	// Devuelve la cantidad total de arcos en el grafo
	public int cantidadArcos();

	// Obtiene un iterador que me permite recorrer todos los vertices almacenados en el grafo 
	public Iterator<Integer> obtenerVertices();

	// Obtiene un iterador que me permite recorrer todos los vertices adyacentes a verticeId 
	public Iterator<Integer> obtenerAdyacentes(int nombre);

	// Obtiene un iterador que me permite recorrer todos los arcos del grafo
	public Iterator<Arco<T>> obtenerArcos();
		
	// Obtiene un iterador que me permite recorrer todos los arcos que parten desde verticeId
	public Iterator<Arco<T>> obtenerArcos(int nombre); 
	
	public void print();
	
	
}
