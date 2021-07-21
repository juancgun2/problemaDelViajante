package iterator;

import java.util.Iterator;
import viajante.Vertice;

public class IteradorVertice<T> implements Iterator<Integer> {
	
	private Iterator<Vertice<T>> it; 
	
	public IteradorVertice( Iterator<Vertice<T>> it ) { 
		this.it = it; 
	}

	@Override
	public boolean hasNext() {
		return it.hasNext();
	}

	@Override
	public Integer next() {
		return it.next().getId(); 
	}
}