package iterator;

import java.util.Iterator;
import viajante.Arco;

public class IteradorArco<T> implements Iterator<Integer> {
	
	private Iterator<Arco<T>> it; 
	
	public IteradorArco( Iterator<Arco<T>> it ) { 
		this.it = it; 
	}

	@Override
	public boolean hasNext() {
		return it.hasNext();
	}

	@Override
	public Integer next() {
		return it.next().getVerticeDestino();
	}

}
