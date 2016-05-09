package juego;

import pantalla.Pantalla;

public class Main {
	public static void main(String[] args) {
		Pantalla pantalla = new Pantalla();
				
		Thread trPantalla = new Thread(pantalla);
		
		trPantalla.start();
	}
}
