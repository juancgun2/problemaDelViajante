package viajante;

public class GrafoNoDirigido<Comparable> extends GrafoDirigido<Comparable> {

	@Override
	public void agregarArco(int verticeId1, int verticeId2, Comparable etiqueta) {
		super.agregarArco(verticeId1, verticeId2, etiqueta);
		super.agregarArco(verticeId2, verticeId1, etiqueta);
	}
	
	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		super.borrarArco(verticeId1, verticeId2);
		super.borrarArco(verticeId2, verticeId1);
	}

}