package entidades;

public class EntidadControlable extends EntidadFisica {
	private boolean arriba;
	private boolean abajo;
	private boolean izquierda;
	private boolean derecha;
	private int velocidad;
	
	public EntidadControlable(String archivo, int x, int y, int velocidad) {
		super(archivo, x, y, 0);
		this.velocidad = velocidad;
	}

	public boolean isArriba() {
		return arriba;
	}

	public boolean isAbajo() {
		return abajo;
	}

	public boolean isIzquierda() {
		return izquierda;
	}

	public boolean isDerecha() {
		return derecha;
	}

	public void setArriba(boolean arriba) {
		this.arriba = arriba;
	}

	public void setAbajo(boolean abajo) {
		this.abajo = abajo;
	}

	public void setIzquierda(boolean izquierda) {
		this.izquierda = izquierda;
	}

	public void setDerecha(boolean derecha) {
		this.derecha = derecha;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}	
}
