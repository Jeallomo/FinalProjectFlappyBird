package logica;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import interfazGrafica.EscenarioJuego;

public class Pajaro extends Thread implements KeyListener {

	// Objects
	private JLabel pajarito;
	private EscenarioJuego juego;
	
	// Attributes
	private int ejeDireccion = 1;
	private int StaticY;
	private boolean terminar;
	private boolean delay = false;
	private int alturaDePaso = 150;

	
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
					//juego.bird.setIcon(new ImageIcon(juego.imagenBird0.getImage().getScaledInstance(juego.getBirdSizeH(), juego.getBirdSizeW(), Image.SCALE_SMOOTH)));
					delay = false;
					this.pajarito.setLocation(this.pajarito.getX(), this.pajarito.getY() + (1 * this.ejeDireccion));
					
					if(alturaDePaso - this.pajarito.getY() == 0) {
						System.out.println("pasa 0");
						juego.bird.setIcon(new ImageIcon(juego.imagenBird0.getImage().getScaledInstance(juego.getBirdSizeH(), juego.getBirdSizeW()-10, Image.SCALE_SMOOTH)));
					}
					if(this.pajarito.getY()- alturaDePaso  >= 10 & this.pajarito.getY()- alturaDePaso < 30) {
						juego.bird.setIcon(new ImageIcon(juego.imagenBird20.getImage().getScaledInstance(juego.getBirdSizeH(), juego.getBirdSizeW(), Image.SCALE_SMOOTH)));
					}
					if(this.pajarito.getY() - alturaDePaso >= 30 & this.pajarito.getY() - alturaDePaso < 50) {
						juego.bird.setIcon(new ImageIcon(juego.imagenBird45.getImage().getScaledInstance(juego.getBirdSizeH(), juego.getBirdSizeW(), Image.SCALE_SMOOTH)));
					}
					if(this.pajarito.getY() - alturaDePaso >= 50 & this.pajarito.getY() - alturaDePaso < 70) {
						juego.bird.setIcon(new ImageIcon(juego.imagenBird75.getImage().getScaledInstance(juego.getBirdSizeH(), juego.getBirdSizeW(), Image.SCALE_SMOOTH)));
					}
					if( this.pajarito.getY() - alturaDePaso >=   70) {
						juego.bird.setIcon(new ImageIcon(juego.imagenBird90.getImage().getScaledInstance(juego.getBirdSizeH()-5, juego.getBirdSizeW(), Image.SCALE_SMOOTH)));
					}
				
					System.out.println(this.pajarito.getY() - alturaDePaso);
					
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
	public void animacionCaida() {
		
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
		juego.bird.setIcon(new ImageIcon(juego.imagenBirdArriba.getImage().getScaledInstance(juego.getBirdSizeH(), juego.getBirdSizeW(), Image.SCALE_SMOOTH)));
		delay = true;
		alturaDePaso = this.StaticY;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
}
