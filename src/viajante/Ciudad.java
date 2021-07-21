package viajante;

public class Ciudad {
	private int id; 
	private String nombre; 
	
	public Ciudad ( int id, String nombre ) { 
		this.id = id; 
		this.nombre = nombre;
	}
	
	public int getId ( ) { 
		return this.id;
	}
	
	public String getNombre() { 
		return this.nombre;
	}
	
	public boolean equals ( Object o ) { 
		try { 
			Ciudad c = (Ciudad)o; 
			return c.getId() == this.id;
		} catch ( Exception e ) {
			return false;
		}
	}
	
	public String toString() { 
		return this.getNombre() ;
	}
}
