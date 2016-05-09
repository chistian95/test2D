package entidades;

public class EntidadFisica extends Entidad {
	private int capa;
	
	public EntidadFisica(String imagen, int x, int y, int capa) {
		super(imagen, x, y);
		this.capa = capa;
	}

	public int getCapa() {
		return capa;
	}
	
}
