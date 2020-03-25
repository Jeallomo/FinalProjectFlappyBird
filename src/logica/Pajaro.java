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
	private boolean delay = false;

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
		 while(true) {
			 while (!terminar) {
				if(this.ejeDireccion == 1) {
					if(delay) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					delay = false;
					this.pajarito.setLocation(this.pajarito.getX(), this.pajarito.getY() + (1 * this.ejeDireccion));
					try {
						Thread.sleep(6);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				while(this.ejeDireccion == -1) {
					if(this.StaticY-45 != this.pajarito.getY()) {
						this.pajarito.setLocation(this.pajarito.getX(), this.pajarito.getY() + (1 * this.ejeDireccion));
						try {
							Thread.sleep(4);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					else {
						this.ejeDireccion = 1;
					}
				}
				this.juego.update();
			 }
			this.pajarito.setLocation(100, 150);
			 try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		 }
	}
	
	//General Methods
	public void pauseThread() {
		this.terminar = true;
	}
	
	public void resumeThread() {
		this.terminar = false;
		ejeDireccion = 1;
	}
	
	// Methods for KeyListener
	@Override
	public void keyPressed(KeyEvent e) {
		this.ejeDireccion = -1;
		this.StaticY = this.pajarito.getY();
		delay = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
}
