package entidades;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Entidad {
	private int x;
	private int y;
	private Image imagen;
	
	public Entidad(String archivo, int x, int y) {
		String imagenUrl = "../res/"+archivo+".png";
        try {
			imagen = ImageIO.read(getClass().getResourceAsStream(imagenUrl));
		} catch (IOException e) {
			System.err.println("No se ha podido cargar la imagen "+archivo);
			e.printStackTrace();
			System.exit(-1);
		}
        this.x = x;
        this.y = y;
	}

	public int getX() {
		return x;
	}
	
	public int getX2() {
		return (x+imagen.getWidth(null));
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}
	
	public int getY2() {
		return (y+imagen.getHeight(null));
	}

	public void setY(int y) {
		this.y = y;
	}

	public Image getImagen() {
		return imagen;
	}
}
