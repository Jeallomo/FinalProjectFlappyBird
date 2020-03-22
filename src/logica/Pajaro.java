package logica;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;

import interfazGrafica.EscenarioJuego;

public class Pajaro extends Thread implements KeyListener {
	
	// Attributes
	private int ejeDireccion = 1;
	private int StaticY;
	private boolean terminar;

	// Objects
	private JLabel pajarito;
	private EscenarioJuego juego;
	
	// Construct
	public Pajaro(JLabel pajarito, EscenarioJuego juego) {
		this.pajarito = pajarito;
		this.juego = juego;
		this.terminar = false;
	}

	// Method for Thread
	public void run() {
		while (!terminar) {
			
			if(this.ejeDireccion == 1) {
				
				this.pajarito.setLocation(this.pajarito.getX(), this.pajarito.getY() + (1 * this.ejeDireccion));

				try {
					Thread.sleep(7);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			if(this.ejeDireccion == -1 && (this.StaticY-50 != this.pajarito.getY()) ) {
				
				this.pajarito.setLocation(this.pajarito.getX(), this.pajarito.getY() + (1 * this.ejeDireccion));

				try {
					Thread.sleep(7);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else {
				this.ejeDireccion = 1;
			}

			this.juego.update();
		}
	}
	
	//General Methods
	public void endThread() {
		this.terminar = true;
	}
	
	// Methods for KeyListener
	@Override
	public void keyPressed(KeyEvent e) {
		this.ejeDireccion *= -1;
		this.StaticY = this.pajarito.getY();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
}
