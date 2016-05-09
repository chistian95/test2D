package pantalla;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import entidades.Entidad;
import entidades.EntidadControlable;
import entidades.EntidadFisica;

public class Pantalla extends JFrame implements Runnable, KeyListener {
	private static final long serialVersionUID = 1L;
	private static final int REFRESCO = 10;
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	
	private List<Entidad> entidades;
	private List<EntidadControlable> controlables;
	private List<EntidadFisica> fisicas;
	
	public Pantalla() { 
		entidades = new ArrayList<Entidad>();
		controlables = new ArrayList<EntidadControlable>();
		fisicas = new ArrayList<EntidadFisica>();
		crearEntidad("imagen", 0, 100);
		crearEntidadControlable("imagen", 0, 356, 2);
		crearEntidadFisica("imagen", 600, 356, 0);
		addKeyListener(this);
		
        setUndecorated(true);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
        	public void windowClosing(WindowEvent e) {
        		dispose();
        		System.exit(0);
        	}
        });
	}
	
	private void crearEntidad(String imagen, int x, int z) {
		Entidad entidad = new Entidad(imagen, x, z);
		entidades.add(entidad);
	}
	
	private void crearEntidadControlable(String imagen, int x, int z, int velocidad) {
		EntidadControlable entidad = new EntidadControlable(imagen, x, z, velocidad);
		entidades.add(entidad);
		fisicas.add(entidad);
		controlables.add(entidad);
	}
	
	private void crearEntidadFisica(String imagen, int x, int z, int capa) {
		EntidadFisica entidad = new EntidadFisica(imagen, x, z, capa);
		entidades.add(entidad);
		fisicas.add(entidad);
	}
	
	public void paint(Graphics g) {
		BufferedImage bf = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
		super.paint(bf.getGraphics());
		for(EntidadControlable entidad : controlables) {
			moverEntidad(entidad);
		}
		for(Entidad entidad : entidades) {				
			bf.getGraphics().drawImage(entidad.getImagen(), entidad.getX(), entidad.getY(), this);
		}
		g.drawImage(bf, 0, 0, null);
	}
	
	private void moverEntidad(EntidadControlable entidad) {
		if(entidad.isDerecha()) {
			entidad.setX(entidad.getX()+entidad.getVelocidad());
		}
		if(entidad.isIzquierda()) {
			entidad.setX(entidad.getX()-entidad.getVelocidad());
		}
		if(entidad.isArriba()) {
			entidad.setY(entidad.getY()-entidad.getVelocidad());
		}
		if(entidad.isAbajo()) {
			entidad.setY(entidad.getY()+entidad.getVelocidad());
		}
		
		for(EntidadFisica entidadF : fisicas) {
			if(entidadF.getCapa() != entidad.getCapa()) {
				continue;
			}
			if(entidadF.equals(entidad)) {
				continue;
			}
			if(entidad.getX() < entidadF.getX2() && entidad.getX2() > entidadF.getX() && entidad.getY() < entidadF.getY2() && entidad.getY2() > entidadF.getY()) {
				if(entidad.isAbajo() && entidad.getY2() > entidadF.getY() && entidad.getY2() < entidadF.getY2()) {
					entidad.setY(entidad.getY()-(entidad.getY2()-entidadF.getY()));
				} else if(entidad.isArriba() && entidad.getY() < entidadF.getY2() && entidad.getY() > entidadF.getY()) {
					entidad.setY(entidad.getY()+(entidadF.getY2()-entidad.getY()));
				} else if(entidad.isDerecha() && entidad.getX2() > entidadF.getX() && entidad.getX2() < entidadF.getX2()) {
					entidad.setX(entidad.getX()-(entidad.getX2()-entidadF.getX()));
				} else if(entidad.isIzquierda() && entidad.getX() < entidadF.getX2() && entidad.getX() > entidadF.getX()) {
					entidad.setX(entidad.getX()+(entidadF.getX2()-entidad.getX()));
				}
			}
		}
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(REFRESCO);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}	        
	        repaint();
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		for(EntidadControlable entidad : controlables) {
			if(e.getKeyCode() == KeyEvent.VK_W) {
				entidad.setArriba(true);
			}
			if(e.getKeyCode() == KeyEvent.VK_S) {
				entidad.setAbajo(true);
			}
			if(e.getKeyCode() == KeyEvent.VK_A) {
				entidad.setIzquierda(true);
			}
			if(e.getKeyCode() == KeyEvent.VK_D) {
				entidad.setDerecha(true);
			}
		}		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		for(EntidadControlable entidad : controlables) {
			if(e.getKeyCode() == KeyEvent.VK_W) {
				entidad.setArriba(false);
			}
			if(e.getKeyCode() == KeyEvent.VK_S) {
				entidad.setAbajo(false);
			}
			if(e.getKeyCode() == KeyEvent.VK_A) {
				entidad.setIzquierda(false);
			}
			if(e.getKeyCode() == KeyEvent.VK_D) {
				entidad.setDerecha(false);
			}
		}
	}
}
