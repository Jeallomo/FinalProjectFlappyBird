package logica;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;

import interfazGrafica.EscenarioJuego;

public class PelotaQueSeMueve extends Thread implements KeyListener {
	
	// Attributes
	private int ejeDireccion = 1;

	// Objects
	private JLabel pajarito;
	private EscenarioJuego juego;
	
	// Construct
	public PelotaQueSeMueve(JLabel pajarito, EscenarioJuego juego) {
		this.pajarito = pajarito;
		this.juego = juego;
	}

	// Method for Thread
	public void run() {
		while (true) {
			
			this.pajarito.setLocation(this.pajarito.getX(), this.pajarito.getY() + (1 * this.ejeDireccion));

			try {
				Thread.sleep(7);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			this.juego.update();
		}
	}
	
	// Methods for KeyListener
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			this.pajarito.setLocation(this.pajarito.getX(), this.pajarito.getY()-50);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
	
	
}
